import { LocalStorage, Loading } from 'quasar'
import { firebaseAuth } from './../../boot/firebase'
import { showErrorMessage } from './../../functions/function-error-show-message'

export function registerUserAction ({ commit }, payload) {
  // commit('registerUserMutation', value)
  Loading.show()
  firebaseAuth.createUserWithEmailAndPassword(payload.email, payload.password)
    .then(res => {
      console.log('res', res)
    })
    .catch(e => {
      showErrorMessage(e.message)
    })
}

export function loginUserAction ({ commit }, payload) {
  Loading.show()
  firebaseAuth.signInWithEmailAndPassword(payload.email, payload.password)
    .then(res => {
      console.log('res', res)
    })
    .catch(e => {
      showErrorMessage(e.message)
    })
}

export function logoutUserAction ({ commit }) {
  firebaseAuth.signOut()
}

export function handleAuthStateChangeAction ({ commit, dispatch }) {
  console.log('State change')
  firebaseAuth.onAuthStateChanged(user => {
    Loading.hide()
    if (user) {
      commit('setLoggedInMutation', true)
      LocalStorage.set('loggedIn', true)
      dispatch('tasks/firebaseReadDataAction', null, { root: true })
      this.$router.push({ name: 'PageTodo' }).catch(() => {
        console.log('Navigation duplication for already logged in users.')
      })
    } else {
      commit('setLoggedInMutation', false)
      LocalStorage.set('loggedIn', false)
      // Set tasksDownloaded to false when the user logs out
      dispatch('tasks/setTasksDownloadedAction', false, { root: true })
      this.$router.replace({ name: 'Auth' })
    }
  })
}
