<template>
  <div>
    <q-banner
    dense
    class="text-white
          bg-green-4
          text-center">
      <span class="text-bold text-subtitle-1">
          Completed
      </span>
    </q-banner>
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
