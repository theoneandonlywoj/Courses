<template>
<div>
    <ListHeader
        headerTitle="To Do"
        bgColor="bg-orange-4"
    />
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
import ListHeader from './Shared/ListHeader'

export default {
  components: {
    Task,
    ListHeader
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
