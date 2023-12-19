<template>
  <div class="container">
    <div class="row mt-5 border border-secondary rounded py-4 profile">
      <div class="col-md-2 how-img">
        <img src="../assets/Portrait_Placeholder.png" class="rounded-circle img-fluid size_picture border border-danger border-3"
             alt=""/>
      </div>
      <div class="col-md-8 py-5">
          <div class="card-body">
            <div class="row">
              <div class="col-sm-3">
                <h3 class="mb-0">
                  {{ name }}
                </h3>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Functie</h6>
              </div>
              <div class="col-sm-9">
                {{ role }}
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Werknemer nr.</h6>
              </div>
              <div class="col-sm-9">
                {{ personalNumber }}
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Locatie</h6>
              </div>
              <div class="col-sm-9">
                {{ location }}
              </div>
            </div>
          </div>
      </div>
    </div>
    <div v-if="selectedImage">
      <div class="card card-body">
        <router-view
            @delete-image="deleteImage()" @save-image="saveImage" v-on:refresh="this.onRefresh()">
        </router-view>
      </div>
    </div>
    <table class="table table-sm normal">
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
      <tr v-for="image of sortedItems" v-bind:key="image.id"  v-on:click="setImage(image)">
        <td>{{ image.laptop.ean }}</td>
        <td>{{ image.name }}</td>
        <td v-if="image.imageMaker !== null ">{{ image.imageMaker.name }}</td>
        <td v-else class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
        <td v-if="image.imageMaker !== null">{{ image.store }}</td>
        <td v-else class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
        <td><span :class="getStatusClass(image)">{{image.status}}</span></td>
        <!--        <td>{{ $t(`status.${image.status}`) }}</td>-->
        <td>{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>

    <table class="table table-sm mobile">
      <thead>
      <tr>
        <th scope="col">{{$t('allImages.ean')}}</th>
        <th scope="col">{{$t('allImages.employeeName')}}</th>
        <th scope="col">{{$t('allImages.status')}}</th>
        <th scope="col">{{$t('allImages.date')}}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="image of sortedItems" v-bind:key="image.id"  v-on:click="setImage(image)">
        <td>{{ image.laptop.ean }}</td>
        <td v-if="image.imageMaker !== null ">{{ image.imageMaker.name }}</td>
        <td v-else class="text-secondary">{{$t('imageDetail.unassigned')}}</td>
        <td><span :class="getStatusClass(image)">{{image.status}}</span></td>
        <!--        <td>{{ $t(`status.${image.status}`) }}</td>-->
        <td>{{ image.upDateDate }}</td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
import NavBar from "@/components/NavBarComponent";

export default {
  name: "ProfilePageComponent",

  inject: ["accountsService", "imagesService"],
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.createInformation();
    });
  },
  data() {
    return {
      personalNumber: '',
      name: '',
      email: '',
      role: '',
      location: '',
      imagesOnGoing: [],
      imagesDone: [],
      accounts: [],
      loggedInAccount: undefined,
      images: [],
      selectedImage: null
    }
  },
  async created() {
    this.createInformation();
  },
  watch: {
    loggedInAccount: {
      handler: function (newVal, oldVal) {
        if (newVal !== oldVal) {
          this.setInformation();
        }
      },
      deep: true
    }
  },
  methods: {
    async createInformation() {
      this.accounts = await this.accountsService.asyncFindAll();
      this.loggedInAccount = this.accounts.find(account => account.loggedIn);
      if (!this.loggedInAccount || !this.loggedInAccount.loggedIn) {
        this.$router.push(NavBar.data().signInRoute);
      } else {
        this.setInformation();
      }
      this.images = await this.accountsService.asyncGetImagesFromAccount(this.loggedInAccount.personalNumber)
    },
    setInformation() {
      this.personalNumber = this.loggedInAccount.personalNumber;
      this.name = this.loggedInAccount.name;
      this.email = this.loggedInAccount.email;
      this.role = this.loggedInAccount.role;
      this.imagesOnGoing = this.loggedInAccount.imagesOnGoing;
      this.imagesDone = this.loggedInAccount.imagesDone;
      this.location = this.loggedInAccount.location;

    },
    dateConverter(givenDate) {
      let date = givenDate.split(' ')[0].split('-'); //now date is ['16', '4', '2017'];
      return new Date(date[2], date[1], date[0]);
    },
    deleteImage() {
      const index = this.images.indexOf(this.selectedImage);
      this.images.splice(index, 1);
      this.selectedImage = null;
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
    async onRefresh() {
      this.images = await this.accountsService.asyncGetImagesFromAccount(this.loggedInAccount.personalNumber);
      this.selectedImage = this.findSelectedFromRouteParams(this.$route?.params?.id)
    },
    findSelectedFromRouteParams(id) {
      if (id > 0) {
        id = parseInt(id)
        return this.images.find(value => value.id === id);
      }
      return null;
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
    }
  }
}
</script>

<style scoped>
.border-3 {
  border-width: 3px !important;
}

.container {
  width: 90%;
}

.profile {
  margin-bottom: 20px;
}

.mobile {
  display: none;
}


@media (max-width: 700px) {
  .container {
    width: 90%;
  }

  .mobile {
    display: inherit;
  }

  .normal {
    display: none;
  }
}

h5 {
  color: lightgray;
}

</style>
