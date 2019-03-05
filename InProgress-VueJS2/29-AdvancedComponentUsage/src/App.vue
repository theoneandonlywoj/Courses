<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
              <appQuote>
                <h1>My Quote For The Day</h1>
                <p>My amazing quote by me.</p>
              </appQuote>
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
