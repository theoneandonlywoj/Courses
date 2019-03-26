<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h1>Routing</h1>
                <hr>
                <!-- Components from the routes will be rendered in <router-view> -->
                <router-view></router-view>
                <appHeader></appHeader>
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
</style>
