<template>
  <div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
      <div class="col-md-3 border-right">
        <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5"
                                                                                     alt="MediaMarkt employee"
                                                                                     width="150px"
                                                                                     src="../assets/Cindy.jpg"><span
            class="fs-3">Cindy Visser</span><span class="text-black-50 fs-6">cindyvisser@mm.nl</span>
        </div>
      </div>
      <form action="/action_page.php" class="col-md-5 border-right">

        <div class="p-3 py-5">
          <div class="d-flex justify-content-between align-items-center mb-3">
            <h4 class="text-right">Profile Settings</h4>
          </div>
          <div class="row mt-2" v-for="item in data" :key="item.id">
            <div class="col-md-12"><label class="labels">Username</label><input type="text" class="form-control"
                                                                                placeholder="Username"
                                                                                :value="item.employeeNumber" required>
            </div>
            <div class="col-md-12"><label class="labels">Password</label><input type="password" class="form-control"
                                                                                placeholder="Password"
                                                                                :value="item.password" required></div>
          </div>
          <div class="row mt-3" v-for="item in data" :key="item.id">
            <div class="col-md-6"><label class="labels">Name</label><input type="text" class="form-control"
                                                                           placeholder="First name"
                                                                           :value="item.firstName"
                                                                           required></div>
            <div class="col-md-6"><label class="labels">Surname</label><input type="text" class="form-control"
                                                                              :value="item.lastName"
                                                                              placeholder="Surname" required>
            </div>
            <div class="col-md-12"><label class="labels" for="phone">Mobile Number</label><input type="tel"
                                                                                                 class="form-control"
                                                                                                 placeholder="Mobile number"
                                                                                                 pattern="[0-9]{10}"
                                                                                                 :value="item.mobileNumber"
                                                                                                 required>
            </div>
            <div class="col-md-9"><label class="labels">Address</label><input type="text" class="form-control"
                                                                              placeholder="Address"
                                                                              :value="item.address" required>
            </div>
            <div class="col-md-3"><label class="labels">House number</label><input type="number" class="form-control"
                                                                                   placeholder="Number"
                                                                                   :value="item.houseNumber"
                                                                                   required>
            </div>
            <div class="col-md-12"><label class="labels">Postcode</label><input type="text" class="form-control"
                                                                                placeholder="Postcode"
                                                                                :value="item.postcode"
                                                                                required></div>
            <div class="col-md-12"><label class="labels">City</label><input type="text" class="form-control"
                                                                            placeholder="City"
                                                                            :value="item.city" required></div>
            <div class="col-md-12"><label class="labels">Function</label><input type="text" class="form-control"
                                                                                placeholder="Function"
                                                                                :value="item.function"
                                                                                required>
            </div>
            <div class="col-md-12"><label class="labels">Email</label><input type="text" class="form-control"
                                                                             placeholder="Email"
                                                                             :value="item.email"
                                                                             required>
            </div>
          </div>
          <div class="mt-3">
            <div class="col-md-12"><label class="labels">Nationality</label><input type="checkbox" class="btn-check "
                                                                                   id="btn-check " autocomplete="off">
            </div>
            <div class="form-check">
              <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" required>
              <label class="form-check-label" for="flexRadioDefault1">
                Belgian
              </label>
            </div>
            <div class="form-check">
              <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" required>
              <label class="form-check-label" for="flexRadioDefault2">
                Dutch
              </label>
            </div>
            <div class="form-check">
              <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" required>
              <label class="form-check-label" for="flexRadioDefault3">
                Luxembourger
              </label>
            </div>
          </div>
          <div class="mt-5 text-center"><input class="btn btn-danger btn-lg btn-block" type="submit" value="Save">
          </div>
        </div>

      </form>
    </div>
  </div>
</template>

<script>
import account from '@/profile.json'

export default {
  name: "ProfilePageComponent",
  data() {
    return {
      data: account
    }
  }
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
