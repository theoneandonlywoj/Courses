<template>
  <q-card>
    <ModalHeader>
      Edit Task
    </ModalHeader>
      <q-form @submit.prevent="submitForm()">
        <q-card-section class="q-pt-none">
        <ModalTaskName
          :taskName.sync="taskToSubmit.name"
          ref="modalTaskName"/>
        <ModalDueDate
          :taskDueDate.sync="taskToSubmit.dueDate"
          @clearDueDate="clearDueDate()"/>
        <ModalDueTime
          :taskDueDate="taskToSubmit.dueDate"
          :taskDueTime.sync="taskToSubmit.dueTime"
          @clearDueTime="clearDueTime()"
        />
    </q-card-section>

    <ModalButtons />
    </q-form>
  </q-card>
</template>

<script>
import ModalHeader from './Shared/ModalHeader'
import ModalTaskName from './Shared/ModalTaskName'
import ModalDueDate from './Shared/ModalDueDate'
import ModalDueTime from './Shared/ModalDueTime'
import ModalButtons from './Shared/ModalButtons'

export default {
  components: {
    ModalHeader,
    ModalTaskName,
    ModalDueDate,
    ModalDueTime,
    ModalButtons
  },
  props: [
    'task', 'taskId'
  ],
  data () {
    return {
      taskToSubmit: {}
    }
  },
  methods: {
    submitForm () {
      console.log(this.taskToSubmit)
      // Validation
      this.$refs.modalTaskName.$refs.newTaskName.validate()
      if (!this.$refs.modalTaskName.$refs.newTaskName.hasError) {
        this.submitTask()
      }
    },
    submitTask () {
      console.log('Submit Task')
      this.$store.dispatch('tasks/updateTaskAction', {
        id: this.taskId,
        updates: this.taskToSubmit
      })
      this.$emit('closeEditTaskDialog')
    },
    clearDueDate () {
      this.taskToSubmit.dueDate = ''
      this.clearDueTime()
    },
    clearDueTime () {
      this.taskToSubmit.dueTime = ''
    }
  },
  mounted () {
    this.taskToSubmit = Object.assign({}, this.task)
  }
}
</script>

<style>

</style>
