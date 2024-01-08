<template>
  <header class="container-fluid text-center mb-5 normal">
    <div class="row mt-2">
      <div class="row col w-auto mx-4 my-2 h-50 statusButtonsStyling bg-danger p-2 rounded" style="--bs-bg-opacity: .65;"
           :class="{'active-tab': $route.path === '/imageListRoute/statusTodo'}"
           @click="getSelectedStatus(this.todoStatus)">
        <div class="col">
          <svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="currentColor"
               class="bi bi-card-checklist my-4"
               viewBox="0 0 16 16">
            <path
                d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
            <path
                d="M7 5.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0zM7 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"/>
          </svg>
        </div>
        <div class="col">
          <h6>{{ $t('imageStatus.statusToDo') }}:</h6>
          <div>
            <h1>{{ amountOfImagesToDo }}</h1>
            <h4>images</h4>
          </div>
        </div>
      </div>
      <div class="row col w-auto mx-4 my-2 h-50 statusButtonsStyling bg-danger p-2 rounded" style="--bs-bg-opacity: .75;"
           :class="{'active-tab': $route.path === '/imageListRoute/statusOnGoing'}"
           @click="getSelectedStatus(this.onGoingStatus)">
        <div class="col">
          <svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="currentColor"
               class="bi bi-arrow-repeat my-4 mx-1"
               viewBox="0 0 16 16">
            <path
                d="M11.534 7h3.932a.25.25 0 0 1 .192.41l-1.966 2.36a.25.25 0 0 1-.384 0l-1.966-2.36a.25.25 0 0 1 .192-.41zm-11 2h3.932a.25.25 0 0 0 .192-.41L2.692 6.23a.25.25 0 0 0-.384 0L.342 8.59A.25.25 0 0 0 .534 9z"/>
            <path fill-rule="evenodd"
                  d="M8 3c-1.552 0-2.94.707-3.857 1.818a.5.5 0 1 1-.771-.636A6.002 6.002 0 0 1 13.917 7H12.9A5.002 5.002 0 0 0 8 3zM3.1 9a5.002 5.002 0 0 0 8.757 2.182.5.5 0 1 1 .771.636A6.002 6.002 0 0 1 2.083 9H3.1z"/>
          </svg>
        </div>
        <div class="col">
          <h6>{{ $t('imageStatus.statusOngoing') }}:</h6>
          <div>
            <h1>{{ amountOfImagesOnGoing }}</h1>
            <h4>images</h4>
          </div>
        </div>
      </div>
      <div class="row col w-auto mx-4 my-2 h-50 statusButtonsStyling bg-danger p-2 rounded" style="--bs-bg-opacity: .95;"
           :class="{'active-tab': $route.path === '/imageListRoute/statusImpossible'}"
           @click="getSelectedStatus(this.impossibleStatus)">
        <div class="col">
          <svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="currentColor"
               class="bi bi-calendar-check my-4 mx-1"
               viewBox="0 0 16 16">
            <path d="M5.5 9.5A.5.5 0 0 1 6 9h4a.5.5 0 0 1 0 1H6a.5.5 0 0 1-.5-.5"/>
            <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
          </svg>
        </div>
        <div class="col">
          <h6>{{ $t('imageStatus.statusImpossible') }}:</h6>
          <div>
            <h1>{{ amountOfImagesImpossible }}</h1>
            <h4>images</h4>
          </div>
        </div>
      </div>
      <div class="row col w-auto mx-4 my-2 h-50 statusButtonsStyling bg-danger p-2 overdate rounded" style="--bs-bg-opacity: .100;"
           :class="{'active-tab': $route.path === '/imageListRoute/statusOverDate'}"
           @click="getSelectedStatus(this.overDateStatus)">
        <div class="col">
          <svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="currentColor"
               class="bi bi-calendar-x my-4 mx-1"
               viewBox="0 0 16 16">
            <path
                d="M6.146 7.146a.5.5 0 0 1 .708 0L8 8.293l1.146-1.147a.5.5 0 1 1 .708.708L8.707 9l1.147 1.146a.5.5 0 0 1-.708.708L8 9.707l-1.146 1.147a.5.5 0 0 1-.708-.708L7.293 9 6.146 7.854a.5.5 0 0 1 0-.708z"/>
            <path
                d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
          </svg>
        </div>
        <div class="col">
          <h6>{{ $t('imageStatus.statusOverDate') }}:</h6>
          <div>
            <h1>{{ amountOfImagesOverDate }}</h1>
            <h4>images</h4>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- mobile view -->
  <header class="container-fluid text-center mb-1 mobile">
    <div class="col">
      <div class="row mx-2 my-2 statusButtonsStyling bg-danger p-2 rounded" style="--bs-bg-opacity: .55;"
           :class="{'active-tab': $route.path === '/imageListRoute/statusTodo'}"
           @click="getSelectedStatus(this.todoStatus)">
        <div class="col">
          <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor"
               class="bi bi-card-checklist"
               viewBox="0 0 16 16">
            <path
                d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
            <path
                d="M7 5.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0zM7 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"/>
          </svg>
        </div>
        <div class="col items">
          <h6 class="py-2">{{ $t('imageStatus.statusToDo') }}:</h6>
          <h1 class="px-2 py-0">{{ amountOfImagesToDo }}</h1>
          <h4 class="px-2 py-1">Images</h4>
        </div>
      </div>
      <div class="row mx-2 my-2 statusButtonsStyling bg-danger p-2 rounded" style="--bs-bg-opacity: .75;"
           :class="{'active-tab': $route.path === '/imageListRoute/statusOnGoing'}"
           @click="getSelectedStatus(this.onGoingStatus)">
        <div class="col">
          <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor"
               class="bi bi-arrow-repeat"
               viewBox="0 0 16 16">
            <path
                d="M11.534 7h3.932a.25.25 0 0 1 .192.41l-1.966 2.36a.25.25 0 0 1-.384 0l-1.966-2.36a.25.25 0 0 1 .192-.41zm-11 2h3.932a.25.25 0 0 0 .192-.41L2.692 6.23a.25.25 0 0 0-.384 0L.342 8.59A.25.25 0 0 0 .534 9z"/>
            <path fill-rule="evenodd"
                  d="M8 3c-1.552 0-2.94.707-3.857 1.818a.5.5 0 1 1-.771-.636A6.002 6.002 0 0 1 13.917 7H12.9A5.002 5.002 0 0 0 8 3zM3.1 9a5.002 5.002 0 0 0 8.757 2.182.5.5 0 1 1 .771.636A6.002 6.002 0 0 1 2.083 9H3.1z"/>
          </svg>
        </div>
        <div class="col items">
          <h6 class="py-2 ">{{ $t('imageStatus.statusOngoing') }}:</h6>
            <h1 class="px-2 py-0">{{ amountOfImagesOnGoing }}</h1>
            <h4 class="px-2 py-1">Images</h4>
        </div>
      </div>
      <div class="row mx-2 my-2 statusButtonsStyling bg-danger p-2 rounded" style="--bs-bg-opacity: .95;"
           :class="{'active-tab': $route.path === '/imageListRoute/statusImpossible'}"
           @click="getSelectedStatus(this.impossibleStatus)">
        <div class="col">
          <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor"
               class="bi bi-calendar-check"
               viewBox="0 0 16 16">
            <path d="M5.5 9.5A.5.5 0 0 1 6 9h4a.5.5 0 0 1 0 1H6a.5.5 0 0 1-.5-.5"/>
            <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
          </svg>
        </div>
        <div class="col items">
          <h6 class="py-2 ">{{ $t('imageStatus.statusImpossible') }}::</h6>
          <h1 class="px-2 py-0">{{ amountOfImagesImpossible }}</h1>
          <h4 class="px-2 py-1">Images</h4>
        </div>
      </div>
    </div>
  </header>


  <div class="ms-lg-5">
    <router-view
    v-on:refresh="this.onRefresh()">

    </router-view>
    <button type="button" class="btn btn-danger" @click="$router.push('/addImage')"
            :class="{'hiddenButton': !this.sessionService._currentToken}">
      {{$t('addImage.buttonAdd')}}
    </button>
  </div>
