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

// Interceptors will be executed on every request and response
const requestInterceptorId = axios.interceptors.request.use(config => {
  console.log('Request Interceptor:', config);
  return config
});

const responseInterceptorId = axios.interceptors.response.use(res => {
  console.log('Response Interceptor:', res);
  return res
});

// Ejecting Interceptors
axios.interceptors.request.eject(requestInterceptorId);
axios.interceptors.response.eject(responseInterceptorId);

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
