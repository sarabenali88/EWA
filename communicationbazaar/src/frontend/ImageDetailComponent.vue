<template>
<div>
  <div class="row justify-content-between">
    <div class="col-auto">
      <h3>
        {{imageCopy.laptop[0].brand}} {{imageCopy.laptop[0].description}}
      </h3>
    </div>
    <div class="col-4">
      <button :class="{'hiddenButton': json.some(account => account.loggedIn) === false || json.some(account => account.loggedIn && account.role !== 'admin')}"
                     type="button" class="btn btn-danger m-2">
        Verwijderen
      </button>
      <button :class="{'hiddenButton': json.some(account => account.loggedIn) === false}"
                     type="button" class="btn btn-outline-secondary" @click="onChange()">
        Bewerken
      </button>
    </div>
  </div>
  <div class="row justify-content-md-left">
    <div class="col col-sm-3">
      EAN: {{imageCopy.laptop[0].ean}}
    </div>
    <div class="col-md-auto">
      ART NR: {{imageCopy.laptop[0].articleNumber}}
    </div>
  </div>
  <div class="pt-4 m-sm-1">
    <div class="row justify-content-md-left">
      <div class="col col-sm-2 text-body-tertiary">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
          <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
          <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
        </svg>
        Status:
      </div>
      <div v-if="editComment === false" class="col-sm-auto">
        {{imageCopy.status}}
      </div>
      <div v-else class="col-sm-auto">
        <select class="form-select" v-model="imageCopy.status">
          <option v-for="(value, key) in statuses" :value="value" :key="key">{{ value }}</option>
        </select>
      </div>
    </div>
    <div class="row justify-content-md-left">
      <div class="col col-sm-2 text-body-tertiary">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
          <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
          <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
        </svg>
        Medewerker:
      </div>
      <div v-if="imageCopy.imageMaker !== ''" class="col-sm-auto">
        {{imageCopy.imageMaker}}
      </div>
      <div v-else class="col-sm-auto text-body-secondary" >
        Niet toegewezen
      </div>
    </div>
    <div class="row justify-content-md-left">
      <div class="col col-sm-2 text-body-tertiary">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar3" viewBox="0 0 16 16">
          <path d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zM1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857V3.857z"/>
          <path d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
        </svg>
        Datum:
      </div>
      <div class="col-sm-auto">
        {{imageCopy.upDateDate}}
      </div>
    </div>
  </div>
  <div class="pt-3">
    <ul class="nav nav-underline ms-3" id="myTab" role="tablist">
      <li class="nav-link active" role="option" data-bs-toggle="tab" value="com" @click="setNav('com')">
        Commentaar
      </li>
      <li class="nav-link" role="option" data-bs-toggle="tab" value="desc" @click="setNav('desc')">
        Beschrijving
      </li>
    </ul>
    <div class="tab-content" id="myTabContent">
      <div v-if="showDesc" class="m-2">
        <div v-if="imageCopy.laptop[0].brand !== 'APPLE'" class="row justify-content-sm-left">
          <div class="col col-sm-2 text-body-tertiary">
            OS:
          </div>
          <div class="col-sm-auto">
            {{imageCopy.laptop[0].os}}
          </div>
        </div>
        <div class="row justify-content-sm-left">
          <div class="col col-sm-2 text-body-tertiary">
            Startklaar versie:
          </div>
          <div class="col-sm-auto">
            {{imageCopy.version}}
          </div>
        </div>
        <div class="row justify-content-sm-left">
          <div class="col col-sm-2 text-body-tertiary">
            Nieuw/Update:
          </div>
          <div class="col-sm-auto">
            {{imageCopy.release}}
          </div>
        </div>
        <div class="row justify-content-sm-left">
          <div class="col col-sm-2 text-body-tertiary">
            Locatie:
          </div>
          <div class="col-sm-auto">
            {{imageCopy.store}}
          </div>
        </div>
      </div>
      <div v-else-if="editComment === true && !showDesc" >
        <textarea class="row justify-content-center m-3 p-3" rows="5" cols="115"
                  v-model="imageCopy.comment"></textarea>
        <div class="row justify-content-between">
          <div class="col-auto">
          </div>
          <div class="col-4">
            <button type="button" class="btn btn-outline-secondary m-2" @click="saveChanges()">Opslaan</button>
            <button type="button" class="btn btn-outline-danger" @click="onChange()">Afsluiten</button>
          </div>
        </div>
      </div>
      <div v-else-if="!showDesc">
        <textarea class="row justify-content-center m-3 p-3" rows="5" cols="115" placeholder="Nog geen comments"
                  v-model="imageCopy.comment" readonly></textarea>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import {Image} from "@/models/Image";
import json from "../account.json";

export default {
  name: "ImageDetailComponent",
  props: [
    'currentImage'
  ],
  emits: ['delete-image', 'save-image'],
  created() {
    this.copyImage(this.currentImage);
  },
  watch: {
    currentImage: {
      handler(newImage) {
        if (newImage !== null) {
          this.copyImage(newImage);
        }
      },
      deep: true,
    }
  },
  data(){
    return {
      statuses: Image.Status,
      showDesc: false,
      editComment: false,
      imageCopy: null,
      json: json
    }
  },
  methods: {
    setNav(word){
      if (word === 'com'){
        this.showDesc = false;
      }
      if (word === 'desc'){
        this.showDesc = true;
      }
    },
    onChange(){
      if (this.editComment === true){
        this.editComment = false;
      } else {
        this.editComment = true;
      }
    },
    saveChanges(){
      if (this.imageCopy.status === "Te doen"){
        this.imageCopy.imageMaker = ""
      }
      this.$emit('save-image', this.imageCopy);
      this.editComment = false;
    },
    copyImage(currentImage) {
      this.imageCopy = JSON.parse(JSON.stringify(currentImage));
    },
  }
}
</script>

<style scoped>
.hiddenButton {
  display: none;
}
</style>