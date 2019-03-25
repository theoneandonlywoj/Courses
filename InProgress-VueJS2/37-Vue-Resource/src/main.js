import Vue from 'vue'
import App from './App.vue'
// Importing Vue-resource as a plugin
import VueResource from 'vue-resource';
Vue.use(VueResource);

// Configuring Vue-resource link globally
Vue.http.options.root = 'https://myvueproject-8a8df.firebaseio.com/myDataPackage.json';

new Vue({
  el: '#app',
  render: h => h(App)
})
