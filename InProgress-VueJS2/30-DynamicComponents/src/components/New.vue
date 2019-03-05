<template>
  <div>
    <h3>New Quote</h3>
    <button v-on:click="counter++">Increase</button>
    <p>Counter: {{ counter }}</p>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      counter: 0
    }
  },
  destroyed(){
    console.log("Component New Destroyed!");
  }
}
</script>

<style lang="css" scoped>
</style>
