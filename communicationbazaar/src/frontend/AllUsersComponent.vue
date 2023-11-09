<template>
  <h1>Gebruikers lijst</h1>

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


</template>

<script>
import UserDetailComponent from "@/frontend/UserDetailComponent";
import NavBarComponent from "@/frontend/NavBarComponent";

export default {
  name: "AllUsersComponent",
  components: UserDetailComponent,

  inject: ["accountsService"],
  emits: ['cancelEvent', 'saveEvent'],
  data() {
    return {
      accounts: [],
      selectedAccount: null
    }
  },
  async created() {
    this.accounts = await this.accountsService.asyncFindAll();
  },
  watch: {
    '$route'() {
      if (this.$route.path.match(NavBarComponent.data().allUsersRoute) && this.accounts.find(account => account.personalNumber === this.$route.params.id)) {
        this.selectedAccount = this.findSelectedFromRouteParams(this.$route.params.id);
      }

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
      } else  if (this.$route.path === NavBarComponent.data().allUsersRoute + '/userAdd') {
        if (confirm("Weet u zeker dat u geen gebruiker wilt toevoegen?")) {
          this.selectedAccount = account;
          this.$router.push(NavBarComponent.data().allUsersRoute + '/' + account.personalNumber);
        }
      } else {
        this.selectedAccount = account;
        this.$router.push(NavBarComponent.data().allUsersRoute + '/' + account.personalNumber);
      }
    },
    addAccount() {
      if (this.selectedAccount === null) {
        this.$router.push(NavBarComponent.data().allUsersRoute + '/userAdd');
      } else if (this.$route.path === NavBarComponent.data().allUsersRoute + '/' + this.selectedAccount.personalNumber) {
        if (confirm("Weet u zeker dat u het wijzigen wilt annuleren?")) {
          this.selectedAccount = null;
          this.$router.push(NavBarComponent.data().allUsersRoute + '/userAdd');
        }
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
      if (confirm("Weet je zeker dat je dit account wilt verwijderen?")) {
        const indexToDelete = this.accounts.indexOf(this.accounts.find((account) => account.personalNumber === account.personalNumber));
        this.accounts.splice(indexToDelete, 1);
        await this.accountsService.asyncDeleteById(account.personalNumber);
      }
    }
  }
}
</script>

<style scoped>

h1, #addButton {
  margin-left: 40px;
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

</style>