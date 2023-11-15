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

  // inject: ["imagesService"],
  data() {
    return {
      images: [],
      chartData: {
        labels: this.getData().map(row => row.time),
        datasets: [{
          label: 'amount of images',
          data: this.getData().map(row => row.amountOfImages)
        }]
      }
      ,
      chartOptions: {
        responsive: true
      }
    }
  },
  created() {
    // this.getImages()

    for(const image in imageData){
      this.images.push(image);
    }

    console.log(this.images)
  },
  methods: {
    // async getImages() {
    //   this.images = await this.imagesService.asyncFindAll();
    // },
    dateMinusMonths(date, months) {
      date.setMonth(date.getMonth() - months);
      return date;
    },
    getDataOfLastMonth() {
      let amountOfImages = 0;
      for (const image in this.images) {
        if (image.upDateDate > this.dateMinusMonths(new Date(), 1)) {
          amountOfImages += 1;
        }
      }
      console.log(amountOfImages)
      return amountOfImages;
    },
    getDataOfLastQuarter() {
      let amountOfImages = 0;
      for (const image in this.images) {
        if (image.upDateDate > this.dateMinusMonths(new Date(), 3)) {
          amountOfImages += 1;
        }
      }
      console.log(amountOfImages)
      return amountOfImages;
    },
    getDataOfLastHalfYear() {
      let amountOfImages = 0;
      for (const image in this.images) {
        if (image.upDateDate > this.dateMinusMonths(new Date(), 6)) {
          amountOfImages += 1;
        }
      }
      console.log(amountOfImages)
      return amountOfImages;
    },
    getData(){
      return [{time : 'laatste maand', amountOfImages: this.getDataOfLastMonth()}, {time : 'laatste kwartaal', amountOfImages: this.getDataOfLastQuarter()}, {time : 'laatste half jaar', amountOfImages: this.getDataOfLastHalfYear()}]
    }
  }
}
</script>