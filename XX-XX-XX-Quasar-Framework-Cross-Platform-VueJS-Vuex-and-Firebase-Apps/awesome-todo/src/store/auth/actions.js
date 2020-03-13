import { LocalStorage } from 'quasar'
import { firebaseAuth } from './../../boot/firebase'
import { showErrorMessage } from './../../functions/function-error-show-message'

export function registerUserAction ({ commit }, payload) {
  // commit('registerUserMutation', value)
  firebaseAuth.createUserWithEmailAndPassword(payload.email, payload.password)
    .then(res => {
      console.log('res', res)
    })
    .catch(e => {
      showErrorMessage(e.message)
    })
}

export function loginUserAction ({ commit }, payload) {
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

export function handleAuthStateChangeAction ({ commit }) {
  console.log('State change')
  firebaseAuth.onAuthStateChanged(user => {
    if (user) {
      console.log('userStatusChanged')
      commit('setLoggedInMutation', true)
      LocalStorage.set('loggedIn', true)
      this.$router.push({ name: 'PageTodo' }).catch(() => {
        console.log('Navigation duplication for already logged in users.')
      })
    } else {
      commit('setLoggedInMutation', false)
      LocalStorage.set('loggedIn', false)
      this.$router.replace({ name: 'Auth' })
    }
  })
}
