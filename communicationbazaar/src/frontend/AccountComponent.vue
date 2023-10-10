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
                    <p class="text-muted">Lorem ipsum</p>
                  </div>
                  <div class="col-6 mb-3">
                    <h6>Done</h6>
                    <p class="text-muted">Dolor sit amet</p>
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

import json from "@/account.json";

export default {
  name: "AccountComponent",

  data() {
    return {
      name: '',
      email: '',
      role: '',
      array: [],
    }
  },
  created() {
    this.createInformation();
  },
  methods: {
    createInformation() {

      const user = json.find(account => account.loggedIn);
      this.name = user.name;
      this.role = user.role;
      this.email = user.email;
    }
  }
}
</script>

<style scoped>

#profilePicture {
  width: 80px;
}
</style>