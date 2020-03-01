<template>
  <q-layout view="hHh lpR fFf">
    <q-header
      v-if="$q.platform.is.desktop"
      elevated>
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

    <q-footer v-if="!$q.platform.is.desktop">
      <q-tabs>
        <q-route-tab
          v-for="nav in navs"
          :key="nav.id"
          :to="{ name: nav.toByName}"
          :icon="nav.icon"
          :label="nav.label" />
      </q-tabs>
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
            v-for="nav in navs"
            :key="nav.id"
            clickable
            exact
            :to="{ name: nav.toByName}"
          >
            <q-item-section
              avatar
            >
              <q-icon :name="nav.icon" />
            </q-item-section>

            <q-item-section>
              <q-item-label>{{ nav.label }}</q-item-label>
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
      leftDrawerOpen: false,
      navs: [
        {
          id: 1,
          label: 'Todo',
          icon: 'list',
          toByName: 'PageTodo'
        },
        {
          id: 2,
          label: 'Settings',
          icon: 'settings',
          toByName: 'Settings'
        }
      ]
    }
  }
}
</script>
