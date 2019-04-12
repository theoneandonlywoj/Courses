import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'

import router from './router'
import store from './store'

// Setting up the baseURL
axios.defaults.baseURL = 'https://axios-9dd80.firebaseio.com'
// Setting up custom headers
axios.defaults.headers.common['Authorization'] = 'something'
axios.defaults.headers.get['Accepts'] = 'application/json'

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
