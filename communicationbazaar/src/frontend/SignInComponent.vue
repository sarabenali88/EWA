<template>
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong">
          <div class="card-body p-5 text-center">

            <h3 class="mb-5">Sign in</h3>

            <div class="form-outline mb-4">
              <div v-if="showAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
                {{ alertMessage }}
                <button type="button" class="btn-close" @click="dismissAlert" aria-label="Close"></button>
              </div>
              <input v-model="personalNumber" @keyup.enter="checkInput" type="text" class="form-control form-control-lg"/>
              <label class="form-label" for="typeEmailX-2">Personal Number</label>
            </div>

            <div class="form-outline mb-4">
              <input v-model="password" @keyup.enter="checkInput" type="password" id="typePasswordX-2" class="form-control form-control-lg"/>
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

import NavBar from "@/frontend/NavBarComponent";

export default {
  name: "SignInComponent",
  inject: ["accountsService"],
  data() {
    return {
      accounts: [],
      personalNumber: "",
      password: "",
      account: null,
      showAlert: false,
      alertMessage: ''
    }
  },
  async created() {
    this.accounts = await this.accountsService.asyncFindAll();
  },
  methods: {
    async checkInput() {
      if (!this.accounts.find(account => account.personalNumber === parseInt(this.personalNumber))) {
        this.displayAlert("Personeelsnummer is verkeerd!")
      } else if (this.accounts.find(account => account.personalNumber === parseInt(this.personalNumber))) {
        this.account = this.accounts.find(account => account.personalNumber === parseInt(this.personalNumber));
        if (this.account.password !== this.password) {
          this.displayAlert("Wachtwoord is verkeerd!")
        } else {
          NavBar.methods.setCurrentContent('contentImage')
          this.account.loggedIn = true;
          console.log(this.account.loggedIn)
          await this.accountsService.asyncSave(this.account);
          this.$router.push(NavBar.data().homeRoute);
        }
      }
    },
    displayAlert(message) {
      this.alertMessage = message;
      this.showAlert = true;
    },
    dismissAlert() {
      this.showAlert = false;
      this.alertMessage = '';
    },
  },
}


</script>

<style scoped>

</style>