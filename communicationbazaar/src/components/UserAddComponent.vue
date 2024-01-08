<template>
  <div class="card h-100">
    <div class="card-body">
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <h6 class="mb-2 text-danger">{{ $t('adminPanel.addAccount') }}</h6>
        </div>
        <div v-if="showAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
          {{ alertMessage }}
          <button type="button" class="btn-close" @click="dismissAlert" aria-label="Close"></button>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !name }">
            <label for="fullName">{{ $t('adminPanel.name') }}</label>
            <input v-model="name" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !email }">
            <label for="eMail">{{ $t('adminPanel.email') }}</label>
            <input v-model="email" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !role }">
            <label for="zIp">{{ $t('adminPanel.role') }}</label>
            <select v-model="role" class="form-select" aria-label="Default select example">
              <option value="" disabled selected>{{ $t('adminPanel.select') }}</option>
              <option value="ImageMaker">{{ $t('adminPanel.optionOne') }}</option>
              <option value="admin">{{ $t('adminPanel.optionTwo') }}</option>
            </select>
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !location }">
            <label for="website">{{ $t('adminPanel.location') }}</label>
            <select v-model="location" class="form-select" aria-label="Default select example">
              <option value="" disabled selected>{{ $t('adminPanel.select') }}</option>
              <option value="Hoorn">Hoorn</option>
              <option value="Amsterdam">Amsterdam</option>
              <option value="Apeldoorn">Apeldoorn</option>
              <option value="Leeuwarden">Leeuwarden</option>
              <option value="Groningen">Groningen</option>
            </select>
          </div>
        </div>
      </div>
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <div class="text-right">
            <button @click="showConfirmModal(cancel)" class="btn btn-secondary">{{ $t('adminPanel.cancelButton') }}
            </button>
            <button @click="showConfirmModal(confirm)" class="btn btn-success m-lg-2">{{ $t('adminPanel.okButton') }}
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="modal" tabindex="-1" role="dialog" style="display: block;" v-if="showModal">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">{{ $t('adminPanel.confirmation') }}</h5>
          <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p>{{ $t('adminPanel.confirmMessage') }}</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" @click="cancelAction()">{{ $t('adminPanel.cancelButton') }}
          </button>
          <button type="button" class="btn btn-success" @click="performAction()">{{ $t('adminPanel.okButton') }}
          </button>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
/**
 * This is the component for the functionalities of adding a user.
 *
 * @author Jasper Fernhout
 */
import {Account} from "@/models/Account";

export default {
  name: "UserAddComponent",
  data() {
    return {
      account: null,
      defaultPersonalNumber: 0,
      name: "",
      email: "",
      password: "welkom",
      role: "",
      location: "",
      showAlert: false,
      alertMessage: '',
      showModal: false,
      cancel: "cancel",
      confirm: "confirm",
      action: ""
    }
  },
  methods: {
    /**
     * This will emit the event call that is worked out in the parent component.
     * The cancel event is linked to clicking the cancel button in the field.
     *
     * @author Jasper Fernhout
     */
    cancelEvent() {
      this.$emit('cancelEvent', null);
    },
    /**
     * This will emit the event call that is worked out in the parent component.
     * The save event is linked to clicking the save button in the field.
     * It will give back a new instance of account.
     *
     * @author Jasper Fernhout
     */
    saveEvent() {
      this.account = new Account(this.defaultPersonalNumber, this.password, this.name, this.email, this.role, this.location, false)
      this.$emit('saveEvent', this.account);
    },
    /**
     * This is a method that will check if every field is filled in.
     * If a field is not filled in, it will give back a false.
     * Else it will give back a true and then the newly created account can be added.
     *
     * @returns {boolean}
     * @author Jasper Fernhout
     */
    fieldsFilledCheck() {
      if (!this.name || !this.email || !this.role || !this.password || !this.location) {
        this.displayAlert(this.$t('adminPanel.errorMessage'))
        return false;
      } else {
        return true;
      }
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
     * A method that wil show the confirmation modal with the given action.
     *
     * @param cancelOrConfirm gives the action that needs to be done when the button is pressed in the modal.
     * @author Jasper Fernhout
     */
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
    /**
     * This method will be called if the x button is pressed in the modal.
     * The modal will close afterwards.
     *
     * @author Jasper Fernhout
     */
    closeModal() {
      this.showModal = false;
    },
    /**
     * An event that will save a newly added account to the list and will send a request to the back end to save.
     * Also an event that will update an already existing account in the front end and will send a request to the back end.
     * And a method that will delete an account in the front end and will send a delete request to the back end.
     *
     * @returns {Promise<void>}
     * @author Jasper Fernhout
     */
    performAction() {
      if (this.action === "confirm") {
        this.saveEvent();
        this.closeModal();
      } else if (this.action === "cancel") {
        this.cancelEvent();
        this.closeModal();
      }
    },
    /**
     * Method that will close the confirmation modal when the cancel button is clicked.
     *
     * @author Jasper Fernhout
     */
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