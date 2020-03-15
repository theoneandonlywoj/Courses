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
  props: ['taskName']
}
</script>

<style>

</style>
