<template>
<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
      <h1>Routing</h1>
      <hr>
      <!-- Components from the routes will be rendered in <router-view> -->
      <router-view name="header-top"></router-view>
      <transition name="slideTransition" mode="out-in">
        <router-view></router-view>
      </transition>
      <router-view name="header-bottom"></router-view>
    </div>
  </div>
</div>
</template>

<script>
import Header from './components/Header.vue'
export default {
  components: {
    appHeader: Header
  }
}
</script>

<style>
.slideTransition-enter {
  opacity: 0;
}

.slideTransition-enter-active {
  animation: slideAnimation-in 0.5s ease-out forwards;
  transition: opacity 0.5s;
}

.slideTransition-leave {}

.slideTransition-leave-active {
  animation: slideAnimation-out 1s ease-out forwards;
  transition: opacity 0.5s;
  opacity: 0;
  position: absolute;
}

@keyframes slideAnimation-in {
  from {
    transform: translateY(20px);
  }

  to {
    transform: translateY(0);
  }
}

@keyframes slideAnimation-out {
  from {
    transform: translateY(0);
  }

  to {
    transform: translateY(20px);
  }
}
</style>
