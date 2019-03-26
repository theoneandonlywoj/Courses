<template>
  <div>
    <h1>The User Page</h1>
    <hr>
    <p>Loaded id: {{ id }}</p>
    <button v-on:click="navigateToHome" class="btn btn-primary">Go To Home</button>
  </div>
</template>

<script>
  export default {
    data(){
      return {
        id: this.$route.params.id
      }
    },
    // Setting up a watched to react to changes in the path parameters
    watch: {
      '$route'(to, from){
        /*
        Setting up the id to the id of the new route
        - to = new $route
        - from = old $route

        As of vue-router version 2.2,
        we can also bind our route params to props of the target components.
        This eliminates the need of watch ing $route .
        Example:
        https://github.com/vuejs/vue-router/tree/dev/examples/route-props
        */
        this.id = to.params.id;
      }
    },
    methods: {
      navigateToHome() {
        this.$router.push({path: '/'});
      }
    }
  }

</script>
