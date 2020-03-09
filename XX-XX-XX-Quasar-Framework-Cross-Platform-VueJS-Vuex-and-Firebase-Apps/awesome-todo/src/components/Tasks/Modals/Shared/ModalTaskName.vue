<template>
    <div class="row q-mb-sm">
        <!-- Task Name -->
        <q-input
            clearable
            autofocus
            outlined
            :value="taskName"
            label="Task Name"
            class="col"
            ref="newTaskName"
            :rules="[val => !!val || 'Field is required']"
            @input="$emit('update:taskName', $event)"
            v-select-all
        />
    </div>
</template>

<script>
export default {
  props: ['taskName'],
  directives: {
    selectAll: {
      inserted (el) {
        // When the element is insterted and the length of the value > 0, select all.
        const input = el.querySelector('.q-field__native')
        input.addEventListener('focus', () => {
          if (input.value.length) {
            input.select()
          }
        })
      }
    }
  }
}
</script>

<style>

</style>
