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
              <input id="personalNumberField" v-model="personalNumber" @keyup.enter="checkInputSignIn" type="text"
                     class="form-control form-control-lg"/>
              <label class="form-label" for="typeEmailX-2">{{ $t('signIn.employeeNumber') }}</label>
            </div>

            <div class="form-outline mb-4">
              <input id="passwordField" v-model="password" @keyup.enter="checkInputSignIn" type="password"
                     class="form-control form-control-lg"/>
              <label class="form-label" for="typePasswordX-2">{{ $t('signIn.password') }}</label>
            </div>

            <button id="loginButton" @click="checkInputSignIn" class="btn btn-danger btn-lg " type="submit">
              {{ $t('signIn.logInButton') }}
            </button>
            <button @click="visibilitySwitch" class="btn btn-danger btn-lg m-1" type="submit">
              {{ $t('signIn.newPassword') }}
            </button>

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
              <input v-model="personalNumber" @keyup.enter="checkInputNewPassword" type="text"
                     class="form-control form-control-lg"/>
              <label class="form-label" for="typeEmailX-2">{{ $t('signIn.employeeNumber') }}</label>
            </div>

            <div class="form-outline mb-4">
              <input v-model="email" @keyup.enter="checkInputNewPassword" class="form-control form-control-lg"/>
              <label class="form-label" for="typePasswordX-2">E-mail</label>
            </div>

            <div class="form-outline mb-4">
              <input v-model="newPassword" @input="checkRegex" @keyup.enter="checkInputNewPassword" type="password"
                     class="form-control form-control-lg"/>
              <label class="form-label" for="typePasswordX-2">{{ $t('signIn.newPassword') }}</label>
            </div>

            <div class="form-outline mb-4">
              <input v-model="passwordRepeat" @keyup.enter="checkInputNewPassword" type="password"
                     class="form-control form-control-lg"/>
              <label class="form-label" for="typePasswordX-2">{{ $t('signIn.passwordRepeat') }}</label>
            </div>

            <button @click="checkInputNewPassword" class="btn btn-danger btn-lg" type="submit">
              {{ $t('signIn.passwordChange') }}
            </button>
            <button @click="visibilitySwitch" class="btn btn-danger btn-lg m-1" type="submit">
              {{ $t('signIn.logInButton') }}
            </button>

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
  inject: ["accountsService", "sessionService"],
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
      isMatch: false,
      sessionService: this.sessionService
    }
  },
  async created() {
    this.accounts = await this.accountsService.asyncFindAll();
  },
  methods: {
    /**
     * When a user wants to sign in, this method will be called.
     * It will check if the personalNumber and password connect with each other.
     * If that is true, the page will go to the homepage.
     *
     * @returns {Promise<void>}
     * @author Jasper Fernhout
     */
    async checkInputSignIn() {
      this.dismissAlert();
      this.account = await this.sessionService.asyncSignIn(this.personalNumber, this.password);
      if (this.account == null) {
        this.displayAlert(this.$t('signIn.wrongPersMessage'));
      } else {
        NavBar.methods.setCurrentContent('contentImage')
        this.$router.push(NavBar.data().homeRoute);
      }
    },
    /**
     * This method will be called when a user wants to make a new password.
     * All the information will be checked when an account is found with the right personalNumber.
     * If everything is correct, the user will be granted with the newly created password.
     *
     * @returns {Promise<void>}
     * @author Jasper Fernhout
     */
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
        } else {
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
    /**
     * This regex is checked when the newly chosen password is being made.
     * The password has to have at least one uppercase, a digit and a special character, witb a min length of 8.
     * (?=.*[A-Z]): Requires at least one uppercase letter.
     * (?=.*\d): Requires at least one digit.
     * [A-Za-z\d@$!%*#?&]{8,}: Matches any combination of uppercase/lowercase letters, digits, and specified special characters, with a minimum length of 8 characters.
     *
     * @author Jasper Fernhout
     */
    checkRegex() {

      const passwordPattern = /^(?=.*[A-Z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$/;

      this.isMatch = passwordPattern.test(this.newPassword);
    },
    /**
     * A method for displaying an alert with the given message.
     *
     * @param message the message that will be shown.
     * @author Jasper Fernhout
     */
    displayAlert(message) {
      this.alertMessage = message;
      this.showAlert = true;
    },
    /**
     * A message to close an alert when it is open.
     *
     * @author Jasper Fernhout
     */
    dismissAlert() {
      this.showAlert = false;
      this.alertMessage = '';
    },
    /**
     * This is a method that will change the view.
     * This switch will determine with view is on the page.
     * The view is either of signing in or choosing a new password.
     *
     * @author Jasper Fernhout
     */
    visibilitySwitch() {
      if (this.visible === true) {
        this.visible = false;
      } else if (this.visible === false) {
        this.visible = true
      }
    }
  }
}


</script>

<style scoped>


.activeContainer {
  display: none;
}

</style>