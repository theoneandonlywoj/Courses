<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="leftDrawerOpen = !leftDrawerOpen"
        />

        <q-toolbar-title>
          Awesome Todo
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-footer >
      Footer
    </q-footer>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      content-class="bg-grey-1"
    >
      <q-list>
        <q-item-label
          header
          class="text-grey-8"
        >
          Navigation
        </q-item-label>

         <q-item
            clickable
            exact
            :to="{ name: 'PageTodo'}"
          >
            <q-item-section
              avatar
            >
              <q-icon name="list" />
            </q-item-section>

            <q-item-section>
              <q-item-label>Todo</q-item-label>
            </q-item-section>
          </q-item>

          <q-item
            clickable
            exact
            :to="{ name: 'Settings'}"
          >
            <q-item-section
              avatar
            >
              <q-icon name="settings" />
            </q-item-section>

            <q-item-section>
              <q-item-label>Settings</q-item-label>
            </q-item-section>
          </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'MainLayout',

  components: {
  },

  data () {
    return {
      leftDrawerOpen: false
    }
  }
}
</script>
