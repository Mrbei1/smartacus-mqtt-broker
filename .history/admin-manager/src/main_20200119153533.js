import Vue from 'vue'
import App from './App.vue'
import axios from 'axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Api from './components/API/Api.vue';
import router from './router'

Vue.use(ElementUI);

Vue.config.productionTip = false

Vue.prototype.$axios = axios.create({
  // baseURL: Api.hot,
  timeout: 10000,
  withCredentials: true
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
