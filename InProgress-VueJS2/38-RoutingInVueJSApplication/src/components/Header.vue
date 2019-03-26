<template lang="html">
  <ul class="nav nav-pills">
    <li role="presentation">
      <router-link :to="{ name: 'home', params: {} }">Home</router-link>
    </li>
    <li role="presentation">
      <router-link :to="{ name: 'user', params: {} }">User</router-link>
    </li>

  </ul>
</template>
