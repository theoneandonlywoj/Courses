<template>
    <q-banner
        dense
        :class="bgColor"
        class="list-header
            text-white
            text-center">
        <span class="text-bold text-subtitle-1">
            {{ headerTitle }}
        </span>
    </q-banner>
</template>

<script>
export default {
  props: ['headerTitle', 'bgColor']
}
</script>

<style>
    .list-header {
        border-top-left-radius: 5px;
        border-top-right-radius: 5px;
    }
</style>
