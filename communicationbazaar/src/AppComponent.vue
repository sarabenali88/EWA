<template>
  <div class="all">
    <HeaderComponent/>
    <NavBarComponent/>
    <div id="mainContent" >
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

import "./main.css"
export default {
  name: 'App',
  components: {
    NavBarComponent,
    HeaderComponent,
  },
  provide() {
    return {
      imagesService: new ImagesAdaptor('/images'),
      laptopsService: new LaptopsAdaptor('/laptops'),
      accountsService: new AccountsAdaptor('/accounts')
    }
  },
  created() {
    this.$router.push("/home");
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
