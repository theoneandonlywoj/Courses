import { uid } from 'quasar'
import { firebaseAuth, firebaseDb } from '../../boot/firebase'

export function updateTaskAction ({ dispatch }, payload) {
  dispatch('firebaseUpdateTaskAction', payload)
}

export function deleteTaskAction ({ commit }, id) {
  commit('deleteTaskMutation', id)
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

export function firebaseReadDataAction ({ commit }) {
  const userId = firebaseAuth.currentUser.uid
  const userTasks = firebaseDb.ref(`tasks/${userId}`)
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
  newTaskRef.set(payload.task)
}

export function firebaseUpdateTaskAction ({ commit }, payload) {
  const userId = firebaseAuth.currentUser.uid
  const updatedTaskRef = firebaseDb.ref(`tasks/${userId}/${payload.id}`)
  updatedTaskRef.update(payload.updates)
}
