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
        <div class="error" v-if="invalid === true">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="start">{{ $t('addImage.ReadyToUseVersion') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="text" class="form-control" v-model.trim="startVersion"/>
        </div>
        <div class="error" v-if="invalid === true">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="image">{{ $t('addImage.imageName') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="text" class="form-control" v-model.trim="imageName"/>
        </div>
        <div class="error" v-if="invalid === true">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="status">{{ $t('addImage.status') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input class="form-control" v-model="statusImage" readonly>
        </div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="date">{{ $t('addImage.date') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input :min="getToday()" type="date" class="form-control" v-model="date"/>
        </div>
        <div class="error" v-if="invalid === true">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="week">{{ $t('addImage.week') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="week" class="form-control" v-model="week" :min="getWeek()"/>
        </div>
        <div class="error" v-if="invalid === true">{{ $t('addImage.alertEmpty') }}</div>
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

/**
 * Script for adding a new Image to the list
 * @author Sara Benali
 */
export default {
  name: "addImageComponent",
  inject: ["imagesService", "laptopsService"],
  data() {
    return {
      selectedLaptop: null,
      startVersion: '',
      imageName: '',
      statusImage: Object.keys(Image.Status)[0],
      date: '',
      week: '',
      invalid: false,
      image: null,
      formattedDate: null,
      formattedWeek: null,
      laptops: [],
      defaultId: 0,
      milliSecond: 604800000,
    }
  },
  /**
   * Async method to load all the laptops when user is on the page
   * @return {Promise<void>}
   * @author Sara Benali
   */
  async created() {
    this.laptops = await this.laptopsService.asyncFindAll();
  },
  methods: {
    /**
     * Method to check inputs of the user and save an image if input is correct
     * @return {Promise<void>}
     * @author Sara Benali
     */
    async validateInput() {
      if (this.selectedLaptop === null || this.startVersion === '' || this.imageName === ''
          || this.date === '' || this.week === '') {
        this.invalid = true;
      } else {
        this.invalid = false;
      }
      if (this.invalid === false) {
        await this.saveImage();
      }
    },
    /**
     * Method to format the entered date value to dd-mm-yyyy format
     * @param inputDate the value of a date
     * @return {string}
     * @author Sara Benali
     */
    formatDate(inputDate) {
      const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
      return new Date(inputDate).toLocaleDateString('nl-NL', options);
    },
    /**
     * Method to format week from "2023-W43" to only get week number (43)
     * @return {string}
     * @author Sara Benali
     */
    formatWeek(week){
      return week.split('W')[1];
    },
    /**
     * Method to save an image to the list with the entered user value
     * @return {Promise<void>}
     * @author Sara Benali
     */
    async saveImage() {
       this.formattedDate = this.formatDate(this.date);
       this.formattedWeek = this.formatWeek(this.week);
       this.image =  new Image(this.defaultId,this.selectedLaptop, this.startVersion, null,
            this.formattedDate, this.statusImage, null, null, this.formattedWeek, null,
           this.imageName, null, null);
      await this.imagesService.asyncSave(this.image);
      await this.imagesService.asyncFindAll();
      this.$router.push('/imageListRoute/allImages');
    },
    /**
     * Method to get today's date and put it as minimum so user can't choose a date before today
     * @return {string}
     * @author Sara Benali
     */
    getToday() {
      return new Date().toISOString().split("T")[0];
    },
    /**
     * Method to calculate this week's date and set it as minimum so user can't choose a week before this week
     * @return {string}
     * @author Sara Benali
     */
    getWeek() {
      const today = new Date();
      const year = today.getFullYear();
      const week = Math.ceil((today - new Date(year, 0, 1)) / this.milliSecond);
      return `${year}-W${week}`;
    },
  },
  computed: {
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
