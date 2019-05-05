<template>
  <div class="">
    <stocksStock v-for="stock in stocks" v-bind:stock="stock"></stocksStock>
  </div>
</template>

<script>
  import Stock from './Stock.vue'
  export default {
    components: {
      stocksStock: Stock
    },
    computed: {
      stocks(){
        return this.$store.getters.stocks;
      }
    }
  }

</script>
