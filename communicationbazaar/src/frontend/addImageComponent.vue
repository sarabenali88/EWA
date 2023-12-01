<template>
  <div class="container mt-5">
    <div class="row mb-3">
      <label class="col-3" for="ean">{{ $t('addImage.eanNumber') }}</label>
      <div class="col-5">
        <div class="input-group">
          <select v-model="selectedLaptop" class="form-control">
              <option v-for="laptop in laptops" :key="laptop.ean" :value="laptop">
             {{laptop.ean}}
              </option>
            </select>
        </div>
        <div class="error" v-if="invalid === true && selectedLaptop === null">{{ $t('addImage.alertEmpty') }}</div>
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
  inject: ["imagesService", "laptopsService"],
  data() {
    return {
      selectedLaptop: null,
      startVersion: '',
      imageName: '',
      statusSelect: '',
      date: '',
      week: '',
      invalid: false,
      invalidEan: false,
      image: null,
      formattedDate: null,
      formattedWeek: null,
      laptops: [],
      defaultId: 0,
    }
  },
  async created() {
    this.laptops = await this.laptopsService.asyncFindAll();
  },
  methods: {
    async validateInput() {
      if (this.selectedLaptop === null || this.startVersion === '' || this.imageName === '' ||
          this.statusSelect === '' || this.date === '' || this.week === '') {
        this.invalid = true;
      } else {
        this.invalid = false;
      }
      if (this.invalid === false) {
        await this.saveImage();
      }
    },
    formatDate(inputDate) {
      const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
      return new Date(inputDate).toLocaleDateString('nl-NL', options);
    },
    splitWeek(){
      return this.week.split('W')[1];
    },
    async saveImage() {
       this.formattedDate = this.formatDate(this.date);
       this.formattedWeek = this.splitWeek(this.week);
       this.image =  new Image(this.defaultId,this.selectedLaptop, this.startVersion, null,
            this.formattedDate, this.statusSelect, null, null, this.formattedWeek, null,
           this.imageName, null, null);
       console.log(this.selectedLaptop);
       console.log(this.image);
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
