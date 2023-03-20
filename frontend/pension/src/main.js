import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios';
import qs from 'qs';
import http from "./http/http.js"
Vue.prototype.staticURL = "http://121.5.35.178:8081";
Vue.prototype.axios = http;
Vue.prototype.qs = qs;
Vue.config.productionTip = false
/* axios.defaults.baseURL = "http://121.5.35.178:8081/admin";
Vue.prototype.axios = axios; */


import 'font-awesome/css/font-awesome.min.css';
// import initRichText from './components/common/initHTMLEditor.js'
// import 'font-awesome/css/font-awesome.min.css'
// 时间戳转换
import time from './time';

Vue.prototype.time = time;
Vue.config.productionTip = false
Vue.use(ElementUI)

new Vue({
    router,
    store,
    // initRichText,
    render: h => h(App)
}).$mount('#app')