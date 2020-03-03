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
        icon="add"
        @click="showAddTask = true">
      </q-btn>
    </div>

    <q-dialog v-model="showAddTask">
      <AddTask></AddTask>
    </q-dialog>
  </q-page>
</template>

<script>
import Task from '../components/Tasks/Task'
import AddTask from '../components/Tasks/Modals/AddTask'

export default {
  name: 'PageTodo',
  components: {
    Task,
    AddTask
  },
  data () {
    return {
      showAddTask: false
    }
  },
  computed: {
    tasks () {
      return this.$store.getters['tasks/tasksGetter']
    }
  }
}
</script>
