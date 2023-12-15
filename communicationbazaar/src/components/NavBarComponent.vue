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
        <div class="active-line"
             :class="{'profile-active': currentContent === 'contentProfile', 'laptop-active': currentContent === 'contentLaptop', 'admin-active': currentContent === 'contentAdmin'}"></div>

        <!-- Image overview icon -->
        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="grey" class="bi bi-grid"
             viewBox="0 0 16 16" @click="setCurrentContent('contentImage')">
          <path
              d="M1 2.5A1.5 1.5 0 0 1 2.5 1h3A1.5 1.5 0 0 1 7 2.5v3A1.5 1.5 0 0 1 5.5 7h-3A1.5 1.5 0 0 1 1 5.5v-3zM2.5 2a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 1h3A1.5 1.5 0 0 1 15 2.5v3A1.5 1.5 0 0 1 13.5 7h-3A1.5 1.5 0 0 1 9 5.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zM1 10.5A1.5 1.5 0 0 1 2.5 9h3A1.5 1.5 0 0 1 7 10.5v3A1.5 1.5 0 0 1 5.5 15h-3A1.5 1.5 0 0 1 1 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 9h3a1.5 1.5 0 0 1 1.5 1.5v3a1.5 1.5 0 0 1-1.5 1.5h-3A1.5 1.5 0 0 1 9 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3z"/>
        </svg>

        <!-- Profile page icon -->
        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="grey" class="bi bi-person"
             viewBox="0 0 16 16" @click="setCurrentContent('contentProfile')">
          <path
              d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
        </svg>


        <!-- Laptop database icon -->
        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="grey" class="bi bi-database"
             viewBox="0 0 16 16" @click="setCurrentContent('contentLaptop')">
          <path
              d="M4.318 2.687C5.234 2.271 6.536 2 8 2s2.766.27 3.682.687C12.644 3.125 13 3.627 13 4c0 .374-.356.875-1.318 1.313C10.766 5.729 9.464 6 8 6s-2.766-.27-3.682-.687C3.356 4.875 3 4.373 3 4c0-.374.356-.875 1.318-1.313ZM13 5.698V7c0 .374-.356.875-1.318 1.313C10.766 8.729 9.464 9 8 9s-2.766-.27-3.682-.687C3.356 7.875 3 7.373 3 7V5.698c.271.202.58.378.904.525C4.978 6.711 6.427 7 8 7s3.022-.289 4.096-.777A4.92 4.92 0 0 0 13 5.698ZM14 4c0-1.007-.875-1.755-1.904-2.223C11.022 1.289 9.573 1 8 1s-3.022.289-4.096.777C2.875 2.245 2 2.993 2 4v9c0 1.007.875 1.755 1.904 2.223C4.978 15.71 6.427 16 8 16s3.022-.289 4.096-.777C13.125 14.755 14 14.007 14 13V4Zm-1 4.698V10c0 .374-.356.875-1.318 1.313C10.766 11.729 9.464 12 8 12s-2.766-.27-3.682-.687C3.356 10.875 3 10.373 3 10V8.698c.271.202.58.378.904.525C4.978 9.71 6.427 10 8 10s3.022-.289 4.096-.777A4.92 4.92 0 0 0 13 8.698Zm0 3V13c0 .374-.356.875-1.318 1.313C10.766 14.729 9.464 15 8 15s-2.766-.27-3.682-.687C3.356 13.875 3 13.373 3 13v-1.302c.271.202.58.378.904.525C4.978 12.71 6.427 13 8 13s3.022-.289 4.096-.777c.324-.147.633-.323.904-.525Z"/>
        </svg>

        <!-- Admin icon -->
        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="grey" class="bi bi-person-lock"
             :class="{'hiddenButton': this.accounts.some(account => account.loggedIn) === false || this.accounts.some(account => account.loggedIn === true && account.role !== 'admin')}"
             viewBox="0 0 16 16" @click="setCurrentContent('contentAdmin')">
          <path
              d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm0 5.996V14H3s-1 0-1-1 1-4 6-4c.564 0 1.077.038 1.544.107a4.524 4.524 0 0 0-.803.918A10.46 10.46 0 0 0 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h5ZM9 13a1 1 0 0 1 1-1v-1a2 2 0 1 1 4 0v1a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1h-4a1 1 0 0 1-1-1v-2Zm3-3a1 1 0 0 0-1 1v1h2v-1a1 1 0 0 0-1-1Z"/>
        </svg>

      </div>

      <div class="nav-side">

        <div class="content" :class="{ 'selected' : currentContent === 'contentImage'}">
          <h5 class="offcanvas-title">Image</h5>
          <ul>
            <li>
              <div :class="{'active-route': $route.path === homeRoute}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey" class="bi bi-house"
                     viewBox="0 0 16 16"
                     :class="{'active-icon': $route.path === homeRoute}">
                  <path
                      d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5 5 5Z"/>
                </svg>
                <router-link :to="homeRoute" :class="{'active-tab': $route.path === homeRoute}">
                  Home
                </router-link>
              </div>
            </li>
            <li>
              <div :class="{'active-route':$route.path.toString().includes(allImagesRoute)}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey" class="bi bi-image"
                     :class="{'active-icon': $route.path.toString().includes(allImagesRoute)}" viewBox="0 0 16 16">
                  <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                  <path
                      d="M2.002 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2h-12zm12 1a1 1 0 0 1 1 1v6.5l-3.777-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12V3a1 1 0 0 1 1-1h12z"/>
                </svg>
                <router-link :to="allImagesRoute" :class="{'active-tab': $route.path.toString().includes(allImagesRoute)}">
                  {{$t('navbar.allImages')}}
                </router-link>
              </div>
            </li>

            <li>
              <div :class="{'active-route': $route.path.toString().includes(statusTodo)}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey"
                     :class="{'active-icon': $route.path.toString().includes(statusTodo)}"
                     class="bi bi-card-checklist my-4"
                     viewBox="0 0 16 16">
                  <path
                      d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
                  <path
                      d="M7 5.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0zM7 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"/>
                </svg>
                <router-link :to="statusTodo" :class="{'active-tab': $route.path.toString().includes(statusTodo)}">
                  {{$t('navbar.toDoList')}}
                </router-link>
              </div>
            </li>

            <li>
              <div :class="{'active-route': $route.path.toString().includes(statusOnGoing)}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey"
                     :class="{'active-icon': $route.path.toString().includes(statusOnGoing)}"
                     class="bi bi-arrow-repeat my-4 mx-1"
                     viewBox="0 0 16 16">
                  <path
                      d="M11.534 7h3.932a.25.25 0 0 1 .192.41l-1.966 2.36a.25.25 0 0 1-.384 0l-1.966-2.36a.25.25 0 0 1 .192-.41zm-11 2h3.932a.25.25 0 0 0 .192-.41L2.692 6.23a.25.25 0 0 0-.384 0L.342 8.59A.25.25 0 0 0 .534 9z"/>
                  <path fill-rule="evenodd"
                        d="M8 3c-1.552 0-2.94.707-3.857 1.818a.5.5 0 1 1-.771-.636A6.002 6.002 0 0 1 13.917 7H12.9A5.002 5.002 0 0 0 8 3zM3.1 9a5.002 5.002 0 0 0 8.757 2.182.5.5 0 1 1 .771.636A6.002 6.002 0 0 1 2.083 9H3.1z"/>
                </svg>
                <router-link :to="statusOnGoing" :class="{'active-tab': $route.path.toString().includes(statusOnGoing)}">
                  {{$t('navbar.ongoingList')}}
                </router-link>
              </div>
            </li>

            <li>
              <div :class="{'active-route': $route.path.toString().includes(statusImpossible)}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey"
                     :class="{'active-icon': $route.path.toString().includes(statusImpossible)}"
                     class="bi bi-arrow-repeat my-4 mx-1"
                     viewBox="0 0 16 16">
                  <path d="M5.5 9.5A.5.5 0 0 1 6 9h4a.5.5 0 0 1 0 1H6a.5.5 0 0 1-.5-.5"/>
                  <path fill-rule="evenodd"
                        d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
                </svg>
                <router-link :to="statusImpossible" :class="{'active-tab': $route.path.toString().includes(statusImpossible)}">
                  Onmogelijke lijst
                </router-link>
              </div>
            </li>

            <li>
              <div :class="{'active-route': $route.path.toString().includes(statusFinished)}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey"
                     :class="{'active-icon':$route.path.toString().includes(statusFinished)}"
                     class="bi bi-calendar-check my-4 mx-1"
                     viewBox="0 0 16 16">
                  <path
                      d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
                  <path
                      d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
                </svg>
                <router-link :to="statusFinished" :class="{'active-tab': $route.path.toString().includes(statusFinished)}">
                  {{$t('navbar.finishedList')}}
                </router-link>
              </div>
            </li>

            <li>
              <div :class="{'active-route': $route.path.toString().includes(statusOverDate)}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey"
                     :class="{'active-icon': $route.path.toString().includes(statusOverDate)}"
                     class="bi bi-calendar-x my-4 mx-1"
                     viewBox="0 0 16 16">
                  <path
                      d="M6.146 7.146a.5.5 0 0 1 .708 0L8 8.293l1.146-1.147a.5.5 0 1 1 .708.708L8.707 9l1.147 1.146a.5.5 0 0 1-.708.708L8 9.707l-1.146 1.147a.5.5 0 0 1-.708-.708L7.293 9 6.146 7.854a.5.5 0 0 1 0-.708z"/>
                  <path
                      d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
                </svg>
                <router-link :to="statusOverDate" :class="{'active-tab': $route.path.toString().includes(statusOverDate)}">
                  {{$t('navbar.overDateList')}}
                </router-link>
              </div>
            </li>
          </ul>
        </div>

        <div class="content" :class="{ 'selected' : currentContent === 'contentProfile'}">
          <h5 class="offcanvas-title">{{$t('navbar.profileTitle')}}</h5>
          <ul>
            <li :class="{'hiddenButton': this.accounts.some(account => account.loggedIn) === false}">
              <div :class="{'active-route': $route.path === myProfileRoute}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey" class="bi bi-house"
                     viewBox="0 0 16 16"
                     :class="{'active-icon': $route.path === myProfileRoute}">
                  <path
                      d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5 5 5Z"/>
                </svg>
                <router-link :to="myProfileRoute" :class="{'active-tab': $route.path === myProfileRoute}">
                  {{$t('navbar.myProfile')}}
                </router-link>
              </div>
            </li>
            <li>
              <div
                  :class="{'active-route': $route.path === signInRoute, 'hiddenButton': this.accounts.some(account => account.loggedIn === true)}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey" class="bi bi-image"
                     :class="{'active-icon': $route.path === signInRoute}" viewBox="0 0 16 16">
                  <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                  <path
                      d="M2.002 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2h-12zm12 1a1 1 0 0 1 1 1v6.5l-3.777-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12V3a1 1 0 0 1 1-1h12z"/>
                </svg>
                <router-link :to="signInRoute" :class="{'active-tab': $route.path === signInRoute}">
                  {{$t('navbar.logIn')}}
                </router-link>
              </div>
            </li>
          </ul>
          <ul>
            <li :class="{'hiddenButton': this.accounts.some(account => account.loggedIn) === false}" @click="logOut">
              <router-link to>
                {{$t('navbar.logOut')}}
              </router-link>
            </li>
          </ul>
        </div>

        <div class="content" :class="{ 'selected' : currentContent === 'contentLaptop'}">
          <h5 class="offcanvas-title">Laptop</h5>
          <ul>
            <li>

            </li>
          </ul>

        </div>

        <div class="content" :class="{ 'selected' : currentContent === 'contentAdmin'}">
          <h5 class="offcanvas-title">{{$t('navbar.adminTitle')}}</h5>
          <ul>
            <li>
              <div
                  :class="{'active-route': $route.path === allUsersRoute, 'hiddenButton': this.accounts.some(account => account.loggedIn) === false || this.accounts.some(account => account.loggedIn && account.role !== 'admin')}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey" class="bi bi-image"
                     :class="{'active-icon': $route.path === allUsersRoute}" viewBox="0 0 16 16">
                  <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                  <path
                      d="M2.002 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2h-12zm12 1a1 1 0 0 1 1 1v6.5l-3.777-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12V3a1 1 0 0 1 1-1h12z"/>
                </svg>
                <router-link :to="allUsersRoute" :class="{'active-tab': $route.path === allUsersRoute}">
                  {{$t('navbar.allUsers')}}
                </router-link>
              </div>
            </li>

            <li>
              <div
                  :class="{'active-route': $route.path === statisticsRoute, 'hiddenButton': this.accounts.some(account => account.loggedIn) === false || this.accounts.some(account => account.loggedIn && account.role !== 'admin')}">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="grey" class="bi bi-image"
                     :class="{'active-icon': $route.path === statisticsRoute}" viewBox="0 0 16 16">
                  <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                  <path
                      d="M2.002 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2h-12zm12 1a1 1 0 0 1 1 1v6.5l-3.777-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12V3a1 1 0 0 1 1-1h12z"/>
                </svg>
                <router-link :to="statisticsRoute" :class="{'active-tab': $route.path === statisticsRoute}">
                  Statistieken
                </router-link>
              </div>
            </li>
          </ul>
        </div>

    </div>
  </div>
  </div>

  <!-- Mobile navbar -->
  <nav class="navbar fixed-bottom bg-body-tertiary" :class="{active: fulNavActive}" >
    <div class="image-active-line" :class="{'all-active': currentImageList === 'allImages', 'todo-active': currentImageList === 'statusTodo', 'ongoing-active': currentImageList === 'statusOnGoing', 'finished-active' : currentImageList === 'statusFinished' }"></div>
    <div class="container-fluid">
      <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="grey" fill-opacity="50%" class="bi bi-image"
            viewBox="0 0 16 16" @click="setImageList(this.allImagesRoute)">
        <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
        <path
            d="M2.002 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2h-12zm12 1a1 1 0 0 1 1 1v6.5l-3.777-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12V3a1 1 0 0 1 1-1h12z"/>
      </svg>

      <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="grey" fill-opacity="50%"
           class="bi bi-card-checklist"
           viewBox="0 0 16 16" @click="setImageList(this.statusTodo)">
        <path
            d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
        <path
            d="M7 5.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0zM7 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"/>
      </svg>

      <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="grey" fill-opacity="50%"
           class="bi bi-arrow-repeat"
           viewBox="0 0 16 16" @click="setImageList(this.statusOnGoing)">
        <path
            d="M11.534 7h3.932a.25.25 0 0 1 .192.41l-1.966 2.36a.25.25 0 0 1-.384 0l-1.966-2.36a.25.25 0 0 1 .192-.41zm-11 2h3.932a.25.25 0 0 0 .192-.41L2.692 6.23a.25.25 0 0 0-.384 0L.342 8.59A.25.25 0 0 0 .534 9z"/>
        <path fill-rule="evenodd"
              d="M8 3c-1.552 0-2.94.707-3.857 1.818a.5.5 0 1 1-.771-.636A6.002 6.002 0 0 1 13.917 7H12.9A5.002 5.002 0 0 0 8 3zM3.1 9a5.002 5.002 0 0 0 8.757 2.182.5.5 0 1 1 .771.636A6.002 6.002 0 0 1 2.083 9H3.1z"/>
      </svg>

      <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="grey" fill-opacity="50%"
           class="bi bi-calendar-check "
           viewBox="0 0 16 16" @click="setImageList(this.statusImpossible)">
        <path
            d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
        <path
            d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
      </svg>

      <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="black"  class="bi bi-list-2"
           viewBox="0 0 16 16" @click="showFullNav()">
        <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1
           .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
      </svg>
    </div>

    <div class="container-fluid" v-if="this.fulNavActive" >
      <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="grey" fill-opacity="50%" class="bi bi-person"
           viewBox="0 0 16 16" @click="setPageContent(myProfileRoute)">
        <path
            d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
      </svg>
      <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="grey" fill-opacity="50%" class="bi bi-person-lock" :class="{'hiddenButton': this.accounts.some(account => account.loggedIn) === false || this.accounts.some(account => account.loggedIn && account.role !== 'admin')}"
           viewBox="0 0 16 16" @click="setPageContent(allUsersRoute)">
        <path
            d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm0 5.996V14H3s-1 0-1-1 1-4 6-4c.564 0 1.077.038 1.544.107a4.524 4.524 0 0 0-.803.918A10.46 10.46 0 0 0 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h5ZM9 13a1 1 0 0 1 1-1v-1a2 2 0 1 1 4 0v1a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1h-4a1 1 0 0 1-1-1v-2Zm3-3a1 1 0 0 0-1 1v1h2v-1a1 1 0 0 0-1-1Z"/>
      </svg>
    </div>
  </nav>

