<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
              <appQuote>
                <!-- The slot is styled on the child component side,
                but the data is passed from parent component. -->
                <h2>{{ quoteTitle }}</h2>
                <p>My amazing quote by me.</p>
              </appQuote>
            </div>
        </div>
    </div>
</template>

<script>
    import Quote from './components/Quote.vue'
    export default {
      data: function () {
        return {
          quoteTitle: 'My Quote For The Day'
        }
      },
      components: {
        appQuote: Quote
      }
    }
</script>

<style>
</style>
