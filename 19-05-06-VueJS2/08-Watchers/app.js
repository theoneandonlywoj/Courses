new Vue({
  el: '#app',
  data: {
    counter: 0,
    secondCounter: 0
  },
  // Execute upon data change
  // Good for asynch tasks
  watch: {
    counter: function(value){
      var vm = this;
      setTimeout(function(){
        console.log("Executed!");
        vm.counter = 0;
      }, 2000);
    }
  },
  methods: {
    counterGreaterThanFive: function() {
			console.log("Method!");
      return this.counter > 5 ? "Greater than 5" : "Smaller or equal 5"
    }
  }
})
