new Vue({
  el: '#exercise',
  data: {
    name: 'Wojciech',
    age: 27,
    image: 'http://sfwallpaper.com/images/nice-images-15.jpg'
  },
  methods: {
    random: function() {
      return Math.random();
    }
  }
});
