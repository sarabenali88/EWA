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

import HeaderComponent from "@/frontend/HeaderComponent";
import NavBarComponent from "@/frontend/NavBarComponent";
import "./main.css"
export default {
  name: 'App',
  components: {
    NavBarComponent,
    HeaderComponent,
  }
}
</script>

<style scoped>

.all {
  overflow: hidden;
}
#mainContent {
  margin-left: 0;
  max-height: calc(100vh + 100px);
}

@media (min-width: 992px) {
  #mainContent {
    margin-top: -50px;
    margin-left: 300px;
    max-height: calc(100vh - 100px);
    overflow-y: auto;
  }
}

@media (max-width: 500px) {
  #mainContent {
    overflow: auto;
    padding-bottom: 200px;
  }

}
</style>
