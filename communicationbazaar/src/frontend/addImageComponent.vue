<template>
  <div class="container">
      <div class="row">
        <div class="col-25">
          <label for="ean">{{$t('addImage.eanNumber')}}</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="number" v-model.number="ean" @blur="validateInput"/>
            <div class="error" v-if="invalid === true && ean === null">{{$t('addImage.alertEmpty')}}</div>
            <div class="error" v-if="invalidEan === true && ean < 0">{{$t('addImage.alertEan')}}</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="start">{{ $t('addImage.ReadyToUseVersion') }}</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="text" v-model.trim="startVersion"/>
            <div class="error" v-if="invalid === true && startVersion === ''">{{$t('addImage.alertEmpty')}}</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="image">{{$t('addImage.imageName')}}</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="text" v-model.trim="imageName"/>
            <div class="error" v-if="invalid === true && imageName === ''">{{$t('addImage.alertEmpty')}}</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="location">{{$t('addImage.imageLocation')}}</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="text" v-model.trim="locationImage"/>
            <div class="error" v-if="invalid === true && locationImage === ''">{{$t('addImage.alertEmpty')}}</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="status">{{$t('addImage.status')}}</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <select v-model="statusSelect">
              <option value="todo">{{$t('addImage.statusToDo')}}</option>
              <option value="ongoing">{{$t('addImage.statusOngoing')}}</option>
              <option value="finished">{{$t('addImage.statusFinished')}}</option>
            </select>
            <div class="error" v-if="invalid === true && statusSelect === ''">{{$t('addImage.alertEmpty')}}</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="date">{{$t('addImage.date')}}</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input :min="getToday()" type="date" v-model="date"/>
            <div class="error" v-if="invalid === true && date === ''">{{$t('addImage.alertEmpty')}}</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="week">{{$t('addImage.week')}}</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="week" v-model="week" :min="minWeek"/>
            <div class="error" v-if="invalid === true && week === ''">{{$t('addImage.alertEmpty')}}</div>
          </div>
        </div>
      </div>
      <div>
        <button class="buttonSave" @click="validateInput">{{$t('addImage.buttonSave')}}</button>
      </div>

  </div>

</template>

<script>
import '../i18n.js'

export default {
  name: "addImageComponent",
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
    }
  },
  methods: {
    validateInput() {
      if (this.ean === '' || this.startVersion === '' || this.imageName === '' || this.locationImage === '' ||
          this.statusSelect === '' || this.date === ''|| this.week === ''){
        this.invalid = true;
      }else{
        this.invalid = '';
      }
      if (this.ean < 0){
        this.invalidEan = true;
      }else{
        this.invalidEan = '';
      }
      if (this.invalid === '' && this.invalidEan === ''){
        this.$router.push('/imageListRoute');
      }
    },
    getToday() {
      return new Date().toISOString().split("T")[0];
    },
  },
  computed: {
    minWeek() {
      const today = new Date(); // get today's date
      const year = today.getFullYear(); // get today's year
      const week = Math.ceil((today - new Date(year, 0, 1)) / 604800000); //calculate current week by the
      // difference between today and the first year by milliseconds
      return `${year}-W${week}`; // minimum week value in formatie
    },
  },
}
</script>

<style scoped>
input[type=text], input[type=date], input[type=number], input[type=week], select, textarea {
  width: 70%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  resize: vertical;
}

input[type=radio] {
  width: 5%;
  margin-top: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  resize: vertical;
}

label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

.buttonSave{
  background-color: #DA1C25;
  color: white;
  padding: 12px 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  width: 25%;
  margin-top: 20px;
  float: left;
}
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 30px;
  margin-top: 25px;
}

.col-25 {
  width: 25%;
  margin-top: 6px;
}

/* Floating column for inputs: 75% width */
.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}
.row:after {
  content: "";
  display: table;
  clear: both;
}
.input-container {
  display: flex;
  flex-direction: column;
}

.error{
  color: darkred;
}

@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=button] {
    width: 100%;
    margin-top: 0;
  }
}
</style>
