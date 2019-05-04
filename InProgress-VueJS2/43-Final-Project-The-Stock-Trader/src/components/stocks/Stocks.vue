<template>
  <div class="">
    <stocksStock v-for="stock in stocks" v-bind:stock="stock"></stocksStock>
  </div>
</template>

<script>
  import Stock from './Stock.vue'
  export default {
    data() {
      return {
        stocks: [
          { id: 1, name: 'BWM', price: 110 },
          { id: 2, name: 'AMZ', price: 100 },
          { id: 3, name: 'TSLA', price: 90 }
        ]
      }
    },
    components: {
      stocksStock: Stock
    }
  }

</script>
