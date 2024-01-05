<template>
  <div class="all">
    <HeaderComponent/>
    <NavBarComponent/>
    <div id="mainContent">
      <router-view/>
    </div>
  </div>

</template>

<script>

import HeaderComponent from "@/components/HeaderComponent";
import NavBarComponent from "@/components/NavBarComponent";
import { ImagesAdaptor } from "@/services/ImagesAdaptor";
import { LaptopsAdaptor } from "@/services/LaptopsAdaptor";
import { AccountsAdaptor } from "@/services/AccountsAdaptor";
import CONFIG from "@/app-config";
import {shallowReactive} from 'vue'
import {SessionSbService} from '@/services/SessionSbService';
import { FetchAdaptor } from '@/services/FetchAdaptor';

import "./main.css"
export default {
  name: 'App',
  components: {
    NavBarComponent,
    HeaderComponent,
  },
  provide() {
    this.SessionSbService = shallowReactive(
        new SessionSbService(CONFIG.BACKEND_URL + '/authentication', CONFIG.JWT_STORAGE_ITEM)
    )
    this.FetchAdaptor = new FetchAdaptor(this.SessionSbService, this.$router)
    return {
      imagesService: new ImagesAdaptor('/images'),
      laptopsService: new LaptopsAdaptor('/laptops'),
      accountsService: new AccountsAdaptor('/accounts'),
      sessionService: this.SessionSbService
    }
  },
  created() {
    this.$router.push("/home");

  },
  unmounted() {
    console.log('App.unmounted() has been called.')
    this.FetchAdaptor.unregister()
  }
}
</script>

<style scoped>


.all {
  overflow: hidden;
  font-family: Inter, sans-serif;
  font-weight: lighter;
}

#mainContent {
  margin-left: 0;
  max-height: calc(100vh + 100px);
  z-index: -10;
}

@media (min-width: 700px) {
  #mainContent {
    margin-top: -50px;
    margin-left: 330px;
    max-height: calc(100vh - 100px);
    overflow-y: auto;
  }
}

@media (max-width: 700px) {
  #mainContent {
    overflow: auto;
    padding-bottom: 200px;
    padding-top: 75px;
  }

}
</style>
