<template>
  <div class="container">
    <form>
      <div class="row">
        <div class="col-25">
          <label for="ean">EAN code</label>
        </div>
        <div class="col-75">
          <input type="text" id="eanId" name="ean" placeholder="EAN code">
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="start">Startklaar Versie</label>
        </div>
        <div class="col-75">
          <input type="text" id="startId" name="start" placeholder="Startklaar versie">
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="image">Image Naam</label>
        </div>
        <div class="col-75">
          <input type="text" id="imageId" name="imageName" placeholder="Image naam">
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="location">Image locatie</label>
        </div>
        <div class="col-75">
          <input type="text" id="fname" name="imageLocation" placeholder="Image door winkel">
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="status">Status</label>
        </div>
        <div class="col-75">
          <select id="statusId" name="status">
            <option value="">Kies een status</option>
            <option value="todo">Te doen</option>
            <option value="ongoing">Mee bezig</option>
            <option value="finished">Afgerond</option>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="date">Datum</label>
        </div>
        <div class="col-75">
          <input type="date" id="dateId" name="date">
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="week">Week</label>
        </div>
        <div class="col-75">
          <input type="week" id="weekId" name="week">
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="subject">Problemen</label>
        </div>
        <div class="col-75">
          <input type="radio" value="yes" name="problem">Ja
          <input type="radio" value="no" name="problem">Nee
          <input type="radio" value="reported" name="problem">Gemeld bij SP
        </div>
      </div>
      <div class="row">
        <input type="submit" value="Submit">
      </div>
    </form>
  </div>

</template>

<script>
export default {
  name: "addImageComponent",

}
</script>

<style scoped>
input[type=text], input[type=date], input[type=week], select, textarea{
  width: 70%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  resize: vertical;
}
input[type=radio]{
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
input[type=submit] {
  background-color: #04AA6D;
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

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
}
</style>