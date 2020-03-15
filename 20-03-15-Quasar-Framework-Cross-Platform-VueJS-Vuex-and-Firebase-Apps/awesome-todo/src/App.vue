<template>
  <div id="q-app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted () {
    this.$store.dispatch('settings/loadSettingsFromLocalStorageAction')
    this.$store.dispatch('auth/handleAuthStateChangeAction')
  }
}
</script>
