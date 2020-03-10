import { LocalStorage } from 'quasar'

export function setSomeLocalStorageSettingAction ({ commit, dispatch }, value) {
  commit('setSomeLocalStorageSettingMutation', value)
  dispatch('saveSettingsToLocalStorageAction')
}

export function saveSettingsToLocalStorageAction ({ state }) {
  LocalStorage.set('settings', state.settings)
}
