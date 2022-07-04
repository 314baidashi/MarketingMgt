import Vue from 'vue'
import App from './App.vue'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import ajax from './axios/index.js'
import Print from 'vue-print-nb'
import "@/styles/index.scss";
import VueCookies from 'vue-cookies'
// import axios from 'axios'
Vue.use(VueCookies);
import config from './plugins/config'

Vue.prototype.$config = config;
import router from './router'　　
Vue.use(Print); //注册
Vue.prototype.$axios = ajax;
Vue.use(ElementUI);
Vue.config.productionTip = false;
// axios.interceptors.request.use(config => {
//     // console.log(config)
//     let token = sessionStorage.getItem('token')
//     if (token) {
//         config.headers.Authorization = token
//     }
//     console.log("config: " + config.headers.Authorization);
//     // 在最后必须 return config
//     return config
// })

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')