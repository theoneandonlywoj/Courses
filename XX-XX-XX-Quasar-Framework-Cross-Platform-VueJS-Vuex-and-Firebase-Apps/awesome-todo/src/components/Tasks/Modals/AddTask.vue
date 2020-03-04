<template>
  <q-card>
    <ModalHeader>
      Add Task
    </ModalHeader>
      <q-form @submit.prevent="submitForm()">
        <q-card-section class="q-pt-none">
        <ModalTaskName :taskName.sync="taskToSubmit.name"/>
        <ModalDueDate
          :taskDueDate.sync="taskToSubmit.dueDate"
          @clearDueDate="clearDueDate()"/>
        <ModalDueTime
          :taskDueDate="taskToSubmit.dueDate"
          :taskDueTime.sync="taskToSubmit.dueTime"
          @clearDueTime="clearDueTime()"
        />
    </q-card-section>

    <q-card-actions
        align="right"
        class="q-mb-md">
        <q-btn
            label="Save"
            color="primary"
            type="submit"
        />
    </q-card-actions>
    </q-form>
  </q-card>
</template>

<script>
import ModalHeader from './Shared/ModalHeader'
import ModalTaskName from './Shared/ModalTaskName'
import ModalDueDate from './Shared/ModalDueDate'
import ModalDueTime from './Shared/ModalDueTime'

export default {
  components: {
    ModalHeader,
    ModalTaskName,
    ModalDueDate,
    ModalDueTime
  },
  data () {
    return {
      taskToSubmit: {
        name: '',
        dueDate: '',
        dueTime: '',
        completed: false
      }
    }
  },
  methods: {
    submitForm () {
      console.log(this.taskToSubmit)
      // Validation
      this.$refs.newTaskName.validate()
      if (!this.$refs.newTaskName.hasError) {
        this.submitTask()
      }
    },
    submitTask () {
      console.log('Submit Task')
      this.$store.dispatch('tasks/addTaskAction', this.taskToSubmit)
      this.$emit('closeAddTaskDialog')
    },
    clearDueDate () {
      this.taskToSubmit.dueDate = ''
      this.clearDueTime()
    },
    clearDueTime () {
      this.taskToSubmit.dueTime = ''
    }
  }
}
</script>

<style>

</style>
