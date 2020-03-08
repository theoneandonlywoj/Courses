export function tasksGetter (state) {
  return state.tasks
}
export function tasksToDoGetter (state) {
  const tasksToDo = {}
  Object.keys(state.tasks).forEach(key => {
    const task = state.tasks[key]
    if (!task.completed) {
      tasksToDo[key] = task
    }
  })
  return tasksToDo
}

export function tasksCompletedGetter (state) {
  const tasksCompleted = {}
  Object.keys(state.tasks).forEach(key => {
    const task = state.tasks[key]
    if (task.completed) {
      tasksCompleted[key] = task
    }
  })
  return tasksCompleted
}
