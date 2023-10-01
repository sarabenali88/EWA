<template>
  <div class="container">
    <Form>
      <div class="row">
        <div class="col-25">
          <label for="ean">EAN code</label>
        </div>
        <div class="col-75">
          <div class="input-container">
          <Field type="text" id="eanId" name="ean" placeholder="EAN code" :rules="[validateEan]" lazy/>
          <ErrorMessage class="error" name="ean"/>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="start">Startklaar Versie</label>
        </div>
        <div class="col-75">
          <div class="input-container">
          <Field type="text" id="startId" name="start" placeholder="Startklaar versie"
                 :rules="[validateInput]" lazy/>
            <ErrorMessage class="error" name="start"/>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="image">Image Naam</label>
        </div>
        <div class="col-75">
          <div class="input-container">
          <Field type="text" id="imageId" name="image" placeholder="Image naam" :rules="[validateInput]" lazy/>
            <ErrorMessage class="error" name="image"/>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="location">Image locatie</label>
        </div>
        <div class="col-75">
          <div class="input-container">
          <Field type="text" id="locationId" name="location" placeholder="Image door winkel" :rules="[validateInput]" lazy/>
            <ErrorMessage class="error" name="location"/>
            </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="status">Status</label>
        </div>
        <div class="col-75">
          <div class="input-container">
          <Field as="select" id="statusId" name="status" :rules="[validateInput]" lazy>
            <option value="todo">Te doen</option>
            <option value="ongoing">Mee bezig</option>
            <option value="finished">Afgerond</option>
          </Field>
            <ErrorMessage class="error" name="status"/>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="date">Datum</label>
        </div>
        <div class="col-75">
          <div class="input-container">
          <Field type="date" id="dateId" name="date" :rules="[validateInput]" lazy/>
            <ErrorMessage class="error" name="date"/>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="week">Week</label>
        </div>
        <div class="col-75">
          <div class="input-container">
          <Field type="week" id="weekId" name="week" :rules="[validateInput]" lazy/>
            <ErrorMessage class="error" name="week"/>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="subject">Problemen</label>
        </div>
        <div class="col-75">
          <Field type="radio" value="yes" name="problem" :rules="[validateInput]" lazy/>Ja
          <Field type="radio" value="no" name="problem" :rules="[validateInput]" lazy/>Nee
          <Field type="radio" value="reported" name="problem" :rules="[validateInput]" lazy/>Gemeld bij SP
            <ErrorMessage class="error" name="problem"/>
          </div>
      </div>
      <div class="row">
        <button type="submit">
          Opslaan
        </button>
      </div>

    </Form>
  </div>

</template>


<script>
import { Form, Field, ErrorMessage } from 'vee-validate';
export default {
  name: "addImageComponent",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    return {
      eanCode: '',
      startVersion: '',
      imageName: '',
      location: '',
      selectedVModel: null,
      imageListRoute: '/imageListRoute',

    }
  },
  methods: {
    validateEan(value) {
        if (!value) {
          return 'Dit veld is verplicht';
        }
        if (isNaN(value)) {
          return "EAN code moet uit getallen bestaan";
        }
        return true;
      },
    validateInput(value){
      if (!value) {
        return 'Dit veld is verplicht';
      }
    },
    }
}
</script>

<style scoped>
input[type=text], input[type=date], input[type=week], select, textarea {
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
button[type=submit] {
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
.error{
  color: darkred;
}
.input-container {
  display: flex;
  flex-direction: column;
}


/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
}
</style>