<template>
  <h1 class="mx-3">
    {{$t('imageStatus.impossibleTitle')}}
  </h1>
  <div :class="{'hiddenPage': !this.sessionService._currentToken && this.sessionService._currentAccount === 'ImageMaker' ||
   this.sessionService._currentToken && this.sessionService._currentAccount.role === 'admin'}">
    <h3>{{$t('imageStatus.noAccessMessage')}}</h3>
  </div>
  <div :class="{'hiddenPage': !this.sessionService._currentToken ||
   this.sessionService._currentToken && this.sessionService._currentAccount.role === 'coworker'}">
    <div class="container-fluid p-3 normal">
      <div v-if="selectedImage">
        <div class="card card-body">
          <router-view v-on:refresh="this.onRefresh()">
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
        <tr v-for="image of sortedItems" v-bind:key="image.ean" v-on:click="setImage(image)">
          <td v-if="isCorrespondingStatus(image)">{{ image.laptop.ean }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.name }}</td>
          <td v-if="isCorrespondingStatus(image) && image.imageMaker !== null">{{ image.imageMaker }}</td>
          <td v-else-if="isCorrespondingStatus(image)" class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
          <td v-if="isCorrespondingStatus(image) && image.imageMaker !== null">{{ image.store }}</td>
          <td v-else-if="isCorrespondingStatus(image)" class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
          <td v-if="isCorrespondingStatus(image)"><span :class="getStatusClass(image)">{{ $t(`status.${image.status}`) }}</span></td>
          <td v-if="isCorrespondingStatus(image)">{{ image.upDateDate }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>

  <!-- mobile view -->
  <div class="container-fluid p-3 mobile">
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view v-on:refresh="this.onRefresh()">

        </router-view>
      </div>
    </div>
    <table class="table table-hover table-sm">
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
        <td v-if="isCorrespondingStatus(image)">{{ image.laptop.ean }}</td>
        <td v-if="isCorrespondingStatus(image) && image.imageMaker !== ''">{{ image.imageMaker }}</td>
        <td v-else-if="isCorrespondingStatus(image)" class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
        <td v-if="isCorrespondingStatus(image)"><span :class="getStatusClass(image)">{{ $t(`status.${image.status}`) }}</span></td>
        <td v-if="isCorrespondingStatus(image)">{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>

</template>

<script>
export default {
  name: "ImageStatusImpossibleComponent",
  inject: ["accountsService", "imagesService", "sessionService"],
  data(){
    return{
      images: [],
      selectedImage: null,
      account: null,
      sessionService: this.sessionService
    }
  },
  async created() {
    await this.onRefresh();
    this.account = this.sessionService._currentAccount
  },
  methods: {
    /**
     * A methode that find the corresponding image with the id in the router
     *
     * @param id
     * @returns {null|*}
     * @author Seyma Kaya
     */
    findSelectedFromRouteParams(id) {
      if (id > 0) {
        id = parseInt(id)
        return this.images.find(value => value.id === id);
      }
      return null;
    },
    /**
     * Method so only images with status IMPOSSIBLE get returned
     * @author Rowin Schenk
     * @param image
     * @returns {boolean}
     */
    isCorrespondingStatus(image) {
      return image.status === "IMPOSSIBLE";
    },
    /**
     * A methode that sets the route with the corresponding imageId
     *
     * @param image
     * @author Seyma Kaya
     */
    setImage(image) {
      let parentPath = this.$route?.fullPath.replace(new RegExp("/\\d+(/\\d+)?$"), '');
      if (this.selectedImage === image) {
        this.selectedImage = null
        this.$router.push(parentPath);
      } else {
        this.selectedImage = image
        this.$router.push(parentPath + "/" + image.laptop.ean + "/" + image.id);
      }
    },
    /**
     * A methode that reinitializes the view of images
     *
     * @returns {Promise<void>}
     * @author Seyma Kaya
     */
    async onRefresh() {
      this.images = await this.imagesService.asyncFindAll();
      this.selectedImage = this.findSelectedFromRouteParams(this.$route?.params?.id)
    },
    /**
     * Method to parse date to correct format
     * @author Rowin Schenk
     * @param givenDate
     * @returns {Date}
     */
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

.hiddenPage{
  display: none;
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