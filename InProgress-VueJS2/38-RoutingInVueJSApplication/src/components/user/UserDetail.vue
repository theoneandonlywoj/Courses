<template>
  <div>
    <h3>Some User Details</h3>
    <p>User ID: {{ $route.params.id }}</p>
    <router-link tag="button"
      class="btn btn-primary"
      v-bind:to="link">Edit</router-link>
  </div>

</template>
<script>
  export default {
    data(){
      return {
        link: { name: 'userEdit',
                params: {id: this.$route.params.id},
                query: {
                  locale: 'en',
                  userID: this.$route.params.id
                },
                hash: "#data"
              }
      }
    }
  }
</script>
