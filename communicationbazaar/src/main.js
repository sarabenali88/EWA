import { createApp } from 'vue'
import { router } from './router'
import App from './AppComponent.vue'

import './main.css'

createApp(App).use(router).mount('#app')
