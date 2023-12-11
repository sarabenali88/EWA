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

    dateMinusMonths(date, months) {
      let newDate = new Date(date);
      newDate.setMonth(newDate.getMonth() - months);
      return newDate;
    },

    getDataMonths(months) {
      const comparisonDate = this.dateMinusMonths(new Date(), months);
      return this.images.filter(image => {
        const imageDate = this.dateConverter(image.upDateDate);
        return imageDate >= comparisonDate;
      }).length;
    },

    getData() {
      return [{time: 'Laatste maand', amountOfImages: this.getDataMonths(1)},
        {time: 'Laatste kwartaal', amountOfImages: this.getDataMonths(3)},
        {time: 'Laatste half jaar', amountOfImages: this.getDataMonths(6)}]
    }
  }
}
</script>