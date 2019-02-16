new Vue({
	el: '#app',
	data: {
		title: 'Hello World!',
    link: 'http://google.com'
	},
  methods: {
    sayHello: function (){
      return this.title;
    }
  }
})
