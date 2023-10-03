<template>
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong">
          <div class="card-body p-5 text-center">

            <h3 class="mb-5">Sign in</h3>

            <div class="form-outline mb-4">
              <input v-model="personalNumber" type="text" class="form-control form-control-lg"/>
              <label class="form-label" for="typeEmailX-2">Personal Number</label>
            </div>

            <div class="form-outline mb-4">
              <input v-model="password" type="password" id="typePasswordX-2" class="form-control form-control-lg"/>
              <label class="form-label" for="typePasswordX-2">Password</label>
            </div>

            <div class="form-check d-flex justify-content-start mb-4">
              <input class="form-check-input" type="checkbox" value="" id="form1Example3"/>
              <label class="form-check-label" for="form1Example3"> Remember password </label>
            </div>

            <button @click="checkInput" class="btn btn-danger btn-lg btn-block" type="submit">Login</button>

            <hr class="my-4">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import json from '../account.json'
import NavBar from "@/frontend/NavBarComponent";

export default {
  name: "SignInComponent",
  data() {
    return {
      personalNumber: "",
      password: "",
      accountData: json
    }
  },
  methods: {
    checkInput() {
      for (let i = 0; i < this.accountData.length; i++) {
        if (this.personalNumber === this.accountData[i].personalNumber) {
          if (this.password === this.accountData[i].password) {
            alert("Ingelogd");
            this.$router.push(NavBar.data().homeRoute);
            this.accountData[i].loggedIn = true;
          } else if (this.password !== this.accountData[i].password) {
            alert("Wachtwoord verkeerd.");
          }
        }
      }
    }
  },
}


</script>

<style scoped>

</style>