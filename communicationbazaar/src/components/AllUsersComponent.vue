<template>
  <div class="all">
    <h1>{{ $t('adminPanel.headTitle') }}</h1>
    <div v-if="showAlert" class="alert alert-success alert-dismissible fade show" role="alert" id="alert">
      {{ alertMessage }}
      <button type="button" class="btn-close" @click="dismissAlert" aria-label="Close"></button>
    </div>

    <div class="container-fluid px-5">
      <router-view :currentAccount="getCurrentAccount()" @cancelEvent="cancelEvent" @saveEvent="saveEvent">

      </router-view>
    </div>

    <button class="btn btn-secondary btn-round" id="addButton" @click="addAccount()">{{ $t('adminPanel.addUser') }}
    </button>

    <div class="container-fluid p-3">
      <ul>
        <li v-for="account in accounts" :key="account.personalNumber">
          <div class="col-xl-6 col-lg-7 col-md-12">
            <div class="card profile-header">
              <div class="body">
                <div class="row">
                  <div class="col-lg-4 col-md-4 col-12">
                    <img class="w-50 rounded-circle mt-4 m-lg-4"
                         src="https://bootdey.com/img/Content/avatar/avatar6.png"
                         alt="">
                  </div>
                  <div class="col-lg-8 col-md-8 col-12">
                    <h4 class="m-t-0 m-b-0"><strong>{{ account.name }}</strong></h4>
                    <p class="job_post">{{ account.role }}</p>
                    <p class="job_post">{{ account.email }}</p>
                    <p class="job_post">{{ account.location }}</p>
                    <div>
                      <button class="btn btn-secondary btn-round" @click="updateAccount(account)">
                        {{ $t('adminPanel.editButton') }}
                      </button>
                      <button class="btn btn-danger btn-round" @click="deleteAccount(account)">
                        {{ $t('adminPanel.deleteButton') }}
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </li>
      </ul>
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
            <button type="button" class="btn btn-secondary" @click="cancelAction()">
              {{ $t('adminPanel.cancelButton') }}
            </button>
            <button type="button" class="btn btn-success" @click="performAction()">{{ $t('adminPanel.okButton') }}
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
/**
 * This is the component for the functionalities of the list with all users.
 *
 * @author Jasper Fernhout
 */
import UserDetailComponent from "@/components/UserDetailComponent";
import NavBarComponent from "@/components/NavBarComponent";
import NavBar from "@/components/NavBarComponent";

