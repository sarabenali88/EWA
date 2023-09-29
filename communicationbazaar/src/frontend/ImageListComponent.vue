<template>
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
      <tr v-for="image of images" v-bind:key = "image.id" >
        <td>{{image.laptop}}</td>
        <td>{{image.laptop}}</td>
        <td>{{image.store}}</td>
        <td>{{image.status}}</td>
        <td>{{image.upDateDate}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {LaptopImage} from "@/models/image";

export default {
  name: "ImageListComponent",
  data() {
    return {
      images: []
    }
  },
  created() {
    for (let i = 0; i < 8; i++) {
      this.images.push(new LaptopImage(
          "Laptop " + [i], "V1.0", "Tilburg", "10 januari 2023", LaptopImage.Status.TODO,
      LaptopImage.Release.NEW, "Er zijn veel problemen", 2, 2023, "Laptop van Sara",
          ""));
    }
  },
}
</script>

<style scoped>

</style>