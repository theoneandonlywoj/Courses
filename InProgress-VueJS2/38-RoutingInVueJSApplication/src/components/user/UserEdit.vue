<template>
  <div class="">
    <h3>Edit the User</h3>
    <p>Locale: {{ $route.query.locale }}</p>
    <hr>
    <button class="btn btn-primary" v-on:click="confirmed = true">Confirm</button>
    <p>Confirmed: {{ confirmed }}</p>
    <div style="height: 700px"></div>
    <p id="data">Some extra data</p>
  </div>
</template>

<script>
  export default {
    data(){
      return {
        confirmed: false
      }
    },
    beforeRouteLeave(to, from, next){
      if (this.confirmed){
        next();
      } else {
        if (confirm('Are you sure?')){
          next();
        } else {
          next(false);
        }
      }

    }
  }
</script>
