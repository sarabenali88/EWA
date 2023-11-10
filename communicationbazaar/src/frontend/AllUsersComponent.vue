<template>
  <h1>Gebruikerslijst</h1>

  <div v-if="showAlert" class="alert alert-success alert-dismissible fade show" role="alert" id="alert">
    {{ alertMessage }}
    <button type="button" class="btn-close" @click="dismissAlert" aria-label="Close"></button>
  </div>

  <div class="container-fluid px-5">
    <router-view :currentAccount="getCurrentAccount()" @cancelEvent="cancelEvent" @saveEvent="saveEvent">

    </router-view>
  </div>

  <button class="btn btn-secondary btn-round" id="addButton" @click="addAccount()">Gebruiker toevoegen</button>

  <div class="container-fluid p-3">
    <ul>
      <li v-for="account in accounts" :key="account.personalNumber">
        <div class="col-xl-6 col-lg-7 col-md-12">
          <div class="card profile-header">
            <div class="body">
              <div class="row">
                <div class="col-lg-4 col-md-4 col-12">
                  <img class="w-50 rounded-circle mt-4 m-lg-4" src="https://bootdey.com/img/Content/avatar/avatar6.png"
                       alt="">
                </div>
                <div class="col-lg-8 col-md-8 col-12">
                  <h4 class="m-t-0 m-b-0"><strong>{{ account.name }}</strong></h4>
                  <span class="job_post">{{ account.role }}</span>
                  <p>{{ account.email }}</p>
                  <div>
                    <button class="btn btn-secondary btn-round" @click="updateAccount(account)">Wijzigen</button>
                    <button class="btn btn-danger btn-round" @click="deleteAccount(account)">Verwijderen</button>
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
import UserDetailComponent from "@/frontend/UserDetailComponent";
import NavBarComponent from "@/frontend/NavBarComponent";
import NavBar from "@/frontend/NavBarComponent";

export default {
  name: "AllUsersComponent",
  components: UserDetailComponent,

  inject: ["accountsService"],
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
      loggedInAccount: undefined,
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
    loggedInAccount: {
      handler: function (newVal, oldVal) {
        if (newVal !== oldVal) {
          this.setInformation();
        }
      },
      deep: true
    }
  },
  methods: {
    findSelectedFromRouteParams(id) {
      if (id > 0) {
        return this.accounts.find(account => account.personalNumber === id);
      }
      return null;
    },
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
    addAccount() {
      if (this.selectedAccount === null) {
        this.$router.push(NavBarComponent.data().allUsersRoute + '/userAdd');
      } else if (this.$route.path === NavBarComponent.data().allUsersRoute + '/' + this.selectedAccount.personalNumber) {
        this.showConfirmModal("fromAdd");
      }
    },
    getCurrentAccount() {
      return this.selectedAccount;
    },
    cancelEvent(selectedAccount) {
      this.$router.push(NavBarComponent.data().allUsersRoute);
      this.selectedAccount = selectedAccount;
    },
    async saveEvent(account) {
      if (account.personalNumber === 0) {
        const newAccount = await this.accountsService.asyncSave(account);
        this.accounts.push(newAccount);
        this.displayAlert("Nieuw account voor " + newAccount.name + " is aangemaakt met personeelsnummer " + newAccount.personalNumber);
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
    async deleteAccount(account) {
      this.confirmAccount = account;
      this.showConfirmModal("delete");
    },
    async createInformation() {
      this.accounts = await this.accountsService.asyncFindAll();
      this.loggedInAccount = this.accounts.find(account => account.loggedIn);
      if (!this.loggedInAccount || !this.loggedInAccount.loggedIn && this.loggedInAccount.role !== "admin") {
        this.$router.push(NavBar.data().homeRoute);
      } else {
        this.setInformation();
      }
    },
    setInformation() {
    },
    displayAlert(message) {
      this.alertMessage = message;
      this.showAlert = true;
    },
    dismissAlert() {
      this.showAlert = false;
      this.alertMessage = '';
    },
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
    closeModal() {
      this.showModal = false;
    },
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
        this.displayAlert("Gebruiker " + this.confirmAccount.name + " is succesvol verwijderd");
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

h1, #addButton {
  margin-left: 40px;
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

#alert {
  margin-left: 40px;
  margin-right: 30px;
}

.modal {
  margin-top: -20px;
}
</style>