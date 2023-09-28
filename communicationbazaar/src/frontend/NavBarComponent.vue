<template>
  <!-- Button for side bar-->
  <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-list"
       viewBox="0 0 16 16" id="sideBarButton" data-bs-toggle="offcanvas" href="#offcanvasExample"
       aria-controls="offcanvasExample">
    <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1
    .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
  </svg>

  <!-- Hidden side bar -->
  <div class="offcanvas sidebar-nav" tabindex="-1" id="offcanvasExample"
       aria-labelledby="offcanvasExampleLabel">

    <div class="sidebar">
      <div class="nav-icons">

        <!-- Image overview icon -->
        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="grey" class="bi bi-grid"
             viewBox="0 0 16 16" >
          <path
              d="M1 2.5A1.5 1.5 0 0 1 2.5 1h3A1.5 1.5 0 0 1 7 2.5v3A1.5 1.5 0 0 1 5.5 7h-3A1.5 1.5 0 0 1 1 5.5v-3zM2.5 2a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 1h3A1.5 1.5 0 0 1 15 2.5v3A1.5 1.5 0 0 1 13.5 7h-3A1.5 1.5 0 0 1 9 5.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zM1 10.5A1.5 1.5 0 0 1 2.5 9h3A1.5 1.5 0 0 1 7 10.5v3A1.5 1.5 0 0 1 5.5 15h-3A1.5 1.5 0 0 1 1 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 9h3a1.5 1.5 0 0 1 1.5 1.5v3a1.5 1.5 0 0 1-1.5 1.5h-3A1.5 1.5 0 0 1 9 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3z"/>
        </svg>

        <!-- Profile page icon -->
        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="grey" class="bi bi-person"
             viewBox="0 0 16 16">
          <path
              d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
        </svg>

        <!-- Laptop database icon -->
        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="grey" class="bi bi-database"
             viewBox="0 0 16 16">
          <path
              d="M4.318 2.687C5.234 2.271 6.536 2 8 2s2.766.27 3.682.687C12.644 3.125 13 3.627 13 4c0 .374-.356.875-1.318 1.313C10.766 5.729 9.464 6 8 6s-2.766-.27-3.682-.687C3.356 4.875 3 4.373 3 4c0-.374.356-.875 1.318-1.313ZM13 5.698V7c0 .374-.356.875-1.318 1.313C10.766 8.729 9.464 9 8 9s-2.766-.27-3.682-.687C3.356 7.875 3 7.373 3 7V5.698c.271.202.58.378.904.525C4.978 6.711 6.427 7 8 7s3.022-.289 4.096-.777A4.92 4.92 0 0 0 13 5.698ZM14 4c0-1.007-.875-1.755-1.904-2.223C11.022 1.289 9.573 1 8 1s-3.022.289-4.096.777C2.875 2.245 2 2.993 2 4v9c0 1.007.875 1.755 1.904 2.223C4.978 15.71 6.427 16 8 16s3.022-.289 4.096-.777C13.125 14.755 14 14.007 14 13V4Zm-1 4.698V10c0 .374-.356.875-1.318 1.313C10.766 11.729 9.464 12 8 12s-2.766-.27-3.682-.687C3.356 10.875 3 10.373 3 10V8.698c.271.202.58.378.904.525C4.978 9.71 6.427 10 8 10s3.022-.289 4.096-.777A4.92 4.92 0 0 0 13 8.698Zm0 3V13c0 .374-.356.875-1.318 1.313C10.766 14.729 9.464 15 8 15s-2.766-.27-3.682-.687C3.356 13.875 3 13.373 3 13v-1.302c.271.202.58.378.904.525C4.978 12.71 6.427 13 8 13s3.022-.289 4.096-.777c.324-.147.633-.323.904-.525Z"/>
        </svg>

        <!-- Admin icon -->
        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="grey" class="bi bi-person-lock"
             viewBox="0 0 16 16">
          <path
              d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm0 5.996V14H3s-1 0-1-1 1-4 6-4c.564 0 1.077.038 1.544.107a4.524 4.524 0 0 0-.803.918A10.46 10.46 0 0 0 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h5ZM9 13a1 1 0 0 1 1-1v-1a2 2 0 1 1 4 0v1a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1h-4a1 1 0 0 1-1-1v-2Zm3-3a1 1 0 0 0-1 1v1h2v-1a1 1 0 0 0-1-1Z"/>
        </svg>
      </div>
      <!-- Navbar links -->
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

.nav-icons {
  background-color: #F4F4F4;
  width: 100px;
  height: 100%;
  padding: 8px;
}

.bi {
  margin-bottom: 15px;
}

.btn {
  padding: 0;
}

.sidebar-nav {
  top: 100px !important;
}

.sidebar {
  display: flex;
  width: 300px;
  padding-right: 20px;
  position: fixed;
  overflow-y: auto;
  height: 100%;
  border-right: solid lightgrey 2px;
  background-color: white;
}

#sideBarButton {
  overflow-y: auto;
  cursor: pointer;
}

#sideBarImageButton {
  cursor: pointer;
}

ul {
  list-style: none;
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