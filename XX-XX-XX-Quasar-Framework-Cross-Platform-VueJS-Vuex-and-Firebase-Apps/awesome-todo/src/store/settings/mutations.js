import { LocalStorage } from 'quasar'

export function setSomeLocalStorageSettingMutation (state, value) {
  state.settings.someLocalStorageSetting = value
}

export function saveSettingsToLocalStorageMutation (state) {
  LocalStorage.set('settings', state.settings)
}