</template>

<script>
import allImagesComponent from "@/components/allImagesComponent.vue";

export default {
  name: 'NavBarComponent',
  inject: ["accountsService"],
  data() {
    return {
      homeRoute: '/home',
      imageListRoute: '/imageListRoute',
      allImagesRoute: '/imageListRoute/allImages',
      statusTodo: '/imageListRoute/statusTodo',
      statusOnGoing: '/imageListRoute/statusOnGoing',
      statusImpossible: '/imageListRoute/statusImpossible',
      statusFinished: '/imageListRoute/statusFinished',
      statusOverDate: '/imageListRoute/statusOverDate',
      myAccountRoute: '/myAccountRoute',
      myProfileRoute: '/myProfileRoute',
      claimedImageRoute: '/claimedImageRoute',
      signInRoute: '/signIn',
      webScraperRoute: '/webScraper',
      allUsersRoute: '/allUsers',
      accounts: [],
      statisticsRoute: '/Statistics',

      currentContent: 'contentImage',
      currentImageList: 'statusFinished',
      fulNavActive: false
    }
  },
  watch: {
    async '$route'() {
      if (this.$route.path.match(this.homeRoute)) {
        this.accounts = await this.accountsService.asyncFindAll();
        this.loggedInAccount = this.accounts.find(account => account.loggedIn);
      }
      if (this.$route.path.match(this.homeRoute) || this.$route.path.match(this.imageListRoute)) {
        this.setCurrentContent('contentImage')
      }
      if (this.$route.path.match(this.signInRoute) || this.$route.path.match(this.myProfileRoute)) {
        this.setCurrentContent('contentProfile')
      }
      if (this.$route.path.match(this.allUsersRoute) || this.$route.path.match(this.statisticsRoute)) {
        this.setCurrentContent('contentAdmin')
      }
    }


  },
  computed: {
    allImagesComponent() {
      return allImagesComponent
    }
  },
  methods: {
    async logOut() {
      this.loggedInAccount = this.accounts.find(account => account.loggedIn === true);
      this.loggedInAccount.loggedIn = false;
      await this.accountsService.asyncSave(this.loggedInAccount);
      this.$router.push(this.signInRoute);
    },

    setCurrentContent(contentId) {
      this.currentContent = contentId;
    },
    setImageList(status) {
      switch (status) {
        case this.allImagesRoute:
          this.$router.push(this.allImagesRoute);
          this.currentImageList = 'allImages';
          break;
        case this.statusTodo:
          this.$router.push(this.statusTodo);
          this.currentImageList = 'statusTodo';
          break;
        case this.statusOnGoing:
          this.$router.push(this.statusOnGoing);
          this.currentImageList = 'statusOnGoing';
          break;
        case this.statusFinished:
          this.$router.push(this.statusFinished);
          this.currentImageList = 'statusFinished';
          break;
        default:
          this.$router.push(this.allImagesRoute);
          this.currentImageList = 'allImages';
      }
    },
    showFullNav() {
      this.fulNavActive = !this.fulNavActive;
    },
    setPageContent(route) {
      this.$router.push(route);
    }
  }
}
</script>

