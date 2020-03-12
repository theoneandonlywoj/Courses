import { firebaseAuth } from './../../boot/firebase'

export function registerUserAction ({ commit }, payload) {
  // commit('registerUserMutation', value)
  firebaseAuth.createUserWithEmailAndPassword(payload.email, payload.password)
    .then(res => {
      console.log('res', res)
    })
    .catch(e => {
      console.log('Register Error', e)
    })
}

export function loginUserAction ({ commit }, payload) {
  firebaseAuth.signInWithEmailAndPassword(payload.email, payload.password)
    .then(res => {
      console.log('res', res)
    })
    .catch(e => {
      console.log('Register Error', e)
    })
}

export function handleAuthStateChangeAction ({ commit }) {
  console.log('State change')
  firebaseAuth.onAuthStateChanged((user) => {
    if (user) {
      this.commit('setLoggedInMutation', true)
    } else {
      this.commit('setLoggedInMutation', false)
    }
  })
}
