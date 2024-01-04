<template>
<div v-if="imageCopy">
  <div class="row justify-content-between">
    <div class="col-auto">
      <h3>
        {{imageCopy.laptop.brand}} {{imageCopy.laptop.description}}
      </h3>
    </div>
    <div class="col-4">
      <button :class="{'hiddenButton': !this.sessionService.isAuthenticated() ||
      this.sessionService.isAuthenticated() && this.sessionService._currentAccount.role !== 'admin'}"
                     type="button" class="btn btn-danger m-2" @click="onDelete()">
        {{$t('imageDetail.deleteButton')}}
      </button>
      <button :class="{'hiddenButton': !this.sessionService._currentToken}"
                     type="button" class="btn btn-outline-secondary" @click="onChange()">
        {{$t('imageDetail.editButton')}}
      </button>
    </div>
  </div>
  <div class="row justify-content-md-left">
    <div class="col col-sm-3">
      {{ $t('imageDetail.ean') }}: {{imageCopy.laptop.ean}}
    </div>
    <div class="col-md-auto">
      {{ $t('imageDetail.articleNumber') }}: {{imageCopy.laptop.articleNumber}}
    </div>
  </div>
  <div class="pt-4 m-sm-1">
    <div class="row justify-content-md-left">
      <div class="col col-sm-2 text-body-tertiary">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
          <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
          <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
        </svg>
        {{ $t('imageDetail.status') }}:
      </div>
      <div v-if="editComment === false" class="col-sm-auto">
        {{ $t(`status.${imageCopy.status}`) }}
      </div>
      <div v-else class="col-sm-auto">
        <select class="form-select" v-model="imageCopy.status">
          <option v-for="(value, key) in statuses" :value="key" :key="value">{{ key }}</option>
        </select>
      </div>
    </div>
    <div class="row justify-content-md-left">
      <div class="col col-sm-2 text-body-tertiary">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
          <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
          <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
        </svg>
        {{ $t('imageDetail.employee') }}:
      </div>
      <div v-if="imageCopy.imageMaker !== null" class="col-sm-auto">
        {{imageCopy.imageMaker.name}}
      </div>
      <div v-else-if="imageCopy.imageMaker === null && editComment === true && imageClaimed === false" class="col-sm-auto link-danger text-decoration-underline" @click="claimImage()">
        {{$t('imageDetail.claimButton')}}
      </div>
      <div v-if="imageCopy.imageMaker === null && editComment === false" class="col-sm-auto text-body-secondary" >
        {{$t('imageDetail.unassigned')}}
      </div>
    </div>
    <div class="row justify-content-md-left">
      <div class="col col-sm-2 text-body-tertiary">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar3" viewBox="0 0 16 16">
          <path d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zM1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857V3.857z"/>
          <path d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
        </svg>
        {{ $t('imageDetail.date') }}:
      </div>
      <div class="col-sm-auto">
        {{imageCopy.upDateDate}}
      </div>
    </div>
  </div>
  <div class="pt-3">
    <ul class="nav nav-underline ms-3" id="myTab" role="tablist">
      <li class="nav-link active" role="option" data-bs-toggle="tab" value="com" @click="setNav('com')">
        {{ $t('imageDetail.comment') }}
      </li>
      <li class="nav-link" role="option" data-bs-toggle="tab" value="desc" @click="setNav('desc')">
        {{ $t('imageDetail.description') }}
      </li>
    </ul>
    <div class="tab-content" id="myTabContent">
      <div v-if="showDesc" class="m-2">
        <div v-if="imageCopy.laptop.brand !== 'APPLE'" class="row justify-content-sm-left">
          <div class="col col-sm-2 text-body-tertiary">
            {{ $t('imageDetail.os') }}:
          </div>
          <div class="col-sm-auto">
            {{imageCopy.laptop.os}}
          </div>
        </div>
        <div class="row justify-content-sm-left">
          <div class="col col-sm-2 text-body-tertiary">
            {{ $t('imageDetail.ReadyToUseVersion') }}:
          </div>
          <div class="col-sm-auto">
            {{imageCopy.version}}
          </div>
        </div>
        <div class="row justify-content-sm-left">
          <div class="col col-sm-2 text-body-tertiary">
            {{ $t('imageDetail.newUpdate') }}:
          </div>
          <div class="col-sm-auto">
            {{ $t(`release.${imageCopy.release}`) }}
          </div>
        </div>
        <div class="row justify-content-sm-left">
          <div class="col col-sm-2 text-body-tertiary">
            {{ $t('imageDetail.location') }}:
          </div>
          <div v-if="imageCopy.imageMaker.name !== null" class="col-sm-auto">
            {{imageCopy.store}}
          </div>
        </div>
      </div>
      <div v-else-if="editComment === true && !showDesc" >
        <textarea class="row justify-content-center m-3 p-3 text" rows="5"
                  v-model="imageCopy.comment"
                  :style="{ borderColor: textareaBorderColor }"
        ></textarea>
        <div class="ms-3" v-if="invalidTextarea">{{$t('imageDetail.commentRequired')}}</div>
        <div class="row justify-content-between">
          <div class="col-auto">
          </div>
          <div class="col-4">
            <button type="button" class="btn btn-outline-secondary m-2" @click="saveChanges()">{{$t('imageDetail.saveButton')}}
            </button>
            <button type="button" class="btn btn-outline-danger" @click="onChange()">{{$t('imageDetail.closeButton')}}
            </button>
          </div>
        </div>
      </div>
      <div v-else-if="!showDesc">
      <textarea
          class="row justify-content-center m-3 p-3 text"
          rows="5"
          :placeholder="$t('imageDetail.placeholder')"
          v-model="imageCopy.comment" readonly
          ></textarea>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import {Image} from "@/models/Image";

