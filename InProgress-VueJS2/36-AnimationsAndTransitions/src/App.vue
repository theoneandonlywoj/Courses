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
                <transition name="slideElement">
                  <div class="alert alert-info" v-if="show">
                    Slide
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

  .slideElement-enter {

  }

  .slideElement-enter-active {
    animation: slideAnimation-in 1s ease-out forwards;
  }

  .slideElement-leave {

  }

  .slideElement-leave-active {
    animation: slideAnimation-out 1s ease-out forwards;
  }

  @keyframes slideAnimation-in {
    from {
      transform: translateY(20px);
    } to {
      transform: translateY(0);
    }
  }

  @keyframes slideAnimation-out {
    from {
      transform: translateY(0);
    } to {
      transform: translateY(20px);
    }
  }
</style>
