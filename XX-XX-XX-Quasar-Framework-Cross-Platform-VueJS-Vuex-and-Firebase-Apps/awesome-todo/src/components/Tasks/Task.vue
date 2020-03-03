<template>
  <q-item
    @click="updateTask({
        id: taskId,
        updates: {
            completed: !task.completed
        }})"
    clickable
    v-ripple
    :class="!task.completed ? 'bg-orange-1' : 'bg-green-1' ">
    <q-item-section side top>
        <q-checkbox
            :value="task.completed"
            class="no-pointer-events"/>
    </q-item-section>

    <q-item-section>
        <q-item-label
          :class="{
              'text-strike': task.completed
          }">
          {{ task.name }}
        </q-item-label>
    </q-item-section>

    <q-item-section
      v-if="task.dueDate"
      side>
      <div class="row">
        <div class="column justify-center">
            <q-icon
              name="event"
              size="18px"
              class="q-mr-xs"
            ></q-icon>
        </div>
        <div class="column">
            <q-item-label
                caption
                class="row justify-end"
            >
              {{ task.dueDate}}
            </q-item-label>
            <q-item-label
                caption
                class="row justify-end"
            >
            <small>{{ task.dueTime }}</small>
            </q-item-label>
        </div>
      </div>
    </q-item-section>
    <q-item-section side>
        <q-btn
            flat
            dense
            rounded
            color="red"
            icon="delete"
            @click.stop="promptToDelete(taskId)"
            >
        </q-btn>
    </q-item-section>
    </q-item>
</template>

<script>
export default {
  name: 'Task',
  props: ['task', 'taskId'],
  methods: {
    updateTask (taskUpdatesObject) {
      this.$store.dispatch('tasks/updateTaskAction', taskUpdatesObject)
    },
    deleteTask (taskId) {
      this.$store.dispatch('tasks/deleteTaskAction', taskId)
    },
    promptToDelete (taskId) {
      this.$q.dialog({
        title: 'Confirm',
        message: 'Really deleted?',
        cancel: {
          push: true,
          color: 'negative'
        },
        persistent: true
      }).onOk(() => {
        this.deleteTask(taskId)
      })
    }
  }
}
</script>

<style>

</style>
