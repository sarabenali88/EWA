<template>
  <h1>
   {{$t('allImages.titleName')}}
  </h1>
  <div class="container-fluid p-3">
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view v-bind:currentImage="selectedImage">

        </router-view>
      </div>
    </div>
    <table class="table table-sm">
      <thead>
      <tr>
        <th scope="col">{{$t('allImages.ean')}}</th>
        <th scope="col">{{$t('allImages.imageName')}}</th>
        <th scope="col">{{$t('allImages.employeeName')}}</th>
        <th scope="col">{{$t('allImages.location')}}</th>
        <th scope="col">{{$t('allImages.status')}}</th>
        <th scope="col">{{$t('allImages.date')}}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="image of images" v-bind:key="image.ean" v-on:click="setImage(image)">
        <td>{{ image.laptop[0].ean }}</td>
        <td>{{ image.name }}</td>
        <td>{{ image.imageMaker }}</td>
        <td>{{image.store}}</td>
        <td>{{ image.status }}</td>
        <td>{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import imageData from '@/image.json';
import imageDetailComponent from "@/frontend/ImageDetailComponent";

export default {
  name: "allImagesComponent",
  components: imageDetailComponent,
  data() {
    return {
      images: [],
      selectedImage: null
    }
  },
  created() {
    for (let i in imageData) {
      this.images.push(imageData[i]);
    }

    this.selectedImage = this.findSelectedFromRouteParams(this.$route?.params?.id);
  },
  methods: {
    findSelectedFromRouteParams(id) {
      if (id > 0) {
        id = parseInt(id)
        return this.images.find(value => value.laptop[0].ean === id);
      }
      return null;
    },
    setImage(image) {
      let parentPath = this.$route?.fullPath.replace(new RegExp("/\\d*$"), '');
      if (this.selectedImage === image) {
        this.selectedImage = null
        this.$router.push(parentPath);
      } else {
        this.selectedImage = image
        this.$router.push(parentPath + "/" + image.laptop[0].ean);
      }
      console.log(this.selectedImage)
    }
  }
}
</script>

<style scoped>

.statusButtonsStyling {
  height: 100px;
}
</style>