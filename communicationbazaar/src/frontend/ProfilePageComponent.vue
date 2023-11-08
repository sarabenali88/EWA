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
                <h3 class="mb-0">Cindy Visser</h3>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Functie</h6>
              </div>
              <div class="col-sm-9">
                Mesh Maker
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Werknemer nr.</h6>
              </div>
              <div class="col-sm-9">
                85819239
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <h6 class="mb-0 text-secondary">Locatie</h6>
              </div>
              <div class="col-sm-9">
                Sontplein
              </div>
            </div>
          </div>
      </div>
    </div>
    <router-view></router-view>
    <div class="ms-lg-3">
      <button type="button" class="btn btn-danger" @click="$router.push('/addImage')">
        add Image
      </button>
    </div>
  </div>
</template>

<script>
import account from '@/profile.json'

export default {
  name: "ProfilePageComponent",
  data() {
    return {
      data: account
    }
  }
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
