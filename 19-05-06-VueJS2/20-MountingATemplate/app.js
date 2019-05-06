var vm1 = new Vue({
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
// Mounting the Vue instance on the element with id app1
vm1.$mount('#app1');

// Uncommon way below
var vm2 = new Vue({
  template: '<h1> Hello from VM2 </h1>'
});

vm2.$mount('#app2');
