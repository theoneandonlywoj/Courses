import User from './components/user/User.vue'
import Home from './components/Home.vue'
import UserStart from './components/user/UserStart.vue'
import UserDetail from './components/user/UserDetail.vue'
import UserEdit from './components/user/UserEdit.vue'
export const routes = [
  { name: "home", path: '', component: Home},
  // Passing parameters
  //{ name: "user", path: '/user/:id', component: User}
  // Configuring subroutes using "children" key
  { name: "user", path: '/user', component: User, children: [
    { name: 'userStart', path: '/userStart', component: UserStart },
    { name: 'userDetail', path: ':id', component: UserDetail },
    { name: 'userEdit', path: ':id/edit', component: UserEdit }
  ]}
]
