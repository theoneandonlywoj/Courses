import Vue from 'vue'
import App from './App.vue'
// Importing Vue-resource as a plugin
import VueResource from 'vue-resource';
Vue.use(VueResource);

// Configuring Vue-resource link globally
Vue.http.options.root = 'https://myvueproject-8a8df.firebaseio.com/myDataPackage.json';

// Intercepting requests
Vue.http.interceptors.push((request, next) => {
  console.log(request);
  // Changing the request type just for sake of changing it
  if (request.method == 'POST'){
    request.method = 'PUT';
  }
  next(response => {
    console.log('Response:');
    console.log(response);
  });
});

new Vue({
  el: '#app',
  render: h => h(App)
})
