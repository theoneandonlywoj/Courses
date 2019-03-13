<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h1>Filters & Mixins</h1>
                <!-- Adding a filter using | -->
                <!-- Chaining local and global filters together -->
                <p>{{ text | toUppercase | to-lowercase }}</p>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
      data() {
        return {
          text: 'Hello there'
        }
      },
      // Creating filter locally
      filters: {
        toUppercase(value) {
          return value.toUpperCase();
        }
      }
    }
</script>

<style>

</style>
