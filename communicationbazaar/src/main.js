import { createApp } from 'vue'
import { router } from './router'
import App from './AppComponent.vue'
import i18n from './i18n'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'


createApp(App).use(router).use(i18n).mount('#app')

