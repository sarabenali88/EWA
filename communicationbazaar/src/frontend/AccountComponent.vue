<template>
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col col-lg-6 mb-4 mb-lg-0">
        <div class="card mb-3">
          <div class="row g-0">
            <div class="col-md-4 gradient-custom text-center text-white">
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                   alt="Avatar" class="img-fluid my-5" id="profilePicture"/>
            </div>
            <div class="col-md-8">
              <div class="card-body p-4">
                <h6>Information</h6>
                <hr class="mt-0 mb-4">
                <div class="row pt-1">
                  <div class="col-6 mb-3">
                    <h6>Name</h6>
                    <p class="text-muted">{{ name }}</p>
                  </div>
                  <div class="col-6 mb-3">
                    <h6>Email</h6>
                    <p class="text-muted">{{ email }}</p>
                  </div>
                  <div class="col-6 mb-3">
                    <h6>Role</h6>
                    <p class="text-muted">{{ role }}</p>
                  </div>
                </div>
                <h6>Projects</h6>
                <hr class="mt-0 mb-4">
                <div class="row pt-1">
                  <div class="col-6 mb-3">
                    <h6>On going</h6>
                    <ul>
                      <li v-for="image in imagesOnGoing" :key="image.laptop.ean">
                        <p>{{ image.laptop.ean }}</p>
                      </li>
                    </ul>
                  </div>
                  <div class="col-6 mb-3">
                    <h6>Done</h6>
                    <ul>
                      <li v-for="image in imagesDone" :key="image.laptop.ean">
                        <p>{{ image.laptop.ean }}</p>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import NavBar from "@/frontend/NavBarComponent";

export default {
  name: "AccountComponent",
  inject: ["accountsService"],
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.createInformation();
    });
  },
  data() {
    return {
      name: '',
      email: '',
      role: '',
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
      this.name = this.loggedInAccount.name;
      this.email = this.loggedInAccount.email;
      this.role = this.loggedInAccount.role;
      this.imagesOnGoing = this.loggedInAccount.imagesOnGoing;
      this.imagesDone = this.loggedInAccount.imagesDone;
    }
  },
}
</script>

<style scoped>

#profilePicture {
  width: 80px;
}

li {
  list-style: none;
}
</style>