new Vue({
        el: '#exercise',
        data: {
            value: ''
        },
        methods: {
          showAlert: function(){
            alert("Alert!");
          },
          storeValueInDataProperty: function($event){
            console.log(event.key);
            this.value = event.key;
          },
          replaceValue: function($event){
            console.log(event.target.value)
            this.value = event.target.value
          }
        }
    });
