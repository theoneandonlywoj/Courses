import { uid, Notify } from 'quasar'
import { showErrorMessage } from './../../functions/function-error-show-message'
import { firebaseAuth, firebaseDb } from '../../boot/firebase'

export function updateTaskAction ({ dispatch }, payload) {
  dispatch('firebaseUpdateTaskAction', payload)
}

export function deleteTaskAction ({ dispatch }, id) {
  dispatch('firebaseDeleteTaskAction', id)
}

export function addTaskAction ({ dispatch }, task) {
  const taskId = uid()
  const payload = {
    id: taskId,
    task: task
  }
  dispatch('firebaseAddTaskAction', payload)
}

export function setSearchValueAction ({ commit }, value) {
  commit('setSearchValueMutation', value)
}

export function setSortByAction ({ commit }, value) {
  commit('setSortByMutation', value)
}

export function firebaseReadDataAction ({ commit, dispatch }) {
  const userId = firebaseAuth.currentUser.uid
  const userTasks = firebaseDb.ref(`tasks/${userId}`)

  // Change tasksDownloaded when values are downloaded
  userTasks.once('value', () => {
    commit('setTasksDownloadedMutation', true)
  }, error => {
    // Show the message and log the user out
    showErrorMessage(error.message)
    dispatch('auth/logoutUserAction', null, { root: true })
  })

  // child added
  userTasks.on('child_added', snapshot => {
    const task = snapshot.val()
    const payload = {
      id: snapshot.key,
      task: task
    }
    commit('addTaskMutation', payload)
  })

  userTasks.on('child_changed', snapshot => {
    const task = snapshot.val()
    const payload = {
      id: snapshot.key,
      updates: task
    }
    commit('updateTaskMutation', payload)
  })

  userTasks.on('child_removed', snapshot => {
    commit('deleteTaskMutation', snapshot.key)
  })
}

export function firebaseAddTaskAction ({ commit }, payload) {
  // The listener will add the task to our state
  // so we need to only one thing - add the task to the Firebase database.
  const userId = firebaseAuth.currentUser.uid
  const newTaskRef = firebaseDb.ref(`tasks/${userId}/${payload.id}`)
  newTaskRef.set(payload.task, error => {
    if (error) {
      showErrorMessage(error.message)
    } else {
      Notify.create({
        message: 'Task added!',
        timeout: 300
      })
    }
  })
}

export function firebaseUpdateTaskAction ({ commit }, payload) {
  const userId = firebaseAuth.currentUser.uid
  const updatedTaskRef = firebaseDb.ref(`tasks/${userId}/${payload.id}`)
  updatedTaskRef.update(payload.updates, error => {
    if (error) {
      showErrorMessage(error.message)
    } else {
      Notify.create({
        message: 'Task updated!',
        timeout: 300
      })
    }
  })
}

export function firebaseDeleteTaskAction ({ commit }, taskId) {
  const userId = firebaseAuth.currentUser.uid
  const toBeDeletedTaskRef = firebaseDb.ref(`tasks/${userId}/${taskId}`)
  toBeDeletedTaskRef.remove(error => {
    if (error) {
      showErrorMessage(error.message)
    } else {
      Notify.create({
        message: 'Task deleted!',
        timeout: 300
      })
    }
  })
}

export function setTasksDownloadedAction ({ commit }, value) {
  commit('setTasksDownloadedMutation', value)
}

export function clearTasksAction ({ commit }) {
  commit('clearTasksMutation')
}
