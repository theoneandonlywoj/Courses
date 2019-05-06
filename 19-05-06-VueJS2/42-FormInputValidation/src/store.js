import Vue from 'vue'
import Vuex from 'vuex'
import axios from './axios-auth'
import globalAxios from 'axios'

import router from './router'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    idToken: null,
    userId: null,
    user: null
  },
  mutations: {
    authUser(state, userData) {
      state.idToken = userData.token
      state.userId = userData.userId
    },
    storeUser(state, user) {
      state.user = user
    },
    clearAuthData(state){
      state.idToken = null;
      state.userId = null;
    }
  },
  actions: {
    signup({commit, dispatch}, authData) {
      axios.post('/signupNewUser?key=AIzaSyCKWv_aD1daU9hn3e4NpRXUxlRx9lJMxTw', {
        email: authData.email,
        password: authData.password,
        returnSecureToken: true
      })
        .then(res => {
          console.log(res)
          commit('authUser', {
            token: res.data.idToken,
            userId: res.data.localId
          })
          // Calculate expiration datetime for the token
          const now = new Date();
          const expirationDate = new Date(now.getTime() + res.data.expiresIn * 1000);
          // Using local storage
          localStorage.setItem('token', res.data.idToken)
          localStorage.setItem('expirationDate', expirationDate)
          localStorage.setItem('userId', res.data.localId)
          dispatch('storeUser', authData)
          dispatch('setLogoutTimer', res.data.expiresIn)
        })
        .catch(error => console.log(error))
    },
    tryAutoLogin({commit}){
      // Checking if the token is in the local storage
      const token = localStorage.getItem('token')
      if(token == null){
        return
      } else {
        // Checking if the token is still valid
        const expirationDate = localStorage.getItem('expirationDate');
        const now = new Date();
        if(now >= expirationDate){
          return
        } else {
          const userId = localStorage.getItem('userId');
          commit('authUser', {
            token: token,
            userId: userId
          })
        }
      }
    },
    login({commit, dispatch}, authData) {
      axios.post('/verifyPassword?key=AIzaSyCKWv_aD1daU9hn3e4NpRXUxlRx9lJMxTw', {
        email: authData.email,
        password: authData.password,
        returnSecureToken: true
      })
        .then(res => {
          console.log(res)
          commit('authUser', {
            token: res.data.idToken,
            userId: res.data.localId
          })
          // Calculate expiration datetime for the token
          const now = new Date();
          const expirationDate = new Date(now.getTime() + res.data.expiresIn * 100);
          // Using local storage
          localStorage.setItem('token', res.data.idToken)
          localStorage.setItem('expirationDate', expirationDate)
          localStorage.setItem('userId', res.data.localId)
          dispatch('setLogoutTimer', res.data.expiresIn)
        })
        .catch(error => console.log(error))
    },
    storeUser({commit, state}, userData) {
      if (!state.idToken) {
        return
      }
      globalAxios.post('/users.json' + '?auth=' + state.idToken, userData)
        .then(res => console.log(res))
        .catch(error => console.log(error))
    },
    fetchUser({commit, state}) {
      if (!state.idToken) {
        return
      }
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
    },
    logout({commit}){
      commit('clearAuthData')
      localStorage.removeItem('expirationDate');
      localStorage.removeItem('token');
      localStorage.removeItem('userId');
      router.push('/signin')
    },
    setLogoutTimer({commit}, expirationTime){
      setTimeout(() => {
        commit('clearAuthData')
      }, expirationTime * 1000)
    }
  },
  getters: {
    user(state){
      return state.user
    },
    isAuthenticated(state){
      return state.idToken !== null
    }
  }
})
