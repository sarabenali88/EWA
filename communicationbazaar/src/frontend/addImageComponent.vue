<template>
  <div class="container">
    <form @submit.prevent="validateInput">
      <div class="row">
        <div class="col-25">
          <label for="ean">EAN code</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="number" v-model.number="ean" placeholder="EAN code" @blur="validateInput"/>
            <div class="error" v-if="invalid === true && ean === null">Dit veld is verplicht</div>
            <div class="error" v-if="invalidEan === true && ean < 0">EAN code mag niet kleiner dan 0 zijn!</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="start">Startklaar Versie</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="text" v-model.trim="startVersion" placeholder="Startklaar versie"/>
            <div class="error" v-if="invalid === true && startVersion === ''">Dit veld is verplicht</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="image">Image Naam</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="text" placeholder="Image naam" v-model.trim="imageName"/>
            <div class="error" v-if="invalid === true && imageName === ''">Dit veld is verplicht</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="location">Image locatie</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="text" v-model.trim="locationImage" placeholder="Locatie van winkel"/>
            <div class="error" v-if="invalid === true && locationImage === ''">Dit veld is verplicht</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="status">Status</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <select v-model="statusSelect">
              <option value="todo">Te doen</option>
              <option value="ongoing">Mee bezig</option>
              <option value="finished">Afgerond</option>
            </select>
            <div class="error" v-if="invalid === true && statusSelect === ''">Dit veld is verplicht</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="date">Datum</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input :min="getToday()" type="date" v-model="date"/>
            <div class="error" v-if="invalid === true && date === ''">Dit veld is verplicht</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="week">Week</label>
        </div>
        <div class="col-75">
          <div class="input-container">
            <input type="week" v-model="week"/>
            <div class="error" v-if="invalid === true && week === ''">Dit veld is verplicht</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="subject">Problemen</label>
        </div>
        <div class="col-75">
          <input type="radio" value="yes" name="problem" v-model="selectedOption"/>Ja
          <input type="radio" value="no" name="problem" v-model="selectedOption"/>Nee
          <input type="radio" value="reported" name="problem" v-model="selectedOption"/>Gemeld bij SP
          <div v-if="selectedOption === 'yes'">
            <div>
              <label>Probleem uitleg:</label>
            </div>
            <textarea v-model="problemExplanation"></textarea>
          </div>
        </div>
      </div>
      <div>
        <button @click="validateInput">Opslaan</button>
      </div>

    </form>
  </div>

</template>


<script>
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
      imageListRoute: '/imageListRoute',
      selectedOption: 'no',
      problemExplanation: ''
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

  }
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

/* Style the label to display next to the inputs */
label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

/* Style the submit button */
button{
  background-color: #DA1C25;
  color: white;
  padding: 12px 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  width: 25%;
  margin-top: 20px;
}

/* Style the container */
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 30px;
  margin-top: 25px;
}

/* Floating column for labels: 25% width */
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

/* Clear floats after the columns */
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

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
}
</style>