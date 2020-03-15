export function tasksGetter (state) {
  return state.tasks
}

export function tasksSortedGetter (state) {
  const tasksSorted = {}
  const keysOrdered = Object.keys(state.tasks)
  // If we return a positive value, a will be placed after b.
  keysOrdered.sort((a, b) => {
    const taskAName = state.tasks[a][state.sortBy].toLowerCase()
    const taskBName = state.tasks[b][state.sortBy].toLowerCase()
    if (taskAName > taskBName) {
      return 1
    } else if (taskAName < taskBName) {
      return -1
    } else {
      return 0
    }
  })
  keysOrdered.forEach(key => {
    tasksSorted[key] = state.tasks[key]
  })
  return tasksSorted
}
export function tasksFilteredGetter (state, getters) {
  const tasksSorted = getters.tasksSortedGetter
  const tasksFiltered = {}
  if (state.search) {
    Object.keys(tasksSorted).forEach(key => {
      const task = tasksSorted[key]
      const taskNameLowercase = task.name.toLowerCase()
      const searchLowercase = state.search.toLowerCase()
      if (taskNameLowercase.includes(searchLowercase)) {
        tasksFiltered[key] = task
      }
    })
    return tasksFiltered
  } else {
    return tasksSorted
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

export function sortByGetter (state) {
  return state.sortBy
}

export function tasksDownloadedGetter (state) {
  return state.tasksDownloaded
}
