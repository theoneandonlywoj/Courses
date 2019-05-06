new Vue({
  el: '#exercise',
  data: {
    value: 0
  },
  computed: {
    result: function() {
      return this.value == 10 ? 'done' : 'not there yet';
    }
  },
  watch: {
    value: function(value) {
      vm = this;
      setTimeout(function() {
        vm.value = 0;
      }, 2000);
    }
  }
});
