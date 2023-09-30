<template>
  <h1>
    Image list
  </h1>
  <ul>
  <li>
    <router-link :to="addImageRoute" :class="{'active-tab': $route.path === addImageRoute}">
      <button>
      add Image
      </button>
    </router-link>
  </li>
  </ul>
</template>

<script>
export default {
  name: "ImageListComponent",

  data() {
    return {
      addImageRoute: '/addImage'
    }
  }
}
</script>

<style scoped>

</style>