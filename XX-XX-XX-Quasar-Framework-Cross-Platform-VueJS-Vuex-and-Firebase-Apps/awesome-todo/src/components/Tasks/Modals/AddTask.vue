<template>
  <q-card>
    <ModalHeader>
      Add Task
    </ModalHeader>
      <q-form @submit.prevent="submitForm()">
        <q-card-section class="q-pt-none">
        <div class="row q-mb-sm">
            <!-- Task Name -->
            <q-input
                clearable
                autofocus
                outlined
                v-model="taskToSubmit.name"
                label="Task Name"
                class="col"
                ref="newTaskName"
                :rules="[val => !!val || 'Field is required']"
            />
        </div>
        <div class="row q-mb-sm">
        <!-- Task Due Date -->
        <q-input
            clearable
            outlined
            label="Due Date"
            v-model="taskToSubmit.dueDate"
        >
            <template v-slot:append>
                <q-icon name="event" class="cursor-pointer">
                <q-popup-proxy
                    ref="qDateProxy"
                    transition-show="scale"
                    transition-hide="scale">
                    <q-date
                        v-model="taskToSubmit.dueDate"
                        @input="() => $refs.qDateProxy.hide()" />
                </q-popup-proxy>
                </q-icon>
            </template>
        </q-input>
        </div>
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
export default {
  components: {
    ModalHeader
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
    }
  }
}
</script>

<style>

</style>
