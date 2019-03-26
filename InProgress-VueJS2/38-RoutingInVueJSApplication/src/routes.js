import User from './components/user/User.vue'
import Home from './components/Home.vue'

export const routes = [
  { name: "home", path: '', component: Home},
  // Passing parameters
  { name: "user", path: '/user/:id', component: User}
]