</template>

<script>
export default {
  name: "ImageListComponent",
  inject: ["accountsService", "imagesService", "sessionService"],
  emits: ["refresh"],
  async created() {
    this.$router.push("/imageListRoute/allImages");
    await this.onRefresh();
  },
  data() {
    return {
      images: [],
      amountOfImagesToDo: 0,
      amountOfImagesOnGoing: 0,
      amountOfImagesImpossible: 0,
      amountOfImagesFinished: 0,
      amountOfImagesOverDate: 0,
      todoStatus: "todoStatus",
      onGoingStatus: "onGoingStatus",
      finishedStatus: "finishedStatus",
      impossibleStatus: "impossibleStatus",
      overDateStatus: "overDateStatus",
      allImagesStatus: "allImages",
      selectedStatus: this.allImagesStatus,
      account: null,
      sessionService: this.sessionService
    }
  },
  methods: {
    async onRefresh(){
      this.images = await this.imagesService.asyncFindAll();
      this.accounts = await this.accountsService.asyncFindAll();
      this.account = this.sessionService._currentAccount;

      this.amountOfImages();
    },
    getSelectedStatus(status) {
      if (this.selectedStatus === status) {
        this.$router.push("/imageListRoute/allImages");
        this.selectedStatus = this.allImagesStatus;
      } else {

        if (status === this.todoStatus) {
          this.$router.push("/imageListRoute/statusTodo");
          this.selectedStatus = this.todoStatus;
        }
        if (status === this.onGoingStatus) {
          this.$router.push("/imageListRoute/statusOnGoing");
          this.selectedStatus = this.onGoingStatus;
        }
        if (status === this.impossibleStatus){
          this.$router.push("/imageListRoute/statusImpossible");
          this.selectedStatus = this.impossibleStatus;
        }
        if (status === this.finishedStatus) {
          this.$router.push("/imageListRoute/statusFinished");
          this.selectedStatus = this.finishedStatus;
        }
        if (status === this.overDateStatus) {
          this.$router.push("/imageListRoute/statusOverDate");
          this.selectedStatus = this.overDateStatus;
        }
      }
    },
    amountOfImages() {
      for (const image of this.images) {
        if (image.status === "TODO") {
          this.amountOfImagesToDo++;
        }
        if (image.status === "ONGOING") {
          this.amountOfImagesOnGoing++;
        }
        if (image.status === "IMPOSSIBLE"){
          this.amountOfImagesImpossible++;
        }
        if (image.status === "FINISHED") {
          this.amountOfImagesFinished++;
        }
        if (image.status === "OVERDATE") {
          this.amountOfImagesOverDate++;
        }
      }
    },
  }
}
</script>

<style scoped>

.statusButtonsStyling {
  height: 55px;
}

.active-tab {
  border: solid black 1px;
}

.mobile {
  display: none;
}

@media (max-width: 500px) {
  .overdate {
    display: none;
  }

  .normal {
    display: none;
  }

  .mobile {
    display: block;
  }

  .items {
    display: flex;
  }
  h6 {
    width: 100px;
  }
}
.hiddenButton {
  display: none;
}

</style>