<template>
  <h1>SignOut</h1>
</template>

<script>
/**
 * Sign out component is called when the sign out button is pressed.
 * It will activate the signOut function in the session service.
 * And it will relocate the user to the sign in view.
 *
 * @author Jasper Fernhout
 */
import navBarComponent from "@/components/NavBarComponent";

export default {
  name: 'SignOutComponent',
  inject: ['sessionService'],
  created() {
    this.sessionService.signOut()
    this.$router.push(navBarComponent.data().signInRoute)
  }
}
</script>

<style scoped>

</style>
