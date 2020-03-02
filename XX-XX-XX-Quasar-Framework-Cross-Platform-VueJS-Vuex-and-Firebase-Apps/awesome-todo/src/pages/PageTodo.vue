<template>
  <q-page class="q-pa-md">
    <q-list
      separator
      bordered>

      <Task
        v-for="(task, key) in tasks"
        :key="key"
        :task="task">
      </Task>

    </q-list>
  </q-page>
</template>

<script>
import Task from '../components/Tasks/Task'

export default {
  name: 'PageTodo',
  components: {
    Task
  },
  computed: {
    tasks () {
      return this.$store.getters['tasks/tasksGetter']
    }
  }
}
</script>
