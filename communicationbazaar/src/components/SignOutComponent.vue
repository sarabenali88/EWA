<template>
  <h1>SignOut</h1>
</template>

<script>
import navBarComponent from "@/components/NavBarComponent";

export default {
  name: 'SignOutComponent',
  inject: ['sessionService'],
  created () {
    this.sessionService.signOut()
    this.$router.push(navBarComponent.data().signInRoute)
  }
}
</script>

<style scoped>

</style>
