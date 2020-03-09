<template>
  <q-page class="q-pa-md">
    <div class="row q-mb-lg">
      <Search />
      <Sort />
    </div>
    <TasksTodo />
    <TasksCompleted />
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
      <AddTask @closeAddTaskDialog="showAddTask = false"></AddTask>
    </q-dialog>
  </q-page>
</template>

<script>
import AddTask from '../components/Tasks/Modals/AddTask'
import TasksTodo from '../components/Tasks/TasksTodo'
import TasksCompleted from '../components/Tasks/TasksCompleted'
import Search from '../components/Tasks/Tools/Search'
import Sort from '../components/Tasks/Tools/Sort'

export default {
  name: 'PageTodo',
  components: {
    AddTask,
    TasksTodo,
    TasksCompleted,
    Search,
    Sort
  },
  data () {
    return {
      showAddTask: false
    }
  }
}
</script>
