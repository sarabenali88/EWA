<template>
  <Bar
      id="my-chart-id"
      :options="chartOptions"
      v-if="loaded" :data="chartData"
  />
</template>

<script>
import {Bar} from 'vue-chartjs'
import {Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, Colors} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, Colors)

export default {
  name: 'StatisticsPageComponent',
  components: {Bar},
  inject: ["imagesService"],

  data() {
    return {
      images: [],
      loaded: false,
      chartData: {
        labels: [],
        datasets: [{
          label: 'amount of images',
          data: [],
          backgroundColor: ['rgb(232, 44, 44)']
        }]
      },
      chartOptions: {
        responsive: true
      }
    }
  },
  async created() {
    // load data from api and add it to the images array
    this.images = await this.imagesService.asyncFindAll();

    // Make labels and data for the chart
    this.chartData.labels = this.getData().map(row => row.time);
    this.chartData.datasets[0].data = this.getData().map(row => row.amountOfImages);

    // set loaded to true so the chart can be shown
    this.loaded = true
  },
  methods: {
    /**
     * Formats date to DD-MM-YYYY
     * @author Rowin Schenk, Pieter Leek
     * @param dateString
     * @returns {Date}
     */
    dateConverter(dateString) {
      const parts = dateString.split(' ')[0].split('-');

      if (parts.length !== 3) {
        throw new Error('Invalid date format. Expected format: DD-MM-YYYY');
      }

      const [day, month, year] = parts.map(Number); // Convert strings to numbers
      const convertedDate = new Date(year, month - 1, day);
      if (convertedDate.toString() === 'Invalid Date') {
        throw new Error('Invalid date value');
      }
      return convertedDate;
    },
    /**
     * Subtracts desired amount of months of the given date
     * @author Rowin Schenk, Pieter Leek
     * @param date
     * @param months
     * @returns {Date}
     */
    dateMinusMonths(date, months) {
      let newDate = new Date(date);
      newDate.setMonth(newDate.getMonth() - months);
      return newDate;
    },
    /**
     * Counts the amount of images that were created more recent than the given amount of months ago
     * @author Rowin Schenk, Pieter Leek
     * @param months
     * @returns {number}
     */
    getDataMonths(months) {
      const comparisonDate = this.dateMinusMonths(new Date(), months);
      return this.images.filter(image => {
        const imageDate = this.dateConverter(image.upDateDate);
        return imageDate >= comparisonDate;
      }).length;
    },

    /**
     * Gets name of the labels and the data for the chart
     * @author Rowin Schenk
     * @returns {[{time: string, amountOfImages: (*|number)},{time: string, amountOfImages: (*|number)},{time: string, amountOfImages: (*|number)}]}
     */
    getData() {
      return [{time: "Last month", amountOfImages: this.getDataMonths(1)},
        {time: "Last Quarter", amountOfImages: this.getDataMonths(3)},
        {time: "Last six months", amountOfImages: this.getDataMonths(6)}]
    }
  }
}
</script>