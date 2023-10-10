<template>
  <div class="ms-lg-5">
    <h1>
      Status Finished Images
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
          <th scope="col">EAN</th>
          <th scope="col">Laptop naam</th>
          <th scope="col">Medewerker</th>
          <th scope="col">Status</th>
          <th scope="col">Datum</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="image of images" v-bind:key="image.ean" v-on:click="setImage(image)">
          <td v-if="isCorrespondingStatus(image)">{{ image.laptop[0].ean }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.name }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.imageMaker }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.status }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.upDateDate }}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <button type="button" class="btn btn-danger" @click="$router.push('/addImage')">
      add Image
    </button>
  </div>
</template>

<script>
import imageData from '@/image.json';
import imageDetailComponent from "@/frontend/ImageDetailComponent";

export default {
  name: "imageStatusFinishedComponent",
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
    isCorrespondingStatus(image){
      if (image.status === "Afgerond"){
        return true;
      } else return false;
    },
    setImage(image) {
      let parentPath = this.$route?.fullPath.replace(new RegExp("/\\d*$"),'');
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
