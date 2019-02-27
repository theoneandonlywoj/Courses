Vue.component('button-counter', {
  // The data property must be a function in the component definition.
  data: function () {
    return {
      count: 0
    }
  },
  template: '<button v-on:click="count++">You clicked me {{ count }} times.</button>'
})
// The component must be defined above the root Vue instance
new Vue({
  el: '#components-demo'
})
