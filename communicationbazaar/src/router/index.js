import {createRouter, createWebHashHistory} from "vue-router";
import WelcomeComponent from "@/frontend/WelcomeComponent";
import UnknownRoute from "@/frontend/UnknownRouteComponent";
import ImageListComponent from "@/frontend/ImageListComponent";
import AccountComponent from "@/frontend/AccountComponent";
import SignInComponent from "@/frontend/SignInComponent";
import NavBar from "@/frontend/NavBarComponent";

export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        { path: NavBar.data().homeRoute, component: WelcomeComponent },
        { path: NavBar.data().imageListRoute, component: ImageListComponent },
        { path: NavBar.data().myAccountRoute, component: AccountComponent },
        { path: NavBar.data().signInRoute, component: SignInComponent },
        { path: '/:pathMatch(.*)', component: UnknownRoute }
    ]
})