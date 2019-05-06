<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h1>Vuex</h1>
                <app-result ></app-result>
                <app-another-result ></app-another-result>
                <hr>
                <h4>Counter</h4>
                <app-counter></app-counter>
                <hr>
                <h4>Another Counter</h4>
                <app-another-counter></app-another-counter>
                <hr>
                <input type="text" v-model="value">
                <p>{{ value }}</p>
            </div>
        </div>
    </div>
</template>

<script>
    import Counter from './components/Counter.vue';
    import Result from './components/Result.vue';
    import AnotherResult from './components/AnotherResult.vue'
    import AnotherCounter from './components/AnotherCounter.vue'

    export default {
        computed: {
          // Computed propery with local getter and setter
          // Rarely used
          value: {
            get(){
              return this.$store.getters.valueGetter;
            },
            set(value){
              // Setting the value with a custom action.
              this.$store.dispatch('updateValueAction', value);
            }
          }
        },
        components: {
            appCounter: Counter,
            appResult: Result,
            appAnotherResult: AnotherResult,
            appAnotherCounter: AnotherCounter,
        }
    }
</script>
