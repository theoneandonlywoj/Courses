<template>
    <div class="container">
      <appProgressBar v-bind:quoteCount="quotes.length" v-bind:maxQuotes="maxQuotes"></appProgressBar>
      <appNewQuote v-on:quoteAdded="newQuoteArrived"></appNewQuote>
      <appQuoteGrid v-bind:quotes=quotes v-on:quoteDeleted="deleteQuote"></appQuoteGrid>
      <div class="row">
        <div class="col-sm-12 text-center">
          <div class="alert alert-info">Info: Click on a Quote to delete it!</div>
        </div>
      </div>
    </div>
</template>

<script>
  import QuoteGrid from './components/QuoteGrid.vue';
  import NewQuote from './components/NewQuote.vue';
  import Header from './components/Header.vue'

  export default {
      data: function() {
        return {
          quotes: [
            'Just a Quote to see something'
          ],
          maxQuotes: 10
        }
      },
      methods: {
        newQuoteArrived(event) {
          console.log(event.quote);
          if (this.quotes.length >= this.maxQuotes){
            return alert("Please delete a Quote first!")
          } else {
            this.quotes.push(event.quote);
          }
        },
        deleteQuote(index){
          this.quotes.splice(index, 1);
        }
      },
      components: {
        appQuoteGrid: QuoteGrid,
        appNewQuote: NewQuote,
        appProgressBar: Header
      }
  }
</script>

<style>
</style>
