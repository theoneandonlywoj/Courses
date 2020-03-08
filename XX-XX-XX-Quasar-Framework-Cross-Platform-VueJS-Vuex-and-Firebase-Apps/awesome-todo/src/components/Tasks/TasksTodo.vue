<template>
  <q-list
      v-if="Object.keys(tasksTodo).length"
      separator
      bordered>

      <Task
        v-for="(task, key) in tasksTodo"
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
    tasksTodo () {
      return this.$store.getters['tasks/tasksTodoGetter']
    }
  }
}
</script>

<style>

</style>
