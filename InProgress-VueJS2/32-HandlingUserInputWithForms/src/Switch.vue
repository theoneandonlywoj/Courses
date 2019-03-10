<template lang="html">
  <div>
    <div id="on"
      v-on:click="logIsOnChange(true)"
      v-bind:class="{active: value}"
    >On</div>
    <div id="off"
      v-on:click="logIsOnChange(false)"
      v-bind:class="{active: !value}"
    >Off</div>
  </div>
</template>

<script>
export default {
  props: ['value'],
  methods:{
    logIsOnChange(value){
      this.value = value
      // v-model expects 'input' event
      this.$emit('input', value);
      console.log(this.value);
    }
  }
}
</script>

<style lang="css" scoped>
  #on, #off {
    width: 40px;
    height: 20px;
    background-color: lightgray;
    padding: 2px;
    display: inline-block;
    margin: 10px -2px;
    box-sizing: content-box;
    cursor: pointer;
    text-align: center;
  }
  #on:hover, #on.active {
    background-color: lightgreen;
  }

  #off:hover, #off.active {
    background-color: lightcoral;
  }
</style>
