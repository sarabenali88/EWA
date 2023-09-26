<template>

  <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-list"
       viewBox="0 0 16 16" id="sideBarButton" data-bs-toggle="offcanvas" href="#offcanvasExample"
       aria-controls="offcanvasExample">
    <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1
    .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
  </svg>

  <div class="offcanvas sidebar-nav" tabindex="-1" id="offcanvasExample"
       aria-labelledby="offcanvasExampleLabel">

    <div class="sidebar">
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
    </div>
  </div>
</template>

<script>
export default {
  name: 'NavBarComponent',
  data() {
    return {
      homeRoute: '/',
      imageListRoute: '/imageListRoute',
      myAccountRoute: '/myAccountRoute',
      signInRoute: '/signIn'
    }
  },
  watch: {},
  computed: {},
  methods: {}
}
</script>

<style scoped>

:root {
  --bs-offcanvas-width: 400px;
  --topNavBarHeight: 200px;
}

.sidebar-nav {
  top: 100px !important;
}

.sidebar {
  background-color: rgb(116, 255, 116);
  align-items: center;
  width: 150px;
  padding: 20px;
  position: fixed;
  overflow-y: auto;
  height: 100%;
}

#sideBarButton {
  overflow-y: auto;
  cursor: pointer;
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

@media (min-width: 992px) {
  .sidebar-nav {
    transform: none !important;
    visibility: visible !important;
    top: 100px !important;
  }

  #sideBarButton {
    visibility: hidden;
  }
}
</style>