export default {
  name: "AllUsersComponent",
  components: UserDetailComponent,

  inject: ["accountsService", "sessionService"],
  emits: ['cancelEvent', 'saveEvent'],
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.createInformation();
    });
  },
  data() {
    return {
      accounts: [],
      selectedAccount: null,
      showAlert: false,
      alertMessage: '',
      showModal: false,
      fromUpdate: "fromUpdate",
      fromAdd: "fromAdd",
      action: "",
      confirmAccount: undefined
    }
  },
  created() {
    this.createInformation();
  },
  watch: {
    '$route'() {
      if (this.$route.path.match(NavBarComponent.data().allUsersRoute) && this.accounts.find(account => account.personalNumber === this.$route.params.id)) {
        this.selectedAccount = this.findSelectedFromRouteParams(this.$route.params.id);
      }
    },
  },
  methods: {

    /**
     * Method that will open the window to update the information of an account.
     *
     * @param account the account check if there is an account already selected.
     * @author Jasper Fernhout
     */
    updateAccount(account) {
      if (account === this.selectedAccount) {
        this.selectedAccount = null;
        this.$router.push(NavBarComponent.data().allUsersRoute);
      } else if (this.$route.path === NavBarComponent.data().allUsersRoute + '/userAdd') {
        this.confirmAccount = account;
        this.showConfirmModal("fromUpdate");
      } else {
        this.selectedAccount = account;
        this.$router.push(NavBarComponent.data().allUsersRoute + '/' + account.personalNumber);
      }
    },

    /**
     * method that will open the window to add an account.
     *
     * @author Jasper Fernhout
     */
    addAccount() {
      if (this.selectedAccount === null) {
        this.$router.push(NavBarComponent.data().allUsersRoute + '/userAdd');
      } else if (this.$route.path === NavBarComponent.data().allUsersRoute + '/' + this.selectedAccount.personalNumber) {
        this.showConfirmModal("fromAdd");
      }
    },

    /**
     * Method that gets the current account that is selected.
     *
     * @returns {null} Gives back the account that is selected.
     * @author Jasper Fernhout
     */
    getCurrentAccount() {
      return this.selectedAccount;
    },

    /**
     * An event that is called when a cancel button is pressed.
     *
     * @param selectedAccount will set the selectedAccount to null if the cancel event is activated.
     * @author Jasper Fernhout
     */
    cancelEvent(selectedAccount) {
      this.$router.push(NavBarComponent.data().allUsersRoute);
      this.selectedAccount = selectedAccount;
    },

    /**
     * A method that will give the needed information to the confimation modal to what action has to be done.
     *
     * @param account the account that has to be added or updated.
     * @returns {Promise<void>} returns the added or updated account.
     * @author Jasper Fernhout
     */
    async saveEvent(account) {
      if (account.personalNumber === 0) {
        const newAccount = await this.accountsService.asyncSave(account);
        this.accounts.push(newAccount);
        this.displayAlert(this.$t('adminPanel.newAccount', {
          name: newAccount.name,
          employeeNumber: newAccount.personalNumber
        }));
        this.$router.push(NavBarComponent.data().allUsersRoute);
      } else {
        const updatedData = JSON.parse(JSON.stringify(account));
        let userIndex = this.accounts.indexOf(this.selectedAccount);
        this.accounts[userIndex] = updatedData;
        this.$router.push(NavBarComponent.data().allUsersRoute);
        await this.accountsService.asyncSave(updatedData);
        this.selectedAccount = null;
      }
    },

    /**
     * A method that will give the needed information to the confimation modal to what action has to be done.
     *
     * @param account the account that has to be deleted.
     * @returns {Promise<void>} gives back the deleted account.
     * @author Jasper Fernhout
     */
    async deleteAccount(account) {
      this.confirmAccount = account;
      this.showConfirmModal("delete");
    },

    /**
     * A method that will create the needed information before the route will be entered.
     *
     * @returns {Promise<void>}
     * @author Jasper Fernhout
     */
    async createInformation() {
      this.accounts = await this.accountsService.asyncFindAll();
      if (!this.sessionService.isAuthenticated() || this.sessionService.isAuthenticatedAndImageMaker()) {
        this.$router.push(NavBar.data().homeRoute);
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
     * @param confirmAction gives the action that needs to be done when the button is pressed in the modal.
     * @author Jasper Fernhout
     */
    showConfirmModal(confirmAction) {
      if (confirmAction === "fromUpdate") {
        this.action = "fromUpdate";
        this.showModal = true;
      } else if (confirmAction === "fromAdd") {
        this.action = "fromAdd";
        this.showModal = true;
      } else if (confirmAction === "delete") {
        this.action = "delete";
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
    async performAction() {
      if (this.action === "fromUpdate") {
        this.selectedAccount = this.confirmAccount;
        this.$router.push(NavBarComponent.data().allUsersRoute + '/' + this.selectedAccount.personalNumber);
        this.closeModal();
      } else if (this.action === "fromAdd") {
        this.selectedAccount = null;
        this.$router.push(NavBarComponent.data().allUsersRoute + '/userAdd');
        this.closeModal();
      } else if (this.action === "delete") {
        const indexToDelete = this.accounts.indexOf(this.accounts.find((account) => account.personalNumber === this.confirmAccount.personalNumber));
        this.accounts.splice(indexToDelete, 1);
        await this.accountsService.asyncDeleteById(this.confirmAccount.personalNumber);
        this.displayAlert(this.$t('adminPanel.deleteUser', {name: this.confirmAccount.name}));
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

h1, #addButton {
  margin-left: 50px;
}

#addButton {
  margin-top: 10px;
}

.card {
  margin-bottom: 30px;
  width: 100%;
}

.card .body {
  font-size: 14px;
  color: #424242;
  padding: 20px;
  font-weight: 400;
}

li {
  list-style: none;
}

@media (max-width: 700px) {
  .all {
    margin-top: 10px;
    margin-left: -35px;
  }

}

#alert {
  margin-left: 40px;
  margin-right: 30px;
}

.modal {
  margin-top: -20px;
}

p {
  margin-bottom: 0px;
}
</style>