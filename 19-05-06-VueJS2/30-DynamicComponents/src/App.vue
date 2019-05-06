<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
              <button v-on:click="selectedComponent='app-quote'">Quote</button>
              <button v-on:click="selectedComponent='app-author'">Author</button>
              <button v-on:click="selectedComponent='app-new'">New</button>
              <hr>
              <p>{{ selectedComponent }}</p>
              <!-- Keeping the component alive -->
              <keep-alive>
                <!-- Dynamic component -->
                <component v-bind:is="selectedComponent">
                  <p>Default</p>
                </component>
              </keep-alive>
            </div>
        </div>
    </div>
</template>

<script>
    import Quote from './components/Quote.vue'
    import Author from './components/Author.vue'
    import New from './components/New.vue'
    export default {
      data: function () {
        return {
          quoteTitle: 'My Quote For The Day',
          selectedComponent: 'app-quote'
        }
      },
      components: {
        appQuote: Quote,
        appAuthor: Author,
        appNew: New
      }
    }
</script>

<style>
</style>
