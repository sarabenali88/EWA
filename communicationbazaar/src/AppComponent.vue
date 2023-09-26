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
  max-height: calc(100vh - 100px);
  overflow-y: auto;
}

@media (min-width: 992px) {
  #mainContent {
    margin-top: -40px;
    margin-left: 150px;
    max-height: calc(100vh - 100px);
    overflow-y: auto;
  }
}
</style>
