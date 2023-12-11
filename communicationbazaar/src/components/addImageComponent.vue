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
        <div class="error" v-if="invalid === true">{{ errorMessage}}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="start">{{ $t('addImage.ReadyToUseVersion') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="text" class="form-control" v-model.trim="startVersion"/>
        </div>
        <div class="error" v-if="invalid === true">{{ errorMessage }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="image">{{ $t('addImage.imageName') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="text" class="form-control" v-model.trim="imageName"/>
        </div>
        <div class="error" v-if="invalid === true">{{ errorMessage}}</div>
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
        <div class="error" v-if="invalid === true">{{ errorMessage}}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="week">{{ $t('addImage.week') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input type="week" class="form-control" v-model="week" readonly/>
        </div>
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
      formattedYear: null,
      errorMessage: null,
      laptops: [],
      defaultId: 0,
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
      if (this.selectedLaptop === null || this.startVersion === '' || this.imageName === '' || this.date === '') {
        this.invalid = true;
        this.errorMessage = this.$t('addImage.alertEmpty');
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
     * Method to save an image to the list with the entered user value
     * @return {Promise<void>}
     * @author Sara Benali
     */
    async saveImage() {
       this.formattedDate = this.formatDate(this.date);
       this.formattedWeek = this.week.split('W')[1];
       this.formattedYear = this.week.split('-')[0];
       this.image =  new Image(this.defaultId,this.selectedLaptop, this.startVersion, null,
            this.formattedDate, this.statusImage, null, null, this.formattedWeek, this.formattedYear,
           this.imageName, null, null);
      await this.imagesService.asyncSave(this.image);
      console.log(this.image);
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
    getWeekFromDate(selectedDate) {
      const date = new Date(selectedDate);
      date.setDate(date.getDate() + 4 - (date.getDay() || 7));
      const year = date.getFullYear();
      const startOfYear = new Date(year, 0, 1);
      const days = Math.ceil((date - startOfYear) / 86400000);
      const week = Math.ceil((days + startOfYear.getDay() + 1) / 7);
      const formattedWeek = week.toString().padStart(2, '0');
      return `${year}-W${formattedWeek}`;
    },
  },
  watch: {
    date(newDate) {
      this.week = this.getWeekFromDate(newDate);
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
