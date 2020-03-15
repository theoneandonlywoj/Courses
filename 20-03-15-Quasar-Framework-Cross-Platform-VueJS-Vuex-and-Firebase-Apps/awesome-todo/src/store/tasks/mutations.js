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

export function setSearchValueMutation (state, payload) {
  state.search = payload
}

export function setSortByMutation (state, value) {
  state.sortBy = value
}

export function setTasksDownloadedMutation (state, value) {
  state.tasksDownloaded = value
}

export function clearTasksMutation (state) {
  state.tasks = {}
}
