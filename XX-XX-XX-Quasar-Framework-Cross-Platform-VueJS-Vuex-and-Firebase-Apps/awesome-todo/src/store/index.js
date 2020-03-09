import Vue from 'vue'
import Vuex from 'vuex'

import tasks from './tasks'

Vue.use(Vuex)

Vue.directive('select-all', {
  inserted (el) {
    // When the element is insterted and the length of the value > 0, select all.
    const input = el.querySelector('.q-field__native')
    input.addEventListener('focus', () => {
      if (input.value.length) {
        input.select()
      }
    })
  }
})
/*
 * If not building with SSR mode, you can
 * directly export the Store instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Store instance.
 */

export default function (/* { ssrContext } */) {
  const Store = new Vuex.Store({
    modules: {
      tasks
    },

    // enable strict mode (adds overhead!)
    // for dev mode only
    strict: process.env.DEV
  })

  return Store
}
