<template lang="html">
  <div>
    <div id="on"
      v-on:click="logIsOnChange(true)"
      v-bind:class="{active: isOn}"
    >On</div>
    <div id="off"
      v-on:click="logIsOnChange(false)"
      v-bind:class="{active: !isOn}"
    >Off</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isOn: true
    }
  },
  methods:{
    logIsOnChange(value){
      this.isOn = value
      console.log(this.isOn);
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
