new Vue({
  el: '#app',
  data: {
    attachRed: false,
    attachBlue: false,
    attachGreen: false,
    color: 'green'
  },
  computed: {
    divClasses: function() {
      return {
        black: this.attachBlue && this.attachRed && this.attachGreen
      }
    }
  }
})
