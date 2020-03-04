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
          @clearDueDate="clearDateFunction()"/>
        <div class="row q-mb-sm">
        <!-- Task Due Time -->
        <q-input
            v-if="taskToSubmit.dueDate"
            v-model="taskToSubmit.dueTime"
            clearable
            outlined
            label="Due Time"
            class="col"
            >
            <template v-slot:append>
                <q-icon name="access_time" class="cursor-pointer">
                    <q-popup-proxy transition-show="scale" transition-hide="scale">
                    <q-time v-model="taskToSubmit.dueTime" />
                    </q-popup-proxy>
                </q-icon>
            </template>
        </q-input>
        </div>
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
export default {
  components: {
    ModalHeader,
    ModalTaskName,
    ModalDueDate
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
    clearDateFunction () {
      this.taskToSubmit.dueDate = ''
      this.taskToSubmit.dueTime = ''
    }
  }
}
</script>

<style>

</style>
