<template>
  <div>
    <h1>
      OOPS AN ERROR HAS OCCURED...
    </h1>
    <h2>
      THERE IS NO COMPONENT UNDER THE PATH {{ $route.path }}
    </h2>
  </div>
</template>

<script>
export default {
  name: 'UnknownRoute'
}
</script>

<style scoped>

div {
  background-color: lightblue;
}

h1, h2 {
  color: black;
}

</style>
