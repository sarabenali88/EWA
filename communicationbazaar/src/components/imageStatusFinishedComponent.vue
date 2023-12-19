<template>
  <h1 class="mx-3">
    {{$t('imageStatus.finishedTitle')}}
  </h1>
  <div class="container-fluid p-3 normal">
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view
          @delete-image="deleteImage()" @save-image="saveImage" v-on:refresh="this.onRefresh()">
        </router-view>
      </div>
    </div>
    <table class="table table-sm">
      <thead>
      <tr>
        <th scope="col">{{ $t('allImages.ean') }}</th>
        <th scope="col">{{ $t('allImages.imageName') }}</th>
        <th scope="col">{{ $t('allImages.employeeName') }}</th>
        <th scope="col">{{ $t('allImages.location') }}</th>
        <th scope="col">{{ $t('allImages.status') }}</th>
        <th scope="col">{{ $t('allImages.date') }}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="image of sortedItems" v-bind:key="image.id" v-on:click="setImage(image)">
        <td v-if="isCorrespondingStatus(image)">{{ image.laptop.ean }}</td>
        <td v-if="isCorrespondingStatus(image)">{{ image.name }}</td>
        <td v-if="isCorrespondingStatus(image)">{{ image.imageMaker.name }}</td>
        <td v-if="isCorrespondingStatus(image)">{{image.store}}</td>
        <td v-if="isCorrespondingStatus(image)"><span :class="getStatusClass(image)">{{image.status}}</span></td>
        <td v-if="isCorrespondingStatus(image)">{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>

  <!-- mobile view -->
  <div class="container-fluid p-3 mobile">
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view>

        </router-view>
      </div>
    </div>
    <table class="table table-sm">
      <thead>
      <tr>
        <th scope="col">EAN</th>
        <th scope="col">Medewerker</th>
        <th scope="col">Status</th>
        <th scope="col">Datum</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="image of images" v-bind:key="image.id" v-on:click="setImage(image)">
        <td v-if="isCorrespondingStatus(image)">{{ image.laptop.ean }}</td>
        <td v-if="isCorrespondingStatus(image)">{{ image.imageMaker.name }}</td>
        <td v-if="isCorrespondingStatus(image)"><span :class="getStatusClass(image)">{{image.status}}</span></td>
        <td v-if="isCorrespondingStatus(image)">{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>

</template>

<script>
import imageDetailComponent from "@/components/ImageDetailComponent";

export default {
  name: "imageStatusFinishedComponent",
  inject: ["imagesService"],
  components: imageDetailComponent,
  data() {
    return {
      images: [],
      selectedImage: null
    }
  },
  async created() {
    this.images = await this.imagesService.asyncFindAll();
    this.selectedImage = this.findSelectedFromRouteParams(this.$route?.params?.id);
  },
  methods: {
    findSelectedFromRouteParams(id) {
      if (id > 0) {
        id = parseInt(id)
        return this.images.find(value => value.id === id);
      }
      return null;
    },
    isCorrespondingStatus(image) {
      if (image.status === "FINISHED") {
        return true;
      } else return false;
    },
    setImage(image) {
      let parentPath = this.$route?.fullPath.replace(new RegExp("/\\d+(/\\d+)?$"), '');
      if (this.selectedImage === image) {
        this.selectedImage = null
        this.$router.push(parentPath);
      } else {
        this.selectedImage = image
        this.$router.push(parentPath + "/" + image.laptop.ean + "/" + image.id);
      }
      console.log(this.selectedImage)
    },
    deleteImage() {
      const index = this.images.indexOf(this.selectedImage);
      this.images.splice(index, 1);
      this.selectedImage = null;
    },
    async onRefresh() {
      this.images = await this.imagesService.asyncFindAll();
      this.selectedImage = this.findSelectedFromRouteParams(this.$route?.params?.id)
    },
    dateConverter(givenDate){
      let date = givenDate.split(' ')[0].split('-'); //now date is ['16', '4', '2017'];
      return new Date(date[2], date[1], date[0]);
    },
    getStatusClass(image) {
      if (image.status === 'FINISHED') {
        return 'badge rounded-pill text-bg-danger';
      } else if (image.status === 'TODO') {
        return 'badge rounded-pill text-bg-danger opacity-25';
      } else if (image.status === 'ONGOING') {
        return 'badge rounded-pill text-bg-danger opacity-50'
      }
      return '';
    }
  },
  computed: {
    sortedItems() {
      // Create a shallow copy of the images array
      let imagesCopy = [...this.images];
      // Sort the copy
      return imagesCopy.sort((a, b) => new Date(this.dateConverter(b.upDateDate)) - new Date(this.dateConverter(a.upDateDate)));
    },
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
