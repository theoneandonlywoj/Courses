<template>
    <div>
        <button class="btn btn-primary" @click="asyncIncrement(100)">Increment</button>
        <button class="btn btn-primary" @click="asyncDecrement">Decrement</button>
    </div>
</template>

<script>
    import { mapMutations, mapActions } from 'vuex';
    export default {
        methods: {
            ...mapActions({
              asyncIncrement: 'asyncIncrement',
              asyncDecrement: 'asyncDecrement'
            })
        }
    }
</script>
