<template>
  <header class="container-fluid text-center mb-5">
    <div class="row mt-2">
      <div class="row col w-auto h-75 mx-4 my-2 h-50 statusButtonsStyling bg-danger p-2" style="--bs-bg-opacity: .75;">
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
          <h6>Todo:</h6>
          <div>
            <h1>38 </h1>
            <h4>images</h4>
          </div>
        </div>
      </div>
      <div class="row col w-auto h-75 mx-4 my-2 h-50 statusButtonsStyling bg-danger p-2" style="--bs-bg-opacity: .75;">
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
          <h6>On going:</h6>
          <div>
            <h1>12 </h1>
            <h4>images</h4>
          </div>
        </div>
      </div>
      <div class="row col w-auto h-75 mx-4 my-2 h-50 statusButtonsStyling bg-danger p-2" style="--bs-bg-opacity: .95;">
        <div class="col">
          <svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="currentColor"
               class="bi bi-calendar-check my-4 mx-1"
               viewBox="0 0 16 16">
            <path
                d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
            <path
                d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
          </svg>
        </div>
        <div class="col">
          <h6>Finished:</h6>
          <div>
            <h1>64</h1>
            <h4>images</h4>
          </div>
        </div>
      </div>
      <div class="row col w-auto h-75 mx-4 my-2 h-50 statusButtonsStyling bg-danger p-2" style="--bs-bg-opacity: .100;">
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
          <h6>Over Date:</h6>
          <div>
            <h1>64</h1>
            <h4>images</h4>
          </div>
        </div>
      </div>
    </div>
  </header>
  <div class="ms-lg-5">
    <h1>
      Alle Images
    </h1>
    <div class="container-fluid p-3">
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
        <tr v-for="image of images" v-bind:key="image.ean">
          <td v-if="isCorrespondingStatus(image)">{{ image.laptop[0].ean }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.name }}</td>
          <td v-if="isCorrespondingStatus(image)">{{ image.imageMaker }}</td>
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

export default {
  name: "imageStatusOnGoingComponent",
  data() {
    return {
      images: []
    }
  },
  methods: {
    isCorrespondingStatus(image){
      if (image.status.equals("Mee Bezig")){
        return true;
      } else return false;
    }
  },
  created() {
    for (let i in imageData) {
      this.images.push(imageData[i]);
    }
  }
}
</script>

<style scoped>

.statusButtonsStyling {
  height: 100px;
}
</style>
