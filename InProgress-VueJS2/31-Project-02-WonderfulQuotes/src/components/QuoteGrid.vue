<template lang="html">
  <div class="row">
    <appQuote v-for="(quote, index) in quotes" v-on:click.native="deleteQuote(index);">{{ quote }}</appQuote>
  </div>
</template>

<script>
import Quote from './Quote.vue';
export default {
  props:['quotes'],
  methods:{
    deleteQuote(index){
      console.log("")
      this.$emit('quoteDeleted', index);
    }
  },
  components: {
    appQuote: Quote
  }
}
</script>

<style lang="css" scoped>
</style>
