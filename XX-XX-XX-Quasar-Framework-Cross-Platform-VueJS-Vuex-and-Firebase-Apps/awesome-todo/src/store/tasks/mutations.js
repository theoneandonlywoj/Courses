import Vue from 'vue'

export function updateTaskMutation (state, payload) {
  Object.assign(state.tasks[payload.id], payload.updates)
}

export function deleteTaskMutation (state, id) {
  Vue.delete(state.tasks, id)
}

export function addTaskMutation (state, payload) {
  Vue.set(state.tasks, payload.id, payload.task)
}
