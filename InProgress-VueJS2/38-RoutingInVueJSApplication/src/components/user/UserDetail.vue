<template>
  <div>
    <h3>Some User Details</h3>
    <p>User ID: {{ $route.params.id }}</p>
    <router-link tag="button"
      class="btn btn-primary"
      v-bind:to="{ name: 'userEdit',
                   params: {id: $route.params.id},
                   query: {
                     locale: 'en',
                     userID: $route.params.id
                     } }">Edit</router-link>
  </div>

</template>
