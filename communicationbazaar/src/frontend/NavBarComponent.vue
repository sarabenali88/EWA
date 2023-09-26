<template>

  <a class="btn btn-primary" data-bs-toggle="offcanvas" href="#offcanvasExample" role="button" aria-controls="offcanvasExample">
    Link with href
  </a>
  <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
    Button with data-bs-target
  </button>

  <div class="offcanvas offcanvas-start sidebar-nav" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasExampleLabel">Offcanvas</h5>
      <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
      <div>
        Some text as placeholder. In real life you can have the elements you have chosen. Like, text, images, lists, etc.
      </div>
      <div class="dropdown mt-3">
        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
          Dropdown button
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#">Action</a></li>
          <li><a class="dropdown-item" href="#">Another action</a></li>
          <li><a class="dropdown-item" href="#">Something else here</a></li>
        </ul>
      </div>
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
      signInRoute: '/sign-in'
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
  .offcanvas {
    width: var(--bs-offcanvas-width);
  }

  .sidebar-nav {
    top: 100px !important;

  }

  @media (min-width: 992px) {
    .sidebar-nav {
      transform: none !important;
      visibility: visible !important;
      top: 100px !important;
    }
  }
</style>