<template>
  <div class="card h-100">
    <div class="card-body">
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <h6 class="mb-2 text-danger">Account Toevoegen</h6>
        </div>
        <div v-if="showAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
          {{ alertMessage }}
          <button type="button" class="btn-close" @click="dismissAlert" aria-label="Close"></button>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !name }">
            <label for="fullName">Naam</label>
            <input v-model="name" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !email }">
            <label for="eMail">Email</label>
            <input v-model="email" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !password }">
            <label for="website">Wachtwoord</label>
            <input v-model="password" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !role }">
            <label for="zIp">Rol</label>
            <select v-model="role" class="form-select" aria-label="Default select example">
              <option value="" disabled selected>Selecteer</option>
              <option value="ImageMaker">ImageMaker</option>
              <option value="admin">admin</option>
            </select>
          </div>
        </div>
      </div>
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <div class="text-right">
            <button @click="showConfirmModal(cancel)" class="btn btn-secondary">Annuleren</button>
            <button @click="showConfirmModal(confirm)" class="btn btn-success m-lg-2">OK</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="modal" tabindex="-1" role="dialog" style="display: block;" v-if="showModal">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Confirmatie</h5>
          <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p>Weet u zeker dat u door wilt gaan?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" @click="cancelAction()">Annuleren</button>
          <button type="button" class="btn btn-success" @click="performAction()">OK</button>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import {Account} from "@/models/Account";

export default {
  name: "UserAddComponent",
  data() {
    return {
      account: null,
      defaultPersonalNumber: 0,
      name: "",
      email: "",
      password: "",
      role: "",
      showAlert: false,
      alertMessage: '',
      showModal: false,
      cancel: "cancel",
      confirm: "confirm",
      action: ""
    }
  },
  methods: {
    cancelEvent() {
      this.$emit('cancelEvent', null);
    },
    saveEvent() {
      this.account = new Account(this.defaultPersonalNumber, this.password, this.name, this.email, this.role, [], [], false)
      this.$emit('saveEvent', this.account);
    },
    fieldsFilledCheck() {
      if (!this.name || !this.email || !this.role || !this.password) {
        this.displayAlert("Niet elk veld is ingevuld!")
        return false;
      } else {
        return true;
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
    showConfirmModal(cancelOrConfirm) {
      if (cancelOrConfirm === "confirm") {
        if (this.fieldsFilledCheck()) {
          this.dismissAlert();
          this.action = "confirm";
          this.showModal = true;
        }
      } else if (cancelOrConfirm === "cancel") {
        this.action = "cancel";
        this.showModal = true;
      }
    },
    closeModal() {
      this.showModal = false;
    },
    performAction() {
      if (this.action === "confirm") {
        this.saveEvent();
        this.closeModal();
      } else if (this.action === "cancel") {
        this.cancelEvent();
        this.closeModal();
      }
    },
    cancelAction() {
      this.closeModal();
    }
  }
}
</script>

<style scoped>
.has-error input, .has-error select {
  border-color: red;
  box-shadow: 0 0 10px red;
}

.modal {
  margin-top: -20px;
}
</style>