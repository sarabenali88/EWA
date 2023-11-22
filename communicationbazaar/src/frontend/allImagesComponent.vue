<template>
  <h1 class="mx-3">
   {{$t('allImages.titleName')}}
  </h1>
  <div class="container-fluid p-3 overflow-auto normal" >
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view v-bind:currentImage="selectedImage"
          @delete-image="deleteImage()" @save-image="saveImage" @imageAdded="addImageToList">
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
      <tr v-for="image of sortedItems" v-bind:key="image.ean" v-on:click="setImage(image)">
        <td>{{ image.laptop[0].ean }}</td>
        <td>{{ image.name }}</td>
        <td v-if="image.imageMaker !== ''">{{ image.imageMaker }}</td>
        <td v-else class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
        <td>{{ image.store }}</td>
        <td>{{ image.status }}</td>
        <td>{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>

  <!--  mobile view -->
  <div class="container-fluid p-3 overflow-auto mobile" >
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view v-bind:currentImage="selectedImage" >

        </router-view>
      </div>
    </div>
    <table class="table table-sm">
      <thead>
      <tr>
        <th scope="col">{{$t('allImages.ean')}}</th>
        <th scope="col">{{$t('allImages.employeeName')}}</th>
        <th scope="col">{{$t('allImages.status')}}</th>
        <th scope="col">{{$t('allImages.date')}}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="image of sortedItems" v-bind:key="image.ean" v-on:click="setImage(image)">
        <td>{{ image.laptop[0].ean }}</td>
        <td v-if="image.imageMaker !== ''">{{ image.imageMaker }}</td>
        <td v-else class="text-secondary">Niet toegewezen</td>
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
import addImageComponent from "@/frontend/addImageComponent";

export default {
      name: "allImagesComponent",
          components: imageDetailComponent, addImageComponent,
          data() {
        return {
          images: [],
          selectedImage: null,
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
    },
    addImageToList(newImage) {
      console.log('imageAdded ontvangen', newImage);
        this.images.push(newImage);
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

.mobile {
  display: none;
}

@media (max-width: 500px) {
  .mobile {
    display: inherit;
  }

  .normal {
    display: none;
  }
}
</style>