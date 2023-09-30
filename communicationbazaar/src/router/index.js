import {createRouter, createWebHashHistory} from "vue-router";
import WelcomeComponent from "@/frontend/WelcomeComponent";
import UnknownRoute from "@/frontend/UnknownRouteComponent";
import ImageListComponent from "@/frontend/ImageListComponent";
import AccountComponent from "@/frontend/AccountComponent";
import SignInComponent from "@/frontend/SignInComponent";
import addImageComponent from "@/frontend/addImageComponent.vue";
export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        { path: '/', component: WelcomeComponent },
        { path: '/imageListRoute', component: ImageListComponent },
        { path: '/myAccountRoute', component: AccountComponent },
        { path: '/signIn', component: SignInComponent },
        { path: '/:pathMatch(.*)', component: UnknownRoute },
        { path: '/addImage', component: addImageComponent}
    ]
})