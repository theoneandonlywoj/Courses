<template>
    <div>
        <button class="btn btn-primary" @click="increment">Increment</button>
        <button class="btn btn-primary" @click="decrement">Decrement</button>
    </div>
</template>

<script>
    export default {
        methods: {
            increment() {
                // Committing a mutation
                this.$store.commit('increment');
            },
            decrement() {
                this.$store.commit('decrement');
            }
        }
    }
</script>
