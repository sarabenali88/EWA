<template>
  <aside class="sidebar">
    <ul>
        <li>
          <router-link :to="homeRoute" :class="{'active-tab': $route.path === homeRoute}">
            Home
          </router-link>
        </li>
        <li>
          <router-link :to="imageListRoute" :class="{'active-tab': $route.path === imageListRoute}">
            Image list
          </router-link>
        </li>
        <li>
          <router-link :to="myAccountRoute" :class="{'active-tab': $route.path === myAccountRoute}">
            My Account
          </router-link>
        </li>
        <li>
          <router-link :to="signInRoute" :class="{'active-tab': $route.path === signInRoute}">
            Log in
          </router-link>
        </li>
      </ul>
  </aside>

</template>

<script>
export default {
  name: 'NavBarComponent',
  data() {
    return {
      homeRoute: '/',
      imageListRoute: '/imageListRoute',
      myAccountRoute: '/myAccountRoute',
      signInRoute: '/sign-in'
    }
  },
  watch: {},
  computed: {},
  methods: {}
}
</script>

<style scoped>
.sidebar {
  background-color: rgb(116, 255, 116);
  align-items: center;
  width: 150px;
  padding: 20px;
  position: fixed;
  overflow-y: auto;
  height: 100%;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  margin-bottom: 10px;
  transition: background-color 0.3s;
}

li a {
  text-decoration: none;
  color: black;
}

li:hover {
  background-color: rgb(46, 165, 46);
}

.active-tab {
  background-color: rgb(46, 165, 46);
}
</style>