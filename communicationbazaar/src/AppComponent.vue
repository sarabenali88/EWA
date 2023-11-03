<template>
  <HeaderComponent/>
  <NavBarComponent/>
  <div id="mainContent">
    <router-view/>
  </div>
</template>

<script>

import HeaderComponent from "@/frontend/HeaderComponent";
import NavBarComponent from "@/frontend/NavBarComponent";
import CONFIG from "@/app-config";
import { ImagesAdaptor } from "@/services/ImagesAdaptor";
import { LaptopsAdaptor } from "@/services/LaptopsAdaptor";
import { AccountsAdaptor } from "@/services/AccountsAdaptor";

export default {
  name: 'App',
  components: {
    NavBarComponent,
    HeaderComponent,
  },
  provide() {
    return {
      imagesService: new ImagesAdaptor(CONFIG.BACKEND_URL + '/images'),
      laptopsService: new LaptopsAdaptor(CONFIG.BACKEND_URL + '/laptops'),
      accountsService: new AccountsAdaptor(CONFIG.BACKEND_URL + '/accounts')
    }
  }
}
</script>

<style scoped>

#mainContent {
  margin-left: 0;
  max-height: calc(100vh - 100px);
  overflow-y: auto;
}

@media (min-width: 992px) {
  #mainContent {
    margin-top: -40px;
    margin-left: 20%;
    max-height: calc(100vh - 100px);
    overflow-y: auto;
  }
}
</style>
