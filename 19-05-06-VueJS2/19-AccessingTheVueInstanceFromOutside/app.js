var vm1 = new Vue({
  el: '#app1',
  data: {
    title: 'The VueJS Instance',
    showParagraph: false
  },
  methods: {
    show: function() {
      this.showParagraph = true;
      this.updateTitle('The VueJS Instance (Updated)');
      // Accessing properties of the button
      console.log(this.$refs.myButton);
      // Setting a value using $refs
      this.$refs.myButton.innerText = '$refs test';
    },
    updateTitle: function(title) {
      this.title = title;
    }
  },
  computed: {
    lowercaseTitle: function() {
      return this.title.toLowerCase();
    }
  },
  watch: {
    title: function(value) {
      alert('Title changed, new value: ' + value);
    }
  }
});

var vm2 = new Vue({
  el: '#app2',
  data: {
    title: "The second Vue Instance"
  },
  methods: {
    onChange: function() {
      vm1.title = 'Changed'
    }
  }
});

setTimeout(function() {
  vm1.title = "Changed by timeout";
}, 3000);
