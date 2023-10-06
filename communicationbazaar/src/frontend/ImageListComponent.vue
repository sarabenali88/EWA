<template>
  <h1>
    Image list
  </h1>
  <ul>
  <li>
    <router-link :to="'/addImage'">
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
}
</script>

<style scoped>

</style>