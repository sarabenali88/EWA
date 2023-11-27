<template>
  <div class="container mt-5">
    <div class="row mb-3">
      <label class="col-3" for="ean">{{ $t('addImage.eanNumber') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="number" class="form-control" v-model.number="ean" @blur="validateInput"/>
        </div>
        <div class="error" v-if="invalid === true && ean === null">{{ $t('addImage.alertEmpty') }}</div>
        <div class="error" v-if="invalidEan === true && ean < 0">{{ $t('addImage.alertEan') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="start">{{ $t('addImage.ReadyToUseVersion') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="text" class="form-control" v-model.trim="startVersion"/>
        </div>
        <div class="error" v-if="invalid === true && startVersion === ''">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="image">{{ $t('addImage.imageName') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="text" class="form-control" v-model.trim="imageName"/>
        </div>
        <div class="error" v-if="invalid === true && imageName === ''">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="location">{{ $t('addImage.imageLocation') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="text" class="form-control" v-model.trim="locationImage"/>
        </div>
        <div class="error" v-if="invalid === true && locationImage === ''">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="status">{{ $t('addImage.status') }}</label>
      <div class="col-5">
        <div class="input-group">
          <select class="form-control" v-model="statusSelect">
            <option value="TODO">{{ $t('addImage.statusToDo') }}</option>
            <option value="ONGOING">{{ $t('addImage.statusOngoing') }}</option>
            <option value="FINISHED">{{ $t('addImage.statusFinished') }}</option>
          </select>
        </div>
        <div class="error" v-if="invalid === true && statusSelect === ''">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="date">{{ $t('addImage.date') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input :min="getToday()" type="date" class="form-control" v-model="date"/>
        </div>
        <div class="error" v-if="invalid === true && date === ''">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="week">{{ $t('addImage.week') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="week" class="form-control" v-model="week" :min="minWeek"/>
        </div>
        <div class="error" v-if="invalid === true && week === ''">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row">
      <div class="col-12">
        <button class="btn btn-danger w-25" @click="validateInput">{{ $t('addImage.buttonSave') }}</button>
      </div>
    </div>
  </div>
</template>

<script>
import {Image} from "@/models/Image";
export default {
  name: "addImageComponent",
  inject: ["imagesService"],
  data() {
    return {
      ean: null,
      startVersion: '',
      imageName: '',
      locationImage: '',
      statusSelect: '',
      date: '',
      week: '',
      invalid: null,
      invalidEan: null,
      image: null,
      formattedDate: null,
    }
  },
  methods: {
    async validateInput() {
      if (this.ean === '' || this.startVersion === '' || this.imageName === '' || this.locationImage === '' ||
          this.statusSelect === '' || this.date === '' || this.week === '') {
        this.invalid = true;
      } else {
        this.invalid = '';
      }
      if (this.ean < 0) {
        this.invalidEan = true;
      } else {
        this.invalidEan = '';
      }
      if (this.invalid === '' && this.invalidEan === '') {
         await this.saveImage();
      }
    },
    formatDate(inputDate) {
      const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
      return new Date(inputDate).toLocaleDateString('nl-NL', options);
    },
    async saveImage() {
       this.formattedDate = this.formatDate(this.date);
       this.image =  new Image(this.ean, this.startVersion, this.locationImage,
            this.formattedDate, this.statusSelect, null, null, null, null,
           this.imageName, null, null);
      await this.imagesService.asyncSave(this.image);
      await this.imagesService.asyncFindAll();
      this.$router.push('/imageListRoute/allImages');
    },
    getToday() {
      return new Date().toISOString().split("T")[0];
    },
  },
  computed: {
    minWeek() {
      const today = new Date();
      const year = today.getFullYear();
      const week = Math.ceil((today - new Date(year, 0, 1)) / 604800000);
      return `${year}-W${week}`;
    },
  },
}
</script>

<style scoped>
.error {
  color: darkred;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 35px;
  margin-top: 25px;
}


</style>
