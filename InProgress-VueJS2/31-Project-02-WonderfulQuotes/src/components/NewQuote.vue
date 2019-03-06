<template lang="html">
  <div class="row">
    <form class="" action="index.html" method="post">
      <div class="col-sm-8 col-sm-offset-2 col-xs-12 col-md-6 col-md-offset-3">
        <label>Quote</label>
        <textarea class="form-control" rows=3 v-model="quote"></textarea>
      </div>
      <div class="col-sm-8 col-sm-offset-2 col-xs-12 col-md-6 col-md-offset-3">
        <!-- Preventing submitting the form and refreshing the page, only executing the method -->
        <button class="btn btn-primary" v-on:click.prevent="createNew">Add Quote</button>
      </div>
    </form>

  </div>
</template>

<script>
export default {
  data: function() {
    return {
      quote: ''
    }
  },
  methods: {
    createNew() {
      this.$emit('quoteAdded', {quote: this.quote});
      // Resetting the form after emmiting the event
      this.quote = '';
    }
  }
}
</script>

<style lang="css" scoped>
</style>
