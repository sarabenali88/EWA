<template>
  <div class="container">
    <div class="row mt-5 border border-secondary rounded py-4">
      <div class="col-md-2 how-img">
        <img src="../assets/Cindy.jpg" class="rounded-circle img-fluid size_picture border border-danger border-3"
             alt=""/>
      </div>
      <div class="col-md-8 py-5">
          <div class="card-body">
            <div class="row">
              <div class="col-sm-3">
                <h3 v-for="item in data" :key="item.id" class="mb-0">
                  {{item.firstName}} {{item.lastName}}
                </h3>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Functie</h6>
              </div>
              <div v-for="item in data" :key="item.id" class="col-sm-9">
                {{item.function}}
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Werknemer nr.</h6>
              </div>
              <div v-for="item in data" :key="item.id" class="col-sm-9">
                {{item.employeeNumber}}
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Locatie</h6>
              </div>
              <div v-for="item in data" :key="item.id" class="col-sm-9">
                {{item.address}}
              </div>
            </div>
          </div>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import account from '@/profile.json';

export default {
  name: "ProfilePageComponent",

  data() {
    return {
      data: account
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
