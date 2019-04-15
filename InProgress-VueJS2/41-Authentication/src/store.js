import Vue from 'vue'
import Vuex from 'vuex'
import axios from './axios-auth';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    idToken: null,
    userId: null
  },
  mutations: {

  },
  actions: {
    signup({commit}, authData){
      const firebaseExpectedSignUpData = {
        email: authData.email,
        password: authData.password,
        returnSecureToken: true
      }
      axios.post('/signupNewUser?key=AIzaSyCKWv_aD1daU9hn3e4NpRXUxlRx9lJMxTw', firebaseExpectedSignUpData)
        .then(res => console.log(res))
        .catch(error => console.log(error))
    },
    login({commit}, authData){
      const firebaseExpectedSignInData = {
        email: authData.email,
        password: authData.password,
        returnSecureToken: true
      }
      axios.post('/verifyPassword?key=AIzaSyCKWv_aD1daU9hn3e4NpRXUxlRx9lJMxTw', firebaseExpectedSignInData)
        .then(res => console.log(res))
        .catch(error => console.log(error))
    }
  },
  getters: {

  }
})
