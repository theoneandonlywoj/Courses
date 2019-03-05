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
  },
  // Access to new stages of the life cycle - activated and deactivated
  deactivated() {
    console.log("Component New Deactivated!");
  },
  activated() {
    console.log("Component New Activated!");
  }
}
</script>

<style lang="css" scoped>
</style>
