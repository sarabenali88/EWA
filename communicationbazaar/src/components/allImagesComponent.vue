<template>
  <h1 class="mx-3">
    {{ $t('allImages.titleName') }}
  </h1>
  <div class="container-fluid p-3 overflow-auto normal">
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view
          @delete-image="deleteImage()" @save-image="saveImage" v-on:refresh="this.onRefresh()">
        </router-view>
      </div>
    </div>
    <table class="table table-hover table-sm">
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
      <tr v-for="image of sortedItems" v-bind:key="image.id" v-on:click="setImage(image)">
        <td>{{ image.laptop.ean }}</td>
        <td>{{ image.name }}</td>
        <td v-if="image.imageMaker !== null ">{{ image.imageMaker.name }}</td>
        <td v-else class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
        <td v-if="image.imageMaker !== null">{{ image.store }}</td>
        <td v-else class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
        <td><span :class="getStatusClass(image)">{{ $t(`status.${image.status}`) }}</span></td>
        <td>{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>

  <!--  mobile view -->
  <div class="container-fluid p-3 overflow-auto mobile">
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view>

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
      <tr v-for="image of sortedItems" v-bind:key="image.id" v-on:click="setImage(image)">
        <td>{{ image.laptop.ean }}</td>
        <td v-if="image.imageMaker !== null">{{ image.imageMaker.name }}</td>
        <td v-else class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
        <td><span :class="getStatusClass(image)">{{image.status}}</span></td>
        <td>{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>

</template>

<script>
import imageDetailComponent from "@/components/ImageDetailComponent";
import { barcode } from './HeaderComponent.vue'

export default {
  name: "allImagesComponent",
  inject: ["imagesService"],
  components: imageDetailComponent,
  data() {
    return {
      images: [],
      selectedImage: null,
      barcode
    }
  },
  async created() {
    await this.onRefresh()
  },
  methods: {
    findSelectedFromRouteParams(id) {
      if (id > 0) {
        id = parseInt(id)
        return this.images.find(value => value.id === id);
      }
      return null;
    },
    setImage(image) {
      let parentPath = this.$route?.fullPath.replace(new RegExp("/\\d+(/\\d+)?$"), '');
      if (this.selectedImage === image) {
        this.$router.push(parentPath);
        this.selectedImage = null;
      } else {
        this.$router.push(parentPath + "/" + image.laptop.ean + "/" + image.id);
        this.selectedImage = image;
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
      this.images = this.images.filter(image => image.status !== 'IMPOSSIBLE');
      this.selectedImage = this.findSelectedFromRouteParams(this.$route?.params?.id)
    },

    dateConverter(givenDate) {
      let date = givenDate.split(' ')[0].split('-'); //now date is ['16', '4', '2017'];
      return new Date(date[2], date[1], date[0]);
    },
    selectImageByEAN(ean) {
      console.log('The EAN value: ', ean)
      const image = this.images.find(image => image.laptop.ean == ean)
      console.log(image)
      if (image) {
        this.setImage(image)
      }
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
  watch: {
    barcode: {
      handler(code) {
        if (code) {
          this.selectImageByEAN(code)
        }
      }
    },
    '$route'(){
      this.selectedImage = this.findSelectedFromRouteParams(this.$route?.params?.id);
    }
  },
  computed: {
    sortedItems() {
      // Create a shallow copy of the images array
      // Sort the copy
      let imagesCopy = [...this.images];
      try {
        return imagesCopy.sort((a, b) => new Date(this.dateConverter(b.upDateDate)) - new Date(this.dateConverter(a.upDateDate)));
      } catch (e){
        return console.log(e + "occured");
      }
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
