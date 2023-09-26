<template>
  <div class="header">
    <div class="headerContent">
      <h1 class="bannerText" id="title">Mediamarkt</h1>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HeaderComponent',
  data () {
    return {}
  },
  watch: {},
  computed: {},
  methods: {}
}
</script>

<style scoped>

.header {
  position: relative;
  color: salmon;
  height: 100px;
  background-color: orange;
}

.headerContent {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  position: relative;
}

.bannerText {
  text-shadow: -1px -1px 0 #fff, 1px -1px 0 #fff, -1px 1px 0 #fff, 1px 1px 0 #fff;
}

</style>