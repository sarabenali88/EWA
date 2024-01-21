<template>
  <div class="container mt-5">
    <div class="row mb-3">
      <label class="col-3" for="ean">{{ $t('addImage.eanNumber') }}</label>
      <div class="col-5">
        <div class="input-group">
          <select  id="selectedLaptopField" v-model="selectedLaptop" class="form-control">
            <option v-for="laptop in laptops" :key="laptop.ean" :value="laptop" >
              {{ laptop.ean }}
            </option>
          </select>
        </div>
        <div class="error" v-if="invalid === true">{{$t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="start">{{ $t('addImage.ReadyToUseVersion') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input id="startVersionField" type="text" class="form-control" v-model.trim="startVersion"/>
        </div>
        <div class="error" v-if="invalid === true">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="image">{{ $t('addImage.imageName') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input id="imageNameField" type="text" class="form-control" v-model.trim="imageName"/>
        </div>
        <div class="error" v-if="invalid === true">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="status">{{ $t('addImage.status') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input id="statusImageField" class="form-control" v-model="statusImage" readonly>
        </div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="date">{{ $t('addImage.date') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input id="dateField" :min="getToday()" type="date" class="form-control" v-model="date"/>
        </div>
        <div class="error" v-if="invalid === true">{{ $t('addImage.alertEmpty') }}</div>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-3" for="week">{{ $t('addImage.week') }}</label>
      <div class="col-5">
        <div class="input-group">
          <input id="weekField" type="week" class="form-control" v-model="week" readonly/>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-12">
        <button id="save-button" class="btn btn-danger w-25" @click="modalShow">{{ $t('addImage.buttonSave') }}</button>
      </div>
    </div>
    <div class="modal" tabindex="-1" role="dialog" style="display: block;" v-if="showModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ $t('adminPanel.confirmation') }}</h5>
            <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>{{ $t('addImage.confirmMessage') }}</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="closeModal()">{{ $t('adminPanel.cancelButton')}}</button>
            <button type="button" id="save-image-button"
                    class="btn btn-danger" @click="onSave()">{{ $t('addImage.buttonSave') }}</button>
          </div>
        </div>
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
      laptops: [],
      defaultId: 0,
      showModal: false,
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
     * @return {boolean}
     * @author Sara Benali
     */
    validateInput() {
      if (this.selectedLaptop === null || this.startVersion === '' || this.imageName === '' || this.date === '') {
        this.invalid = true;
        return false;
      } else {
        this.invalid = false;
        return true;
      }
    },
    /**
     * Method to close the modal
     * @author Sara Benali
     */
    closeModal() {
      this.showModal = false;
    },
    /**
     * Method to show the modal only after the fields are validated
     * @author Sara Benali
     */
    modalShow() {
      if (this.validateInput()) {
        this.showModal = true;
      }
    },
    /**
     * Method to save the new created image and to close the modal
     * @return {Promise<void>}
     * @author Sara Benali
     */
    async onSave() {
      await this.saveImage();
      this.closeModal();
    },
    /**
     * Method to format the entered date value to dd-mm-yyyy format
     * @param inputDate the value of a date
     * @return {string} -date format in "DD-MM-YYYY"
     * @author Sara Benali
     */
    formatDate(inputDate) {
      const options = {year: 'numeric', month: 'numeric', day: 'numeric'};
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
      this.image = new Image(this.defaultId, this.selectedLaptop, this.startVersion, null,
          this.formattedDate, this.statusImage, null, null, this.formattedWeek, this.formattedYear,
          this.imageName, null, null);
      await this.imagesService.asyncSave(this.image);
      await this.imagesService.asyncFindAll();
      this.$router.push('/imageListRoute/allImages');
    },
    /**
     * Method to get today's date and put it as minimum so user can't choose a date before today
     * @return {string} -date format in without the timezone included
     * @author Sara Benali
     */
    getToday() {
      return new Date().toISOString().split("T")[0];
    },
    /**
     * Gets the week from the selected date
     * @param selectedDate -selected date in "YYYY-MM-DD" format
     * @return {string} -week format in "YYYY-Www"
     *
     * @author Sara Benali
     */
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
    /**
     * checks if the date value is changed, so it updates the week value accordingly
     * @param newDate - new Date value
     * @author Sara Benali
     */
    date(newDate) {
      this.week = this.getWeekFromDate(newDate);
    },
  },
  computed: {},
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
