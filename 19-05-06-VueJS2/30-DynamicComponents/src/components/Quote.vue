<template lang="html">
  <div>
    <!-- Using VueJS slot -->
    <div class="title">
      <slot name="title"></slot>
      <span style="color: #ccc">
        <slot name="subtitle">
          <!-- Passing default content of the slot.
          It will be used if no values are provided. -->
          Default content
        </slot>
      </span>
    </div>
    <div class="content">
      <slot name="content"></slot>
    </div>
  </div>
</template>

<script>
export default {}
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

.title {
  font-style: italic;
}
</style>
