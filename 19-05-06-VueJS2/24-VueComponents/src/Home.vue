<template>
  <!-- It is obligatory to have a single element in the component template.
  That can be fixed by wrapping everything in a div. -->
  <div>
    <p>Server Stataus: {{ status }}</p>
    <hr>
    <button v-on:click="changeStatus">Change Status</button>
  </div>
</template>

<script>
export default {
  data: function(){
    return {
      status: 'Critical'
    }
  },
  methods: {
    // ES6 Syntax, equal to changeStatus: function {}
    changeStatus(){
      this.status = 'Normal'
    }
  }
}
</script>

<style>
</style>
