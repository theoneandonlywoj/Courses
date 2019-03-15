<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h1>Animations</h1>
                <hr>
                <button class="btn btn-primary" v-on:click="show = !show">Show Alert</button>
                <br><br>
                <transition name="myElement">
                  <div class="alert alert-info" v-if="show">
                    Some Info
                  </div>
                </transition>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
              show: false
            }
        }
    }
</script>

<style>
  .myElement-enter {
    opacity: 0;
  }

  .myElement-enter-active {
    transition: opacity 1s;
  }

  .myElement-leave {
    opacity: 1;
  }

  .myElement-leave-active {
    transition: opacity 1s;
    opacity: 0;
  }
</style>
