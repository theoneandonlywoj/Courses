export function updateTaskAction ({ commit }, payload) {
  commit('updateTaskMutation', payload)
}

export function deleteTaskAction ({ commit }, id) {
  commit('deleteTaskMutation', id)
}
