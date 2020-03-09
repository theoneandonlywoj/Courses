<template>
  <q-select
    filled
    v-model="sortBy"
    :options="options"
    label="Sort By"
    stack-label
    emit-value
    map-options
    class="col q-ml-sm"
    />
</template>

<script>
export default {
  data () {
    return {
      sortBy: 'name',
      options: [
        {
          label: 'Name',
          value: 'name'
        },
        {
          label: 'Due Date',
          value: 'dueDate'
        }
      ]
    }
  }
}
</script>

<style scoped>
    .q-select {
        flex: 0 0 150px;
    }
</style>
