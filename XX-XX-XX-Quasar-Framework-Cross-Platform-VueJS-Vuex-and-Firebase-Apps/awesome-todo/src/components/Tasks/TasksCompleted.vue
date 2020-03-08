<template>
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
</template>

<script>
import Task from './Task'
export default {
  components: {
    Task
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
