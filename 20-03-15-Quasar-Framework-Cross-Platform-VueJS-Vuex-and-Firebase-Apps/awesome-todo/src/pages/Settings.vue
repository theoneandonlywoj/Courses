<template>
  <q-page>
    <q-list>
      <q-item-label header>Settings</q-item-label>
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
    <q-list>
      <q-item-label header>More</q-item-label>
        <q-item
          tag="label"
          v-ripple
          @click="emailUs()">
          <q-item-section>
            <q-item-label>Email Us</q-item-label>
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
  },
  methods: {
    emailUs () {
      window.location.href = 'mailto:wor@kth.se?subject=Awesome To Do Feedback'
    }
  }
}
</script>
