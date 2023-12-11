<template>
  <Bar
      id="my-chart-id"
      :options="chartOptions"
      v-if="loaded" :data="chartData"
  />
</template>

<script>
import imageData from '@/image.json';
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
    for (let i in imageData) {
      this.images.push(imageData[i]);
    }

    // Make labals and data for the chart
    this.chartData.labels = this.getData().map(row => row.time);
    this.chartData.datasets[0].data = this.getData().map(row => row.amountOfImages);

    // set loaded to true so the chart can be shown
    this.loaded = true
  },
  methods: {

    // convert date to a date object
    dateConverter(date) {
      let data = date.split(' ')[0].split('-'); //now date is ['16', '4', '2017'];
      return new Date(data[2], data[1] - 1, data[0]);
    },
    dateMinusMonths(date, months) {
      date.setMonth(date.getMonth() - months);
      return date;
    },

    getDataMonths(months) {
      let amountOfImages = 0;
      for (const image of this.images) {
        let correctDate = this.dateConverter(image.upDateDate)
        if (correctDate > this.dateMinusMonths(new Date(), months)) {
          amountOfImages += 1;
        }
      }
      return amountOfImages;
    },
    getData() {
      return [{time: 'Laatste maand', amountOfImages: this.getDataMonths(1)},
        {time: 'Laatste kwartaal', amountOfImages: this.getDataMonths(3)},
        {time: 'Laatste half jaar', amountOfImages: this.getDataMonths(6)}]
    }
  }
}
</script>