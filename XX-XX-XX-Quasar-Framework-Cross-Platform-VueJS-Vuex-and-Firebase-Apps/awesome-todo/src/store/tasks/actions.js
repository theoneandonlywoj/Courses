import { uid } from 'quasar'

export function updateTaskAction ({ commit }, payload) {
  commit('updateTaskMutation', payload)
}

export function deleteTaskAction ({ commit }, id) {
  commit('deleteTaskMutation', id)
}

export function addTaskAction ({ commit }, task) {
  const taskId = uid()
  const payload = {
    id: taskId,
    task: task
  }
  commit('addTaskMutation', payload)
}

export function setSearchValueAction ({ commit }, value) {
  commit('setSearchValueMutation', value)
}

export function setSortByAction ({ commit }, value) {
  commit('setSortByMutation', value)
}

export function firebaseReadDataAction ({ commit }) {
  console.log('Reading data from the Firebase database')
}
