<template lang="html">
  <ul class="nav nav-pills">
      <!--
        Replacing a list element containing a router-link with
        a router-link with a tag "li".
        "exact" means that the active-class will be on if the current path
        is exactly the same as the path the user is currently using.
      -->
      <router-link :to="{ name: 'home', params: {} }"
       tag="li"
       active-class="active"
       exact>
        <a>Home</a>
      </router-link>
      <router-link :to="{ name: 'user', params: {id: 1} }"
       tag="li"
       active-class="active">
       <a>User 1</a>
      </router-link>
      <router-link :to="{ name: 'user', params: {id: 2} }"
       tag="li"
       active-class="active">
       <a>User 2</a>
      </router-link>
  </ul>
</template>
