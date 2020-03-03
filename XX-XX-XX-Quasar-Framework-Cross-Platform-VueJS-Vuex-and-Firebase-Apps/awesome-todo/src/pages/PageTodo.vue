<template>
  <q-page class="q-pa-md">
    <q-list
      separator
      bordered>

      <Task
        v-for="(task, key) in tasks"
        :key="key"
        :task="task"
        :taskId="key">
      </Task>

    </q-list>

    <div
      class="absolute-bottom
             text-center
             q-mb-lg">
      <q-btn
        round
        color="primary"
        size="24px"
        icon="add">
      </q-btn>
    </div>
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
