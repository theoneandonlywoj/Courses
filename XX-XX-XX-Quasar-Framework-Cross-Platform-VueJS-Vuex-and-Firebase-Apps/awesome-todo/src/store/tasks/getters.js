export function tasksGetter (state) {
  return state.tasks
}

export function tasksFilteredGetter (state) {
  const tasksFiltered = {}
  if (state.search) {
    Object.keys(state.tasks).forEach(key => {
      const task = state.tasks[key]
      if (task.name.includes(state.search)) {
        tasksFiltered[key] = task
      }
    })
    return tasksFiltered
  } else {
    return state.tasks
  }
}

export function tasksTodoGetter (state, getters) {
  const tasksFiltered = getters.tasksFilteredGetter
  const tasksToDo = {}
  Object.keys(tasksFiltered).forEach(key => {
    const task = state.tasks[key]
    if (!task.completed) {
      tasksToDo[key] = task
    }
  })
  return tasksToDo
}

export function tasksCompletedGetter (state, getters) {
  const tasksFiltered = getters.tasksFilteredGetter
  const tasksCompleted = {}
  Object.keys(tasksFiltered).forEach(key => {
    const task = state.tasks[key]
    if (task.completed) {
      tasksCompleted[key] = task
    }
  })
  return tasksCompleted
}

export function searchGetter (state) {
  return state.search
}
