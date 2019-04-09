<template>
  <div>
    <p>Counter is: {{ counter }}</p>
    <p>Number of click: {{ clicks }}</p>
  </div>
</template>

<script>
    import { mapGetters} from 'vuex';
    export default {
        computed: {
          // Spread operation "..."
          // Being able to use mapGetters with our own local computed properties
          ...mapGetters({
          counter: 'doubleCounter',
          clicks: 'stringCounter'
        })
      }
    }
</script>
