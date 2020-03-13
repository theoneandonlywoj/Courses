import { LocalStorage } from 'quasar'

export default ({ router }) => {
  router.beforeEach((to, from, next) => {
    const loggedIn = LocalStorage.getItem('loggedIn')
    if (!loggedIn && to.name !== 'Auth') {
      next({ name: 'Auth' })
    } else {
      next()
    }
  })
}
