new Vue({
    el: '#example',
    data: { hello: 'Hello World!' },
    methods: {
      changeText: function(event){
        // The event object is created automatically by Javascript
        this.hello = event.target.value;
      }
    }
})
