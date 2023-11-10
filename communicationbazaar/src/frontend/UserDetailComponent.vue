<template>
  <div class="card h-100">
    <div class="card-body">
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <h6 class="mb-2 text-danger">Account Details</h6>
        </div>
        <div v-if="showAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
          {{ alertMessage }}
          <button type="button" class="btn-close" @click="dismissAlert" aria-label="Close"></button>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !accountCopy.name }">
            <label for="fullName">Naam</label>
            <input v-model="accountCopy.name" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !accountCopy.email }">
            <label for="eMail">Email</label>
            <input v-model="accountCopy.email" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="phone">Personeelsnummer</label>
            <input v-model="accountCopy.personalNumber" class="form-control" readonly>
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="website">Wachtwoord</label>
            <input v-model="accountCopy.password" class="form-control" type="password" readonly>
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !accountCopy.role }">
            <label for="zIp">Rol</label>
            <select v-model="accountCopy.role" class="form-select" aria-label="Default select example">
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
export default {
  name: "UserDetailComponent",
  props: ['currentAccount'],
  data() {
    return {
      accountCopy: null,
      showAlert: false,
      alertMessage: '',
      showModal: false,
      cancel: "cancel",
      confirm: "confirm",
      action: ""
    }
  },
  created() {
    this.copyAccount(this.currentAccount);
  },
  watch: {
    currentAccount: {
      handler(newAccount) {
        if (newAccount !== null) {
          this.copyAccount(newAccount);
        }
      },
      deep: true,
    }
  },
  methods: {
    cancelEvent() {
      this.$emit('cancelEvent', null);
    },
    saveEvent() {
      this.$emit('saveEvent', this.accountCopy);
    },
    copyAccount(currentAccount) {
      this.accountCopy = JSON.parse(JSON.stringify(currentAccount));
    },
    hasChanged() {
      return this.compareAccounts(this.accountCopy, this.currentAccount);
    },
    compareAccounts(accountCopy, currentAccount) {
      if (!accountCopy || !currentAccount) {
        return false;
      }

      return accountCopy.personalNumber === currentAccount.personalNumber &&
          accountCopy.password === currentAccount.password &&
          accountCopy.name === currentAccount.name &&
          accountCopy.email === currentAccount.email &&
          accountCopy.role === currentAccount.role;
    },
    fieldsFilledCheck(accountCopy) {
      if (!accountCopy.name || !accountCopy.email || !accountCopy.role) {
        this.displayAlert("Niet elk veld is ingevuld!");
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
        if (this.fieldsFilledCheck(this.accountCopy)) {
          if (!this.hasChanged()) {
            this.dismissAlert();
            this.action = "confirm";
            this.showModal = true;
          } else {
            this.cancelEvent();
          }
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