<template>
  <div>
    <portfolioStock v-for="stock in stocks" v-bind:stock="stock"></portfolioStock>
  </div>
</template>

<script>
  import Stock from './Stock.vue'
  export default {
    computed: {
      stocks(){
        return this.$store.getters.stockPortfolio;
      }
    },
    components: {
      portfolioStock: Stock
    }
  }

</script>
