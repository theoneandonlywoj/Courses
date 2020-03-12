// Firebase App (the core Firebase SDK) is always required and must be listed first
import * as firebase from 'firebase/app'

// If you enabled Analytics in your project, add the Firebase SDK for Analytics
import 'firebase/analytics'

// Add the Firebase products that you want to use
import 'firebase/auth'

var firebaseConfig = {
  apiKey: 'AIzaSyAN_vXDc4hQhGM7ssouGzYk0T50IWhZw78',
  authDomain: 'awesome-todo-bfd64.firebaseapp.com',
  databaseURL: 'https://awesome-todo-bfd64.firebaseio.com',
  projectId: 'awesome-todo-bfd64',
  storageBucket: 'awesome-todo-bfd64.appspot.com',
  messagingSenderId: '982726307131',
  appId: '1:982726307131:web:b383211a8dede248835b1e',
  measurementId: 'G-GGT3B69DMD'
}

// Initialize Firebase
const firebaseApp = firebase.initializeApp(firebaseConfig)
const firebaseAuth = firebaseApp.auth()

export { firebaseAuth }
