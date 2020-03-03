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
