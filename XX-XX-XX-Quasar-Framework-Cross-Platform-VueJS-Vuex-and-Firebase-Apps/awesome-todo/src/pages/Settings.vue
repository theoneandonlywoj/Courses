<template>
  <q-page>
    <q-list>
    <q-item-label header>Notifications</q-item-label>
      <q-item tag="label" v-ripple>
        <q-item-section>
          <q-item-label>Some Local Storage Setting</q-item-label>
        </q-item-section>
        <q-item-section side >
          <q-toggle color="blue"
            v-model="someLocalStorageSetting"
          />
        </q-item-section>
      </q-item>
    </q-list>
  </q-page>
</template>

<script>
export default {
  name: 'Settings',
  computed: {
    someLocalStorageSetting: {
      get () {
        return this.$store.getters['settings/settingsSomeLocalStorageSettingGetter']
      },
      set (value) {
        this.$store.dispatch('settings/setSomeLocalStorageSettingAction', value)
      }
    }
  }
}
</script>
