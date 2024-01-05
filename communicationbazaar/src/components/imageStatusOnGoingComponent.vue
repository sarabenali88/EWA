<template>
  <h1 class="mx-3">
    {{ $t('imageStatus.ongoingTitle') }}
  </h1>
  <div :class="{'hiddenPage': this.sessionService._currentToken && this.sessionService._currentAccount.role === 'ImageMaker' ||
   this.sessionService._currentToken && this.sessionService._currentAccount.role === 'admin'}">
    <h3>{{ $t('imageStatus.noAccessMessage') }}</h3>
  </div>
  <div :class="{'hiddenPage': !this.sessionService._currentToken ||
   this.sessionService._currentToken && this.sessionService._currentAccount.role !== 'admin'}">
    <div class="container-fluid p-3 normal">
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
        <td v-if="isCorrespondingStatus(image)"><span :class="getStatusClass(image)">{{ $t(`status.${image.status}`) }}</span></td>
        <td v-if="isCorrespondingStatus(image)">{{ image.upDateDate }}</td>
      </tr>
        </tbody>
      </table>
    </div>
  </div>


<!--   mobile view -->
  <div class="container-fluid p-3 mobile">
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view
            @delete-image="deleteImage()" @save-image="saveImage">
        </router-view>
      </div>
    </div>
    <table class="table table-hover table-sm">
      <thead>
      <tr>
        <th scope="col">{{ $t('allImages.ean') }}</th>
        <th scope="col">{{ $t('allImages.employeeName') }}</th>
        <th scope="col">{{ $t('allImages.status') }}</th>
        <th scope="col">{{ $t('allImages.date') }}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="image of sortedItems" v-bind:key="image.id" v-on:click="setImage(image)">
        <td v-if="isCorrespondingStatus(image)">{{ image.laptop.ean }}</td>
        <td v-if="isCorrespondingStatus(image)">{{ image.imageMaker.name }}</td>
        <td v-if="isCorrespondingStatus(image)">{{image.store}}</td>
        <td v-if="isCorrespondingStatus(image)"><span :class="getStatusClass(image)">{{ $t(`status.${image.status}`) }}</span></td>
        <td v-if="isCorrespondingStatus(image)">{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import imageDetailComponent from "@/components/ImageDetailComponent";

export default {
  name: "imageStatusOnGoingComponent",
  inject: ["accountsService", "imagesService", "sessionService"],
  components: imageDetailComponent,
  data() {
    return {
      images: [],
      selectedImage: null,
      accounts: [],
      account: null,
      sessionService: this.sessionService
    }
  },
  async created() {
    this.images = await this.imagesService.asyncFindAll();
    this.accounts = await this.accountsService.asyncFindAll();

    this.account = this.sessionService._currentAccount
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
    /**
     * Method so only images with status ONGOING get returned
     * @author Rowin Schenk
     * @param image
     * @returns {boolean}
     */
    isCorrespondingStatus(image) {
      return image.status === "ONGOING";
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
    saveImage(image) {
      const index = this.images.indexOf(this.selectedImage);
      this.images[index] = image;
      this.setImage(image);
    },
    /**
     * Method to parse date to correct format
     * @author Rowin Schenk
     * @param givenDate
     * @returns {Date}
     */
    dateConverter(givenDate) {
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
      } else if (image.status === 'IMPOSSIBLE'){
        return 'badge rounded-pill text-bg-danger opacity-50';
      }
      return '';
    }
  },
  computed: {
    /**
     * Sorts images by date with most recent date at the top
     * @author Rowin Schenk
     * @returns {*[]}
     */
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

.hiddenPage {
  display: none;
}
</style>