export default {
  name: "ImageDetailComponent",
  inject: ["accountsService", "imagesService", "sessionService"],
  emits: ['delete-image', 'save-image', 'refresh'],
  data(){
    return {
      statuses: Image.Status,
      showDesc: false,
      editComment: false,
      imageCopy: null,
      accounts: [],
      imageClaimed: false,
      account: null,
      invalidTextarea: false,
      sessionService: this.sessionService
    }
  },
  watch: {
    '$route'(){
      this.reInitialise();
    },

  },
  async created() {
    this.account = !this.sessionService._currentAccount
    this.reInitialise();
  },
  methods: {
    async reInitialise(){
      this.imageCopy =
          await this.imagesService.asyncFindById(this.$route?.params?.id)
    },
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
    async onDelete(){
      await this.imagesService.asyncDeleteById(this.imageCopy.id)
      this.$emit('refresh')
    },
    async saveChanges(){
      if (this.imageCopy.status !== Object.keys(Image.Status)[0] && this.imageCopy.imageMaker === null){
        this.imageCopy.imageMaker = this.sessionService._currentAccount
        this.imageCopy.store = this.sessionService._currentAccount.location;
      }
      if (this.imageCopy.status === Object.keys(Image.Status)[0] ||
          this.imageCopy.status === Object.keys(Image.Status)[3]){
        this.imageCopy.imageMaker = null
        this.imageCopy.store = null
      }
      if (this.imageCopy.status === Object.keys(Image.Status)[3]) {
        if (this.imageCopy.comment === null) {
          this.invalidTextarea = true;
          return false;
        } else {
          this.invalidTextarea = false;
        }
      }
      this.editComment = false;
      this.imageClaimed = false;

      await this.imagesService.asyncSave(this.imageCopy);
      this.$emit('refresh')

    },
    claimImage(){
      this.imageClaimed = true;
      this.imageCopy.imageMaker = this.sessionService._currentAccount;
      this.imageCopy.status = Object.keys(Image.Status)[1];
      this.imageCopy.store = this.sessionService._currentAccount.location;
    }
  },
  computed: {
    textareaBorderColor() {
      return this.invalidTextarea ? 'red' : '';
    },
  }
}
</script>

<style scoped>
.hiddenButton {
  display: none;
}
.text {
  width: 90%;

}

</style>