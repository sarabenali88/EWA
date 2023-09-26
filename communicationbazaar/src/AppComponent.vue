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
export default {
  name: 'App',
  components: {
    NavBarComponent,
    HeaderComponent,
  }
}
</script>

<style scoped>

#mainContent {
  margin-left: 0;
}

@media (min-width: 992px) {
  #mainContent {
    margin-top: -40px;
    margin-left: 150px;
  }
}
</style>
