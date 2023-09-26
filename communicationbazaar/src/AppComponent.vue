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
  margin-left: 150px;
}
</style>
