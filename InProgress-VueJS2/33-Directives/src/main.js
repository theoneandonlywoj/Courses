import Vue from 'vue'
import App from './App.vue'

// Globally defined directive
/*
  Hooks
  - bind(el, binding, vnode) - Once Directive is Attached
  - inserted(el, binding, vnode) - Inserted in Parent Node
  - update(el, binding, vnode, oldVnode) - Once Component is Updated (without Children)
  - componentUpdated(el, binding, vnode, oldVnode) - Once Component is Updated (wtih Children)
  - unbind(el, binding, vnode) - One Directive is Removed
*/
Vue.directive('highlight', {
  bind(el, binding, vnode) {
    el.style.backgroundColor="green";
  }
});
new Vue({
  el: '#app',
  render: h => h(App)
})
