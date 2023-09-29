<template>
  <div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
      <div class="col-md-3 border-right">
        <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" alt="MediaMarkt employee" width="150px" src="../assets/Cindy.jpg"><span class="fs-3">Cindy Visser</span><span class="text-black-50 fs-6">cindyvisser@mm.nl</span><span> </span></div>
      </div>
      <div class="col-md-5 border-right">
        <div class="p-3 py-5">
          <div class="d-flex justify-content-between align-items-center mb-3">
            <h4 class="text-right">Profile Settings</h4>
          </div>
          <div class="row mt-2">
            <div class="col-md-6"><label class="labels">Name</label><input type="text" class="form-control" placeholder="First name" value=""></div>
            <div class="col-md-6"><label class="labels">Surname</label><input type="text" class="form-control" value="" placeholder="Surname"></div>
          </div>
          <div class="row mt-3">
            <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" class="form-control" placeholder="Phone number" value=""></div>
            <div class="col-md-6"><label class="labels">Address</label><input type="text" class="form-control" placeholder="Address" value=""></div>
            <div class="col-md-6"><label class="labels">House number</label><input type="text" class="form-control" placeholder="House number" value=""></div>
            <div class="col-md-12"><label class="labels">Postcode</label><input type="text" class="form-control" placeholder="Postcode" value=""></div>
            <div class="col-md-12"><label class="labels">City</label><input type="text" class="form-control" placeholder="City" value=""></div>
            <div class="col-md-12"><label class="labels">Department</label><input type="text" class="form-control" placeholder="Department" value=""></div>
            <div class="col-md-12"><label class="labels">Email</label><input type="text" class="form-control" placeholder="Email" value=""></div>
          </div>
          <div class="mt-5 text-center"><button class="btn btn-danger btn-lg btn-block" type="button">Save Profile</button></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfilePageComponent"
}
</script>

<style scoped>
.labels {
  font-size: 11px
}

.form-control:focus {
  border-color: #ffffff;
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(255, 0, 0, 0.6);
}

</style>
