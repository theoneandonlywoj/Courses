import { uid } from 'quasar'
import { firebaseAuth, firebaseDb } from '../../boot/firebase'

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
}
