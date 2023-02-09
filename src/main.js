import Vue from 'vue'
import App from './App.vue'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css';

Vue.use(ElementUI,  {size:"mini"});
Vue.config.productionTip = false

import router from './router'
import Axios from "axios";
import request from "@/utils/request";

Vue.prototype.Axios = Axios
Vue.prototype.request= request


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
