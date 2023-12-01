<template>
  <div class="container">
    <div class="row mt-5 border border-secondary rounded py-4">
      <div class="col-md-2 how-img">
        <img src="../assets/Portrait_Placeholder.png" class="rounded-circle img-fluid size_picture border border-danger border-3"
             alt=""/>
      </div>
      <div class="col-md-8 py-5">
          <div class="card-body">
            <div class="row">
              <div class="col-sm-3">
                <h3 class="mb-0">
                  {{ name }}
                </h3>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Functie</h6>
              </div>
              <div class="col-sm-9">
                {{ role }}
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Werknemer nr.</h6>
              </div>
              <div class="col-sm-9">
                {{ personalNumber }}
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Locatie</h6>
              </div>
              <div class="col-sm-9">
                {{ location }}
              </div>
            </div>
          </div>
      </div>
    </div>
    <table class="table table-sm">
      <thead>
      <tr>
        <th scope="col">EAN</th>
        <th scope="col">Laptop naam</th>
        <th scope="col">Medewerker</th>
        <th scope="col">Vestiging</th>
        <th scope="col">Status</th>
        <th scope="col">Datum</th>
      </tr>
      </thead>
      <tbody>


      
      </tbody>
    </table>

  </div>
</template>

<script>
import NavBar from "@/frontend/NavBarComponent";

export default {
  name: "ProfilePageComponent",

  inject: ["accountsService"],
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.createInformation();
    });
  },
  data() {
    return {
      personalNumber: '',
      name: '',
      email: '',
      role: '',
      location: '',
      imagesOnGoing: [],
      imagesDone: [],
      accounts: [],
      loggedInAccount: undefined
    }
  },
  created() {
    this.createInformation();
  },
  watch: {
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
    async createInformation() {
      this.accounts = await this.accountsService.asyncFindAll();
      this.loggedInAccount = this.accounts.find(account => account.loggedIn);
      if (!this.loggedInAccount || !this.loggedInAccount.loggedIn) {
        this.$router.push(NavBar.data().signInRoute);
      } else {
        this.setInformation();
      }
    },
    setInformation() {
      this.personalNumber = this.loggedInAccount.personalNumber;
      this.name = this.loggedInAccount.name;
      this.email = this.loggedInAccount.email;
      this.role = this.loggedInAccount.role;
      this.imagesOnGoing = this.loggedInAccount.imagesOnGoing;
      this.imagesDone = this.loggedInAccount.imagesDone;
      this.location = this.loggedInAccount.location;
    }
  },
}
</script>

<style scoped>
.border-3 {
  border-width: 3px !important;
}

h5 {
  color: lightgray;
}

</style>
