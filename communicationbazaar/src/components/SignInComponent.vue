<template>
  <div class="container py-5 h-100" :class="{'activeContainer': visible === false}">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong">
          <div class="card-body p-5 text-center">

            <h3 class="mb-5">{{ $t('signIn.title') }}</h3>

            <div class="form-outline mb-4">
              <div v-if="showAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
                {{ alertMessage }}
                <button type="button" class="btn-close" @click="dismissAlert" aria-label="Close"></button>
              </div>
              <input id="personalNumberField" v-model="personalNumber" @keyup.enter="checkInputSignIn" type="text" class="form-control form-control-lg"/>
              <label class="form-label" for="typeEmailX-2">{{ $t('signIn.employeeNumber') }}</label>
            </div>

            <div class="form-outline mb-4">
              <input id="passwordField" v-model="password" @keyup.enter="checkInputSignIn" type="password" class="form-control form-control-lg"/>
              <label class="form-label" for="typePasswordX-2">{{ $t('signIn.password') }}</label>
            </div>

            <button id="loginButton" @click="checkInputSignIn" class="btn btn-danger btn-lg " type="submit">
              {{ $t('signIn.logInButton') }}</button>
            <button @click="visibilitySwitch" class="btn btn-danger btn-lg m-1" type="submit">
              {{ $t('signIn.newPassword') }}</button>

            <hr class="my-4">
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="container py-5 h-100" :class="{'activeContainer': visible === true}">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong">
          <div class="card-body p-5 text-center">

            <h3 class="mb-5">{{ $t('signIn.newPassword') }}</h3>

            <div class="form-outline mb-4">
              <div v-if="showAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
                {{ alertMessage }}
                <button type="button" class="btn-close" @click="dismissAlert" aria-label="Close"></button>
              </div>
              <input v-model="personalNumber" @keyup.enter="checkInputNewPassword" type="text" class="form-control form-control-lg"/>
              <label class="form-label" for="typeEmailX-2">{{ $t('signIn.employeeNumber') }}</label>
            </div>

            <div class="form-outline mb-4">
              <input v-model="email" @keyup.enter="checkInputNewPassword" class="form-control form-control-lg"/>
              <label class="form-label" for="typePasswordX-2">E-mail</label>
            </div>

            <div class="form-outline mb-4">
              <input v-model="newPassword" @input="checkRegex" @keyup.enter="checkInputNewPassword" type="password" class="form-control form-control-lg"/>
              <label class="form-label" for="typePasswordX-2">{{ $t('signIn.newPassword') }}</label>
            </div>

            <div class="form-outline mb-4">
              <input v-model="passwordRepeat" @keyup.enter="checkInputNewPassword" type="password" class="form-control form-control-lg"/>
              <label class="form-label" for="typePasswordX-2">{{ $t('signIn.passwordRepeat') }}</label>
            </div>

            <button @click="checkInputNewPassword" class="btn btn-danger btn-lg" type="submit">
              {{ $t('signIn.passwordChange') }}</button>
            <button @click="visibilitySwitch" class="btn btn-danger btn-lg m-1" type="submit">
              {{ $t('signIn.logInButton') }}</button>

            <hr class="my-4">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
/**
 * This is the component for the functionalities of signing in.
 *
 * @author Jasper Fernhout
 */

import NavBar from "@/components/NavBarComponent";

export default {
  name: "SignInComponent",
  inject: ["accountsService"],
  data() {
    return {
      accounts: [],
      personalNumber: "",
      password: "",
      newPassword: "",
      passwordRepeat: "",
      email: "",
      account: null,
      showAlert: false,
      alertMessage: '',
      visible: true,
      isMatch: false
    }
  },
  async created() {
    this.accounts = await this.accountsService.asyncFindAll();
  },
  methods: {
    async checkInputSignIn() {
      if (!this.accounts.find(account => account.personalNumber === parseInt(this.personalNumber))) {
        this.displayAlert(this.$t('signIn.wrongPersMessage'));
      } else if (this.accounts.find(account => account.personalNumber === parseInt(this.personalNumber))) {
        this.account = this.accounts.find(account => account.personalNumber === parseInt(this.personalNumber));
        if (!this.password) {
          this.displayAlert(this.$t('signIn.wrongPassMessage'));
        } else if (!await this.accountsService.verifyPassword(this.personalNumber, this.password)) {
          this.displayAlert(this.$t('signIn.wrongPassMessage'));
        } else if (this.account.password === "welkom") {
          this.password = "";
          this.visibilitySwitch();
        }else {
          NavBar.methods.setCurrentContent('contentImage')
          this.account.loggedIn = true;
          await this.accountsService.asyncSave(this.account);
          this.$router.push(NavBar.data().homeRoute);
        }
      }
    },
    async checkInputNewPassword() {
      if (!this.accounts.find(account => account.personalNumber === parseInt(this.personalNumber))) {
        this.displayAlert(this.$t('signIn.wrongPersMessage'));
      } else if (this.accounts.find(account => account.personalNumber === parseInt(this.personalNumber))) {
        this.account = this.accounts.find(account => account.personalNumber === parseInt(this.personalNumber));
        if (!this.email || !this.passwordRepeat || !this.newPassword) {
          this.displayAlert(this.$t('adminPanel.errorMessage'));
        } else if (this.account.email !== this.email) {
          this.displayAlert(this.$t('signIn.emailWrong'));
        } else if (this.isMatch === false) {
          this.displayAlert(this.$t('signIn.passwordCheck'));
        } else if (this.newPassword !== this.passwordRepeat) {
          this.displayAlert(this.$t('signIn.passwordWrongRepeat'));
        }else {
          this.account.password = this.newPassword;
          await this.accountsService.asyncSave(this.account);
          this.dismissAlert();
          this.newPassword = "";
          this.passwordRepeat = "";
          this.email = "";
          this.visibilitySwitch();
        }
      }
    },
    checkRegex() {
      // (?=.*[A-Z]): Requires at least one uppercase letter.
      // (?=.*\d): Requires at least one digit.
      // [A-Za-z\d@$!%*#?&]{8,}: Matches any combination of uppercase/lowercase letters, digits, and specified special characters, with a minimum length of 8 characters.
      const passwordPattern = /^(?=.*[A-Z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$/;

      this.isMatch = passwordPattern.test(this.newPassword);
    },
    displayAlert(message) {
      this.alertMessage = message;
      this.showAlert = true;
    },
    dismissAlert() {
      this.showAlert = false;
      this.alertMessage = '';
    },
    visibilitySwitch(){
      if (this.visible === true) {
        this.visible = false;
      } else if (this.visible === false) {
        this.visible = true
      }
    }
  },
}


</script>

<style scoped>


.activeContainer{
  display: none;
}

</style>