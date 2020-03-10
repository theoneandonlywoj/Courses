export function setSomeLocalStorageSettingMutation (state, value) {
  state.settings.someLocalStorageSetting = value
}

export function setSettingMutation (state, value) {
  state.settings = value
}
