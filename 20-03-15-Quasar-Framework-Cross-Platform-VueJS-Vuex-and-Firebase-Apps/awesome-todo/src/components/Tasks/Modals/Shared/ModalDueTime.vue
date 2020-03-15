<template>
  <div class="row q-mb-sm">
        <!-- Task Due Time -->
        <q-input
            v-if="taskDueDate"
            :value="taskDueTime"
            clearable
            outlined
            label="Due Time"
            class="col"
            >
            <template v-slot:append>
                <q-icon name="access_time" class="cursor-pointer">
                    <q-popup-proxy
                        transition-show="scale"
                        transition-hide="scale"
                        ref="qTimeProxy"
                    >
                        <q-time
                            format24h
                            now-btn
                            @input="$emit('update:taskDueTime', $event),
                                        $refs.qTimeProxy.hide()"
                            :value="taskDueTime" />
                    </q-popup-proxy>
                </q-icon>
            </template>
        </q-input>
    </div>
</template>

<script>
export default {
  props: ['taskDueDate', 'taskDueTime']
}
</script>

<style>

</style>
