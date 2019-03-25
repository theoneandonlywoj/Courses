import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import { routes } from './routes.js';

Vue.use(VueRouter);
const router = new VueRouter({
  routes: routes
});

new Vue({
  el: '#app',
  router: router,
  render: h => h(App)
})
