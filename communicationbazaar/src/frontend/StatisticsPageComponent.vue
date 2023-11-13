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
        labels: ['laatste maand', 'laatste kwartaal', 'laatste half jaar'],
        datasets: [{data: this.getData()}]
      }
      ,
      chartOptions: {
        responsive: true
      }
    }
  },
  created() {
    for (let i in imageData) {
      this.images.push(imageData[i]);
    }
  },
  methods: {
    dateMinusMonths(date, months) {
      date.setMonth(date.getMonth() - months);
      return date;
    },
    getDataOfLastMonth() {
      let amountOfImages = 0;
      for (const image in this.images) {
        if (image.upDateDate < this.dateMinusMonths(Date.now(), 1)) {
          amountOfImages += 1;
        }
        return amountOfImages;
      }
    },
    getDataOfLastQuarter() {
      let amountOfImages = 0;
      for (const image in this.images) {
        if (image.upDateDate < this.dateMinusMonths(Date.now(), 3)) {
          amountOfImages += 1;
        }
        return amountOfImages;
      }
    },
    getDataOfLastHalfYear() {
      let amountOfImages = 0;
      for (const image in this.images) {
        if (image.upDateDate < this.dateMinusMonths(Date.now(), 6)) {
          amountOfImages += 1;
        }
        return amountOfImages;
      }
    },
    getData(){
      return [this.getDataOfLastMonth(), this.getDataOfLastQuarter(), this.getDataOfLastHalfYear() ]
    }
  }
}
</script>