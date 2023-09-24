<template>

  <nav>
    <ul>
      <ul id="left">
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
      </ul>

      <ul id="right">
        <li>
          <router-link :to="signInRoute" :class="{'active-tab': $route.path === signInRoute}">
            Log in
          </router-link>
        </li>
      </ul>
    </ul>
  </nav>

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

/*none style, otherwise there will be comming dots*/
ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

nav {
  background-color: rgb(116, 255, 116);
  color: #000;
  height: 50px;
  align-items: center;
}

nav ul {
  display: flex;
  justify-content: space-between;
  padding-top: 8px;
}

/*Color of the links and smooth color change*/
nav a {
  text-decoration: none;
  color: #000;
  padding: 10px 20px;
  transition: background-color 0.3s;
}

nav a:hover {
  background-color: rgb(46, 165, 46);
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: rgb(116, 255, 116);
  min-width: 120px;
  padding: 0;
}

.dropdown-content li {
  padding: 10px;
  text-align: center;
}

.dropdown:hover .dropdown-content {
  display: block;
}

.active-tab {
  background-color: rgb(46, 165, 46);
}

/*Keeping account at the right side*/
#right {
  justify-content: flex-end;
  padding-right: 10px;
}
</style>