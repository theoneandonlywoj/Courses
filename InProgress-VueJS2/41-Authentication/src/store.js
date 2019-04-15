import Vue from 'vue'
import Vuex from 'vuex'
import axios from './axios-auth'
import globalAxios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    idToken: null,
    userId: null,
    user: null
  },
  mutations: {
    authUser(state, userData){
      state.idToken = userData.token;
      state.userId = userData.userId;
    },
    storeuser(state, user){
      state.user = user;
    }
  },
  actions: {
    signup({commit, dispatch}, authData){
      const firebaseExpectedSignUpData = {
        email: authData.email,
        password: authData.password,
        returnSecureToken: true
      }
      axios.post('/signupNewUser?key=AIzaSyCKWv_aD1daU9hn3e4NpRXUxlRx9lJMxTw', firebaseExpectedSignUpData)
        .then(res => {
          console.log(res)
          commit('authUser', {
            token: res.data.idToken,
            userId: res.data.localId
          });
          console.log('Dispatching storeuser');
          dispatch('storeUser', authData);
        })
        .catch(error => console.log(error))
    },
    login({commit}, authData){
      const firebaseExpectedSignInData = {
        email: authData.email,
        password: authData.password,
        returnSecureToken: true
      }
      axios.post('/verifyPassword?key=AIzaSyCKWv_aD1daU9hn3e4NpRXUxlRx9lJMxTw', firebaseExpectedSignInData)
        .then(res => {
          console.log(res)
          commit('authUser', {
            token: res.data.idToken,
            userId: res.data.localId
          })
        })
        .catch(error => console.log(error))
    },
    storeUser({commit, state}, userData){
      // If the idToken is not null
      if (!state.idToken){
        return
      }
      globalAxios('/users.json' + '?auth=' + state.idToken, userData)
        .then(res => console.log(res))
        .catch(err => console.log(err))
    },
    fetchUser({commit}){
      globalAxios.get('/users.json' + '?auth=' + state.idToken)
        .then(res => {
          console.log(res)
          const data = res.data
          const users = []
          for (let key in data) {
            const user = data[key]
            user.id = key
            users.push(user)
          }
          console.log(users)
          commit('storeUser', users[0])
        })
        .catch(error => console.log(error))
    }
  },
  getters: {
    user(state){
      return state.user
    }
  }
})
