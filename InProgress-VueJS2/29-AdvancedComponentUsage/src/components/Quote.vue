<template lang="html">
  <div>
    <!-- Using VueJS slot -->
    <slot></slot>
  </div>
</template>

<script>
export default {
}
</script>

<style scoped>
  div {
    border: 1px solid #ccc;
    box-shadow: 1px 1px 2px black;
    padding: 30px;
    margin: 30px auto;
    text-align: center;
  }

  /*
  By default, style of the slot is applied from the place where the slot is filled.
  */
  h2 {
    color: red;
  }
</style>
