new Vue({
  el: '#app',
  data: {
    counter: 0,
    secondCounter: 0
  },
  // Functions that require longer computation timeout
  computed: {
		output: function(){
			console.log("Computed!");
			return this.counter > 5 ? "Greater than 5" : "Smaller or equal 5"
		}
  },
  methods: {
    counterGreaterThanFive: function() {
			console.log("Method!");
      return this.counter > 5 ? "Greater than 5" : "Smaller or equal 5"
    }
  }
})
