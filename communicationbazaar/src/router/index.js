import {createRouter, createWebHashHistory} from "vue-router";
import WelcomeComponent from "@/frontend/WelcomeComponent";
import UnknownRoute from "@/frontend/UnknownRouteComponent";
import ImageListComponent from "@/frontend/ImageListComponent";
import AccountComponent from "@/frontend/AccountComponent";
import SignInComponent from "@/frontend/SignInComponent";
import NavBar from "@/frontend/NavBarComponent";
import json from '../account.json'
import imageStatusTodoComponent from "@/frontend/imageStatusTodoComponent.vue";
import imageStatusOnGoingComponent from "@/frontend/imageStatusOnGoingComponent.vue";
import imageStatusFinishedComponent from "@/frontend/imageStatusFinishedComponent.vue";
import imageStatusOverDateComponent from "@/frontend/imageStatusOverDateComponent.vue";

export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: NavBar.data().homeRoute,
            component: WelcomeComponent,
        },
        {
            path: NavBar.data().imageListRoute,
            component: ImageListComponent,
            children: [
                {path: NavBar.data().statusTodoRoute, component: imageStatusTodoComponent},
                {path: NavBar.data().statusOnGoingRoute, component: imageStatusOnGoingComponent},
                {path: NavBar.data().statusFinishedRoute, component: imageStatusFinishedComponent},
                {path: NavBar.data().statusOverDateRoute, component: imageStatusOverDateComponent}
            ]
        },
        {
            path: NavBar.data().myAccountRoute,
            component: AccountComponent,
            beforeEnter: (to, from, next) => {
                if (json.some(account => account.loggedIn)) {
                    next();
                } else {
                    next(NavBar.data().signInRoute)
                }
            }
        },
        {
            path: NavBar.data().signInRoute,
            component: SignInComponent
        },
        {path: '/:pathMatch(.*)', component: UnknownRoute}
    ]
})