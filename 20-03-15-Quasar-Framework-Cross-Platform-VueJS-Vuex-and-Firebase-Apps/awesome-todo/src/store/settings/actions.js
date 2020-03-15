import { LocalStorage } from 'quasar'

export function setSomeLocalStorageSettingAction ({ commit, dispatch }, value) {
  commit('setSomeLocalStorageSettingMutation', value)
  dispatch('saveSettingsToLocalStorageAction')
}

export function saveSettingsToLocalStorageAction ({ state }) {
  LocalStorage.set('settings', state.settings)
}

export function loadSettingsFromLocalStorageAction ({ commit, state }) {
  const value = LocalStorage.getItem('settings')
  if (value) {
    commit('setSettingMutation', value)
  }
}
