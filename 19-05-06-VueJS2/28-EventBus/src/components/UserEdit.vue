<template>
    <div class="component">
        <h3>You may edit the User here</h3>
        <p>Edit me!</p>
        <p>User Age: {{userAge}}</p>
        <button v-on:click="editAge">Add a year!</button>
    </div>
</template>

<script>
  // Importing the eventBus from the main.js file
  import { eventBus } from '../main'
  export default {
    props:['userAge'],
    methods: {
      editAge() {
        this.userAge += 1;
        //this.$emit('ageWasEditted', {age: this.userAge});
        eventBus.$emit('ageWasEditted', {age: this.userAge});
      }
    }
  }
</script>

<style scoped>
    div {
        background-color: lightgreen;
    }
</style>
