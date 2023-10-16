<template>
  <h1>User List</h1>

  <div class="container-fluid px-5">
    <router-view :currentAccount="getCurrentAccount()" @cancelEvent="cancelEvent">

    </router-view>
  </div>

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
                    <button class="btn btn-primary btn-round" @click="setAccount(account)">Wijzigen</button>
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
import accounts from "@/account.json";
import UserDetailComponent from "@/frontend/UserDetailComponent";
import NavBarComponent from "@/frontend/NavBarComponent";

export default {
  name: "AllUsersComponent",
  components: UserDetailComponent,

  emits: ['cancelEvent'],
  data() {
    return {
      accounts: [],
      selectedAccount: null
    }
  },
  created() {
    for (let i in accounts) {
      this.accounts.push(accounts[i]);
    }
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
    setAccount(account) {
      if (account === this.selectedAccount) {
        this.selectedAccount = null;
        this.$router.push(NavBarComponent.data().allUsersRoute);
      } else {
        this.selectedAccount = account;
        this.$router.push(NavBarComponent.data().allUsersRoute + '/' + account.personalNumber);
      }
      console.log(this.selectedAccount)
    },
    getCurrentAccount() {
      return this.selectedAccount;
    },
    cancelEvent(selectedAccount) {
      this.selectedAccount = selectedAccount;
    }
  }
}
</script>

<style scoped>

h1 {
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