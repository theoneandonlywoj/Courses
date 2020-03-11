<template>
  <q-layout view="hHh lpR fFf">
    <q-header
      v-if="$q.platform.is.desktop"
      elevated>
      <q-toolbar>
        <q-toolbar-title class="absolute-center">
          Awesome Todo
        </q-toolbar-title>

      <q-btn
        flat
        icon-right="account_circle"
        label="Login"
        :to="{ name: 'Auth' }"
        class="absolute-right"
      >
      </q-btn>
      </q-toolbar>
    </q-header>

    <q-footer v-if="!$q.platform.is.desktop">
      <q-tabs>
        <q-route-tab
          v-for="nav in navs"
          :key="nav.id"
          :to="{ name: nav.toByName }"
          :icon="nav.icon"
          :label="nav.label" />
      </q-tabs>
    </q-footer>

    <q-drawer
      v-model="leftDrawerOpen"
      :width="250"
      show-if-above
      bordered
      content-class="bg-primary"
    >
      <q-list dark>
        <q-item-label
          header
          class="text-grey-4"
        >
          Navigation
        </q-item-label>

         <q-item
            v-for="nav in navs"
            :key="nav.id"
            clickable
            exact
            :to="{ name: nav.toByName }"
            class="text-grey-4"
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

<style lang="scss">
  .q-drawer {
    .q-router-link--active {
      color: white !important;
    }
  }
</style>
