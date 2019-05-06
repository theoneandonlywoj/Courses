new Vue({
  el: '#app',
  data: {
    title: 'Vue Instance Lifecycle'
  },
  // Executing some code on the beforeCreate stage
  beforeCreate: function(){
    console.log('beforeCreate()');
  },
  created: function(){
    console.log('created()');
  },
  beforeMounted: function(){
    console.log('beforeMounted()');
  },
  mounted: function(){
    console.log('mounted()');
  },
  beforeUpdate: function(){
    console.log('beforeUpdate()');
  },
  updated: function(){
    console.log('updated()');
  },
  beforeDestroy: function(){
    console.log('beforeDestroy()');
  },
  destroyed: function(){
    console.log('destroyed()');
  },
  methods: {
    destroy: function(){
      this.$destroy();
    }
  }
})
