<template>
  <q-input
    outlined
    v-select-all
    v-model="searchField"
    label="Search"
    class="col">
    <template v-slot:append>
        <q-icon
            v-if="searchField !== ''"
            name="close"
            @click="searchField = ''"
            class="cursor-pointer" />
        <q-icon name="search" />
    </template>
  </q-input>
</template>

<script>
export default {
  computed: {
    searchField: {
      get () {
        return this.$store.getters['tasks/searchGetter']
      },
      set (value) {
        this.$store.dispatch('tasks/setSearchValueAction', value)
      }
    }
  }

}
</script>

<style>

</style>
