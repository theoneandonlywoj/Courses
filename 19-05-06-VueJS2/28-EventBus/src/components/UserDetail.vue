<template>
<div class="component">
  <h3>You may view the User Details here</h3>
  <p>Many Details</p>
  <p>User Name: {{ switchName() }}</p>
  <p>User Age: {{ userAge }}</p>
  <button v-on:click="resetName">Reset Name</button>
</div>
</template>

<script>
import { eventBus } from '../main'
// Getting data from the parent component using properties
export default {
  // 'name' must match name used in the template
  props: {
    // Adding validation by passing an array of allowed types
    name: {
      type: String,
      required: true
    },
    userAge: Number
  },
  methods: {
    switchName() {
      return this.name.split("").reverse().join("");
    },
    resetName(){
      this.name = 'Wojciech';
      // Emmiting an event
      //this.$emit('nameWasReset', {name: this.name});
      eventBus.$emit('nameWasReset', {name: this.name});
    }
  },
  // Adding a hook that will listen to the changes from time when it was created
  created() {
    eventBus.$on('ageWasEditted', (eventAge) => {
      this.userAge = eventAge.age;
    });
    eventBus.$on('nameWasReset', (eventName) => {
      this.name = eventName.name;
    });
  }
}
</script>

<style scoped>
div {
  background-color: lightcoral;
}
</style>
