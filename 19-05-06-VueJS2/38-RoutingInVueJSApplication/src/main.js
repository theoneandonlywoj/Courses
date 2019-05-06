import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import {
  routes
} from './routes.js';

Vue.use(VueRouter);
const router = new VueRouter({
  routes: routes,
  mode: 'history',
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      if (to.hash) {
        return {
          selector: to.hash
        };
      } else {
        return {
          x: 0,
          y: 0
        };
      }
    }
  }
});

router.beforeEach((to, from, next) => {
  console.log("Global beforeEach");
  /* Next() accepts multiple values:
  - false -> to abort
  - path -> to redirect
  - router link object -> f.e. name of the path
  - nothing -> it will simply continue the operations
  */
  next();
});

new Vue({
  el: '#app',
  router: router,
  render: h => h(App)
})
