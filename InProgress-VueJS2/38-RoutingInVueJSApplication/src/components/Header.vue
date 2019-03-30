<template lang="html">
  <ul class="nav nav-pills">
      <!--
        Replacing a list element containing a router-link with
        a router-link with a tag "li".
        "exact" means that the active-class will be on if the current path
        is exactly the same as the path the user is currently using.
      -->
      <router-link v-bind:to="{ name: 'home', params: {} }"
       tag="li"
       active-class="active"
       exact>
        <a>Home</a>
      </router-link>
      <router-link v-bind:to="{ name: 'userStart', params: {} }"
       tag="li"
       active-class="active">
       <a>User</a>
     </router-link>
  </ul>
</template>
