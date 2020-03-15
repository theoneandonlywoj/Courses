<template>
    <div class="row q-mb-sm">
    <!-- Task Due Date -->
        <q-input
            clearable
            outlined
            label="Due Date"
            :value="taskDueDate"
            @clear="$emit('clearDueDate')"
        >
            <template v-slot:append>
                <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy
                        ref="qDateProxy"
                        transition-show="scale"
                        transition-hide="scale">
                        <q-date
                            @input="$emit('update:taskDueDate', $event),
                                    $refs.qDateProxy.hide()"
                            :value="taskDueDate"
                            today-btn
                        />
                    </q-popup-proxy>
                </q-icon>
            </template>
        </q-input>
    </div>
</template>

<script>
export default {
  props: ['taskDueDate', 'clearDateFunction']
}
</script>

<style>

</style>
