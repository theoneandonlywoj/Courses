<template>
<div class="component">
  <h3>You may view the User Details here</h3>
  <p>Many Details</p>
  <p>User Name: {{ switchName() }}</p>
  <button v-on:click="resetName">Reset Name</button>
  <!-- Adding a button triggering the callback. -->
  <button v-on:click="resetFn()">Reset Name From Parent Function</button>
</div>
</template>

<script>
// Getting data from the parent component using properties
export default {
  // 'name' must match name used in the template
  props: {
    // Adding validation by passing an array of allowed types
    name: {
      type: String,
      required: true
    },
    resetFn: Function
  },
  methods: {
    switchName() {
      return this.name.split("").reverse().join("");
    },
    resetName(){
      this.name = 'Wojciech';
      // Emmiting an event
      this.$emit('nameWasReset', {name: this.name});
    }
  }
}
</script>

<style scoped>
div {
  background-color: lightcoral;
}
</style>
