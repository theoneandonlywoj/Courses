new Vue({
	el: '#app',
	data: {
		title: 'Hello World!',
    link: 'http://google.com',
    finishedLink: '<a href="http://google.com">Google (finished link)</a>'
	},
  methods: {
    sayHello: function (){
      this.title = 'Hello';
      return this.title;
    }
  }
})
