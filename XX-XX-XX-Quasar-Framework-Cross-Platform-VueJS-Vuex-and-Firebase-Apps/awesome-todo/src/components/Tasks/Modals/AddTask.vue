<template>
  <q-card>
    <q-card-section class="row">
        <div class="text-h6">Add Task</div>
        <q-space/>
        <q-btn
            flat
            round
            dense
            icon="close"
            v-close-popup
        >
        </q-btn>
    </q-card-section>

    <q-form @submit.prevent="submitForm()">
        <q-card-section class="q-pt-none">
        <div class="row q-mb-sm">
            <!-- Task Name -->
            <q-input
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
            outlined
            label="Due Time"
            v-model="taskToSubmit.dueTime">
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
export default {
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
      this.refs.newTaskName.validate()
      if (!this.refs.hasError) {
        this.submitTask()
      }
    },
    submitTask () {
      console.log('Submit Task')
    }
  }
}
</script>

<style>

</style>
