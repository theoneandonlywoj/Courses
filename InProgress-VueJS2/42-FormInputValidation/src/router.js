import Vue from 'vue'
import VueRouter from 'vue-router'
import store from './store'

import WelcomePage from './components/welcome/welcome.vue'
import DashboardPage from './components/dashboard/dashboard.vue'
import SignupPage from './components/auth/signup.vue'
import SigninPage from './components/auth/signin.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', component: WelcomePage },
  { path: '/signup', component: SignupPage },
  { path: '/signin', component: SigninPage },
  { path: '/dashboard',
    component: DashboardPage,
    beforeEnter(to, from, next){
      // Checking if the idToken is set
      if (store.state.idToken){
        // Continue...
        next()
      } else{
        // Redirect to '/signin'
        next('/signin')
      }
    }
  }
]

export default new VueRouter({mode: 'history', routes})
