export function updateTaskMutation (state, payload) {
  Object.assign(state.tasks[payload.id], payload.updates)
}
