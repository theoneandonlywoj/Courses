<template>
<div>
    <q-banner
        dense
        class="text-white
               bg-orange-4
               text-center">
      <span class="text-bold text-subtitle-1">
          To Do
      </span>
    </q-banner>
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
</div>
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
