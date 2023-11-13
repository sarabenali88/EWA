<template>
  <h1>
    Status Todo Images
  </h1>
  <div :class="{'hiddenPage': json.some(account => account.loggedIn && account.role === 'ImageMaker') ||
   json.some(account => account.loggedIn && account.role === 'admin')}">
    <h3>U heeft niet de bevoegdheden om deze data te zien</h3>
  </div>
  <div :class="{'hiddenPage': json.some(account => account.loggedIn) === false ||
   json.some(account => account.loggedIn && account.role === 'coworker')}">
    <div class="container-fluid p-3">
      <div v-if="selectedImage">
        <div class="card card-body">
          <router-view v-bind:currentImage="selectedImage"
                       @delete-image="deleteImage()" @save-image="saveImage">

          </router-view>
        </div>
      </div>
      <table class="table table-sm">
        <thead>
        <tr>
          <th scope="col">EAN</th>
          <th scope="col">Laptop naam</th>
          <th scope="col">Medewerker</th>
          <th scope="col">Vestiging</th>
          <th scope="col">Status</th>
          <th scope="col">Datum</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="image of sortedItems" v-bind:key="image.ean" v-on:click="setImage(image)">
          <td v-if="isCorrespondingStatus(image)">{{ image.laptop[0].ean }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.name }}</td>
          <td v-if="isCorrespondingStatus(image) && image.imageMaker !== ''">{{ image.imageMaker }}</td>
          <td v-else-if="isCorrespondingStatus(image)" class="text-secondary">Niet toegewezen</td>
          <td v-if="isCorrespondingStatus(image)">{{image.store}}</td>
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
import imageDetailComponent from "@/frontend/ImageDetailComponent";
import accountData from '@/account.json';

export default {
  name: "imageStatusTodoComponent",
  components: imageDetailComponent,
  data() {
    return {
      images: [],
      selectedImage: null,
      json : accountData
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
    isCorrespondingStatus(image) {
      if (image.status === "Te doen") {
        return true;
      } else return false;
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
    },
    deleteImage() {
      const index = this.images.indexOf(this.selectedImage);
      this.images.splice(index, 1);
      this.selectedImage = null;
    },
    saveImage(image){
      const index = this.images.indexOf(this.selectedImage);
      this.images[index] = image;
      this.setImage(image);
    },
    dateConverter(givenDate){
      let date = givenDate.split(' ')[0].split('-'); //now date is ['16', '4', '2017'];
      return new Date(date[2], date[1], date[0]);
    }
  },
  computed: {
    sortedItems() {
      // Create a shallow copy of the images array
      let imagesCopy = [...this.images];
      // Sort the copy
      return imagesCopy.sort((a, b) => new Date(this.dateConverter(b.upDateDate)) - new Date(this.dateConverter(a.upDateDate)));
    }
  }
}
</script>

<style scoped>

.statusButtonsStyling {
  height: 100px;
}

.hiddenPage{
  display: none;
}
</style>