<style scoped>

:root {
  --bs-offcanvas-width: 400px;
  --topNavBarHeight: 200px;
}

.active-line {
  border-right: #DA1C25 solid 2px;
  height: 60px;
  position: absolute;
  left: 100px;
  top: 18px;
  transition: 250ms ease-in-out;
}

.image-active {
  top: 18px;
}

.profile-active {
  top: 112px;
}

.laptop-active {
  top: 212px;
}

.admin-active {
  top: 312px;
}


.offcanvas-title {
  padding-bottom: 20px;
}

.content {
  display: none;
}

.content.selected {
  display: block;
  padding-left: 30px;
}


.nav-side {
  width: 200px;
  height: 100%;
}

li .bi {
  margin: 0 5px 5px 0 !important;
}


.offcanvas-title {
  color: lightgrey;
  font-size: 35px;
}

.nav-icons {
  background-color: #F4F4F4;
  width: 100px;
  height: 100%;
  padding: 8px;
}

.bi {
  margin-bottom: 15px;
  cursor: pointer;
}

.sidebar-nav {
  top: 100px !important;
}

.sidebar {
  display: flex;
  width: 330px;
  overflow-y: auto;
  height: 100%;
  border-right: solid lightgrey 2px;
  background-color: white;
}

#sideBarButton {
  margin-top: 100px;
  overflow-y: auto;
  cursor: pointer;
  z-index: 2;
}


