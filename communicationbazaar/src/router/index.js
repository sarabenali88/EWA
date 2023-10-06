import {createRouter, createWebHashHistory} from "vue-router";
import WelcomeComponent from "@/frontend/WelcomeComponent";
import UnknownRoute from "@/frontend/UnknownRouteComponent";
import ImageListComponent from "@/frontend/ImageListComponent";
import AccountComponent from "@/frontend/AccountComponent";
import SignInComponent from "@/frontend/SignInComponent";
import addImageComponent from "@/frontend/addImageComponent.vue";
import NavBar from "@/frontend/NavBarComponent";
import json from '../account.json'
import imageStatusTodoComponent from "@/frontend/imageStatusTodoComponent.vue";
import imageStatusOnGoingComponent from "@/frontend/imageStatusOnGoingComponent.vue";
import imageStatusFinishedComponent from "@/frontend/imageStatusFinishedComponent.vue";
import imageStatusOverDateComponent from "@/frontend/imageStatusOverDateComponent.vue";
import imageDetailComponent from "@/frontend/ImageDetailComponent";


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
                {path: NavBar.data().statusTodoRoute, component: imageStatusTodoComponent, children: [ { path: ':id', component: imageDetailComponent }]},
                {path: NavBar.data().statusOnGoingRoute, component: imageStatusOnGoingComponent, children: [ { path: ':id', component: imageDetailComponent }]},
                {path: NavBar.data().statusFinishedRoute, component: imageStatusFinishedComponent, children: [ { path: ':id', component: imageDetailComponent }]},
                {path: NavBar.data().statusOverDateRoute, component: imageStatusOverDateComponent, children: [ { path: ':id', component: imageDetailComponent }]}
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
        { path: '/:pathMatch(.*)', component: UnknownRoute },
        { path: '/addImage', component: addImageComponent}
    ]
})