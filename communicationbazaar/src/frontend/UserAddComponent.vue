<template>
  <div class="card h-100">
    <div class="card-body">
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <h6 class="mb-2 text-danger">Account Toevoegen</h6>
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
            <button @click="cancelEvent()" class="btn btn-secondary">Annuleren</button>
            <button @click="saveEvent()" class="btn btn-success m-lg-2">OK</button>
          </div>
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
      role: ""
    }
  },
  methods: {
    cancelEvent() {
      if (confirm("Weet je zeker dat je wilt annuleren?")) {
        this.$emit('cancelEvent', null);
      }
    },
    saveEvent() {
      if (this.fieldsFilledCheck()) {
        if (confirm("Weet u zeker dat u de gebruiker wilt toevoegen?")) {
          this.account = new Account(this.defaultPersonalNumber, this.password, this.name, this.email, this.role, [], [], false)
          this.$emit('saveEvent', this.account);
        }
      }
    },
    fieldsFilledCheck() {
      if (!this.name || !this.email || !this.role || !this.password) {
        alert("Niet elk veld is ingevuld")
        return false;
      } else {
        return true;
      }
    }
  }
}
</script>

<style scoped>
.has-error input, .has-error select {
  border-color: red;
  box-shadow: 0 0 10px red;
}
</style>