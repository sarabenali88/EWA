<template>
  <Bar
      id="my-chart-id"
      :options="chartOptions"
      :data="chartData"
  />
</template>

<script>
import imageData from '@/image.json';
import {Bar} from 'vue-chartjs'
import {Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
  name: 'StatisticsPageComponent',
  components: {Bar},

  data() {
    return {
      images: [],
      chartData: {
        labels: [],
        datasets: [{
          label: 'amount of images',
          data: []
        }]
      },
      chartOptions: {
        responsive: true
      }
    }
  },
  created() {

    for (const image of imageData) {
      let data = image.upDateDate.split(' ')[0].split('-'); //now date is ['16', '4', '2017'];
      let newData = new Date(data[2], data[1] - 1, data[0]);
      this.images.push(newData);
    }
    this.chartData.labels = this.getData().map(row => row.time);
    this.chartData.datasets[0].data = this.getData().map(row => row.amountOfImages);


  },
  methods: {
    dateMinusMonths(date, months) {
      date.setMonth(date.getMonth() - months);
      return date;
    },
    getDataOfLastMonth() {
      let amountOfImages = 0;
      for (const image of this.images) {
        if (new Date(image) > this.dateMinusMonths(new Date(), 1)) {
          amountOfImages += 1;
        }
      }
      return amountOfImages;
    },
    getDataOfLastQuarter() {
      let amountOfImages = 0;
      for (const image of this.images) {
        if (image > this.dateMinusMonths(new Date(), 3)) {
          amountOfImages += 1;
        }
      }
      return amountOfImages;
    },

    getDataOfLastHalfYear() {
      let amountOfImages = 0;
      for (const image of this.images) {
        if (image > this.dateMinusMonths(new Date(), 6)) {
          amountOfImages += 1;
        }
      }
      return amountOfImages;
    },
    getData() {
      return [{time: 'laatste maand', amountOfImages: this.getDataOfLastMonth()}, {
        time: 'laatste kwartaal',
        amountOfImages: this.getDataOfLastQuarter()
      }, {time: 'laatste half jaar', amountOfImages: this.getDataOfLastHalfYear()}]
    }
  }
}
</script>