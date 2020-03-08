<template>
  <div class="q-mt-lg">
    <ListHeader
      headerTitle="Completed"
      bgColor="bg-green-4"
    />
    <q-list
      v-if="Object.keys(tasksCompleted).length"
      separator
      bordered>

      <Task
        v-for="(task, key) in tasksCompleted"
        :key="key"
        :task="task"
        :taskId="key">
      </Task>
    </q-list>
  </div>
</template>

<script>
import Task from './Task'
import ListHeader from './Shared/ListHeader'

export default {
  components: {
    Task,
    ListHeader
  },
  computed: {
    tasksCompleted () {
      return this.$store.getters['tasks/tasksCompletedGetter']
    }
  }
}
</script>

<style>

</style>
