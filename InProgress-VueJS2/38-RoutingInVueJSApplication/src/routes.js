import User from './components/user/User.vue'
import Home from './components/Home.vue'

export const routes = [
  { name: "home", path: '', component: Home},
  { name: "user", path: '/user', component: User}
]