ul {
  margin-left: -35px;
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

.active-tab {
  color: #DA1C25;
  font-weight: bold;
  display: inline-block;
  width: 170px;
  height: 30px;
  padding-left: 5px;
}

.active-route {
  margin-left: -10px;
  display: flex;
  padding-left: 10px;
  padding-top: 3px;
  background-color: #F5F5F5;
  width: 205px;
  height: 30px;
  border-radius: 5px;
}

.active-icon {
  fill: #DA1C25;
  width: 28px;
}

.active-route .bi {
  margin: 0 0 5px 0 !important;
}

.hiddenButton {
  display: none;
}


@media (min-width: 700px) {
  .sidebar-nav {
    transform: none !important;
    visibility: visible !important;
    top: 100px !important;
  }

  .sidebar-default {
    visibility: visible;
  }

  .fixed-bottom {
    display: none;
  }


  #sideBarButton {
    visibility: hidden;
  }
}

@media (max-width: 700px) {
  .sidebar-nav {
    display: none;
  }

  .bi-list {
    display: none;
  }


  .fixed-bottom {
    z-index: 1;
    border-top: solid lightgrey 2px;
    height: 90px;
    padding-right: 15px;
    padding-left: 15px;
    padding-top: 15px;
  }

  .image-active-line {
    border-top: #DA1C25 solid 2px;
    width: 60px;
    position: absolute;
    transition: 250ms ease-in-out;
    top: 0;
    left: 23px;
  }

  .all-active {
    left: 4.5%;
  }

  .todo-active {
    left: 24%;
  }

  .ongoing-active {
    left: 44%;
  }

  .finished-active {
    left: 63.5%;
  }

  .active {
    height: 180px;
    transition: 250ms ease-in-out;
  }

}

</style>
