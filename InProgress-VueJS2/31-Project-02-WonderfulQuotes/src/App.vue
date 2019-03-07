<template>
    <div class="container">
      <appNewQuote v-on:quoteAdded="newQuoteArrived"></appNewQuote>
      <appQuoteGrid v-bind:quotes=quotes></appQuoteGrid>
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
          this.quotes.push(event.quote)
        }
      },
      components: {
        appQuoteGrid: QuoteGrid,
        appNewQuote: NewQuote
      }
  }
</script>

<style>
</style>
