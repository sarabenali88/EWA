import {createRouter, createWebHashHistory} from "vue-router";
import WelcomeComponent from "@/frontend/WelcomeComponent";
import UnknownRoute from "@/frontend/UnknownRouteComponent";
import ImageListComponent from "@/frontend/ImageListComponent";
import AccountComponent from "@/frontend/AccountComponent";
import SignInComponent from "@/frontend/SignInComponent";
import addImageComponent from "@/frontend/addImageComponent.vue";
import NavBar from "@/frontend/NavBarComponent";
import allImagesComponent from "@/frontend/allImagesComponent.vue";
import imageStatusTodoComponent from "@/frontend/imageStatusTodoComponent.vue";
import imageStatusOnGoingComponent from "@/frontend/imageStatusOnGoingComponent.vue";
import imageStatusFinishedComponent from "@/frontend/imageStatusFinishedComponent.vue";
import imageStatusOverDateComponent from "@/frontend/imageStatusOverDateComponent.vue";
import imageDetailComponent from "@/frontend/ImageDetailComponent";
import WebScraperComponent from "@/frontend/WebScraperComponent";
import ProfilePageComponent from "@/frontend/ProfilePageComponent";
import AllUsersComponent from "@/frontend/AllUsersComponent";
import UserDetailComponent from "@/frontend/UserDetailComponent";
import UserAddComponent from "@/frontend/UserAddComponent";
import ClaimedImageComponent from "@/frontend/ClaimedImageComponent";


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
                {
                    path: 'allImages',
                    component: allImagesComponent,
                    children: [{path: ':ean/:id', component: imageDetailComponent}]
                },
                {
                    path: 'statusTodo',
                    component: imageStatusTodoComponent,
                    children: [{path: ':ean/:id', component: imageDetailComponent}]
                },
                {
                    path: 'statusOnGoing',
                    component: imageStatusOnGoingComponent,
                    children: [{path: ':ean/:id', component: imageDetailComponent}]
                },
                {
                    path: 'statusFinished',
                    component: imageStatusFinishedComponent,
                    children: [{path: ':ean/:id', component: imageDetailComponent}]
                },
                {
                    path: 'statusOverDate',
                    component: imageStatusOverDateComponent,
                    children: [{path: ':ean/:id', component: imageDetailComponent}]
                }
            ]
        },
        {
            path: NavBar.data().myAccountRoute,
            component: AccountComponent,
        },
        {
            path: NavBar.data().signInRoute,
            component: SignInComponent
        },
        {
            path: NavBar.data().webScraperRoute,
            component: WebScraperComponent
        },
        {
            path: NavBar.data().allUsersRoute,
            component: AllUsersComponent,
            children: [
                {path: ':id', component: UserDetailComponent},
                {path: 'userAdd', component: UserAddComponent}],
        },
        {
            path: NavBar.data().myProfileRoute,
            name: 'myProfileRoute',
            component: ProfilePageComponent,
            children: [
                {
                    path: 'claimedImageRoute',
                    name: 'claimedImageRoute',
                    component: ClaimedImageComponent,
                },
            ]
        },


        {path: '/:pathMatch(.*)', component: UnknownRoute},
        {path: '/addImage', component: addImageComponent}
    ]
})
