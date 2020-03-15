<template>
  <q-page>
    <div class="q-pa-md
                absolute
                full-width
                full-height
                column">
      <template v-if="tasksDownloaded">
        <div class="row q-mb-lg">
        <Search />
        <Sort />
        </div>
        <q-scroll-area class="q-scroll-area-tasks">
          <TasksTodo />
          <TasksCompleted
            class="q-mb-xl"
          />
        </q-scroll-area>
        <div
          class="absolute-bottom
                text-center
                q-mb-lg
                q-mt-lg
                no-pointer-events">
          <q-btn
            round
            color="primary"
            size="24px"
            icon="add"
            class="all-pointer-events"
            @click="showAddTask = true">
          </q-btn>
        </div>
      </template>

      <template v-else>
        <span class="absolute-center">
          <q-spinner
            color="primary"
            size="3em"
          />
        </span>
      </template>
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
  },
  computed: {
    tasksDownloaded: {
      get () {
        return this.$store.getters['tasks/tasksDownloadedGetter']
      },
      set (value) {
        this.$store.dispatch('tasks/setTasksDownloadedAction', value)
      }
    }
  }
}
</script>
<style scoped>
  .q-scroll-area-tasks {
    display: flex;
    flex-grow: 1;
  }
</style>
