import { createApp } from 'vue'
import { router } from './router'
import App from './AppComponent.vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'

createApp(App).use(router).mount('#app')
