<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h1>Built-in Directives</h1>
                <p v-text="'Some Text'"></p>
                <!-- It is not recommended to use v-html -->
                <p v-html="'<strong>Strong Text</strong>'"></p>
            </div>
            <hr>
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h1>Custom Directives</h1>
                <!-- Setting a value to the directive -->
                <!-- Using a modifier. Modifiers can be chained. -->
                <p v-highlight:background.delayed="'green'">Color this</p>
                <p v-local-highlight:background.delayed.blink="{mainColor: 'red', secondColor: 'green', blinkDelay: 500}">Color this (local directive)</p>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
      // Registering local directives
      directives: {
        'local-highlight': {
          bind(el, binding, vnode) {
            // Setting the style from the input arguments
            var delay = 0;
            // Passing a modifier
            if (binding.modifiers['delayed']){
              delay = 1000;
            }
            if (binding.modifiers['blink']){
              let mainColor = binding.value.mainColor;
              let secondColor = binding.value.secondColor;
              let currentColor = mainColor;
              setTimeout(() => {
                setInterval(() => {
                  currentColor == secondColor ? currentColor = mainColor : currentColor = secondColor;
                  if (binding.arg == 'background'){
                    el.style.backgroundColor = currentColor;
                  }
                }, binding.value.blinkDelay);
              }, delay);

            } else {
              setTimeout(() => {
                if (binding.arg == 'background'){
                  el.style.backgroundColor=binding.value.mainColor;
                }
              }, delay);
            }


          }
        }
      }
    }
</script>

<style>

</style>
