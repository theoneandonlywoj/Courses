<template>
  <div class="component">
    <h3>You may view the User Details here</h3>
    <p>Many Details</p>
    <p>User Name: {{ switchName() }}</p>
  </div>
</template>

<script>
// Getting data from the parent component using properties
export default {
  // 'name' must match name used in the template
  props: ['name'],
  methods: {
    switchName() {
      return this.name.split("").reverse().join("");
    }
  }
}
</script>

<style scoped>
div {
  background-color: lightcoral;
}
</style>
