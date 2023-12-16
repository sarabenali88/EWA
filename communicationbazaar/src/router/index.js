import {createRouter, createWebHashHistory} from "vue-router";
import WelcomeComponent from "@/components/WelcomeComponent";
import UnknownRoute from "@/components/UnknownRouteComponent";
import ImageListComponent from "@/components/ImageListComponent";
import AccountComponent from "@/components/AccountComponent";
import SignInComponent from "@/components/SignInComponent";
import addImageComponent from "@/components/addImageComponent.vue";
import NavBar from "@/components/NavBarComponent";
import allImagesComponent from "@/components/allImagesComponent.vue";
import imageStatusTodoComponent from "@/components/imageStatusTodoComponent.vue";
import imageStatusOnGoingComponent from "@/components/imageStatusOnGoingComponent.vue";
import imageStatusFinishedComponent from "@/components/imageStatusFinishedComponent.vue";
import imageStatusOverDateComponent from "@/components/imageStatusOverDateComponent.vue";
import imageDetailComponent from "@/components/ImageDetailComponent";
import WebScraperComponent from "@/components/WebScraperComponent";
import ProfilePageComponent from "@/components/ProfilePageComponent";
import AllUsersComponent from "@/components/AllUsersComponent";
import UserDetailComponent from "@/components/UserDetailComponent";
import UserAddComponent from "@/components/UserAddComponent";
import StatisticsPageComponent from "@/components/StatisticsPageComponent.vue";

import ClaimedImageComponent from "@/components/ClaimedImageComponent";


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
            path: NavBar.data().statisticsRoute,
            component: StatisticsPageComponent
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
                {path: ':ean/:id', component: imageDetailComponent}
            ]
        },


        {path: '/:pathMatch(.*)', component: UnknownRoute},
        {path: '/addImage', component: addImageComponent}
    ]
})
