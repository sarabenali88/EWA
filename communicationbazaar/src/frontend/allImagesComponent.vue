<template>
  <div class="ms-lg-5">
    <h1>
      Alle Images
    </h1>
    <div class="container-fluid p-3">
      <table class="table table-sm">
        <thead>
        <tr>
          <th scope="col">EAN</th>
          <th scope="col">Laptop naam</th>
          <th scope="col">Medewerker</th>
          <th scope="col">Status</th>
          <th scope="col">Datum</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="image of images" v-bind:key="image.ean">
          <td v-if="isCorrespondingStatus(image)">{{ image.laptop[0].ean }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.name }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.imageMaker }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.status }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.upDateDate }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import imageData from '@/image.json';

export default {
  name: "allImagesComponent",
  data() {
    return {
      images: []
    }
  },
  created() {
    for (let i in imageData) {
      this.images.push(imageData[i]);
    }
  }
}
</script>

<style scoped>

.statusButtonsStyling {
  height: 100px;
}
</style>