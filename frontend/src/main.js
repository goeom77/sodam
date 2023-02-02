import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import "sweetalert2/dist/sweetalert2.min.css";

loadFonts()

createApp(App).use(router)
  .use(router)
  .use(store)
  .use(vuetify)
  .mount('#app')

  window.Kakao.init('46ea2a430e00975ef0166ed7e47277e9');