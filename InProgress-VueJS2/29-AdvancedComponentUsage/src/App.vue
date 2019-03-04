<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
              <appQuote></appQuote>
            </div>
        </div>
    </div>
</template>

<script>
    import Quote from './components/Quote.vue'
    export default {
      components: {
        appQuote: Quote
      }
    }
</script>

<style>
</style>
