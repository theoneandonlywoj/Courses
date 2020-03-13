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

export function logoutUserAction ({ commit }) {
  firebaseAuth.signOut()
}

export function handleAuthStateChangeAction ({ commit }) {
  console.log('State change')
  firebaseAuth.onAuthStateChanged(user => {
    if (user) {
      console.log('userStatusChanged')
      commit('setLoggedInMutation', true)
      this.$router.push({ name: 'PageTodo' }).catch(() => {
        console.log('Navigation duplication for already logged in users.')
      })
    } else {
      commit('setLoggedInMutation', false)
      this.$router.replace({ name: 'Auth' })
    }
  })
}
