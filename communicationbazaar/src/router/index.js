import {createRouter, createWebHashHistory} from "vue-router";
import WelcomeComponent from "@/frontend/WelcomeComponent";
import UnknownRoute from "@/frontend/UnknownRouteComponent";
import ImageListComponent from "@/frontend/ImageListComponent";
import AccountComponent from "@/frontend/AccountComponent";
import SignInComponent from "@/frontend/SignInComponent";
import addImageComponent from "@/frontend/addImageComponent.vue";
import NavBar from "@/frontend/NavBarComponent";
import json from '../account.json'


export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: NavBar.data().homeRoute,
            component: WelcomeComponent
        },
        {
            path: NavBar.data().imageListRoute,
            component: ImageListComponent
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
        { path: '/:pathMatch(.*)', component: UnknownRoute },
        { path: '/addImage', component: addImageComponent}
    ]
})