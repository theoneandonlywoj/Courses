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
                <!-- Using type animation makes the animation
                     to dictate the length of the changes -->
                <transition name="slideElement" type="animation">
                  <div class="alert alert-info" v-if="show">
                    Slide
                  </div>
                </transition>
                <!-- Transition on creation using 'appear'-->
                <transition name="myElement" appear>
                  <div class="alert alert-info" v-if="show">
                    On Creation
                  </div>
                </transition>
                <!-- Using AnimateCSS -->
                <!-- Forcing Vue to use specific classes, without 'v-' prefix -->
                <!-- Possible options:
                     - enter-class
                     - enter-active-class
                     - leave-class
                     - leave-active-class -->
                <transition
                  appear
                  enter-active-class="animated bounce"
                  leave-active-class="animated bounce">
                  <div class="alert alert-info" v-show="show">
                    Animated with AnimateCSS
                  </div>
                </transition>
                <!-- Using dynamic name -->
                <hr>
                <select v-model="alertAnimation">
                  <option value="myElement">Fade</option>
                  <option value="slideElement">Slide</option>
                </select>
                <transition
                  appear
                  v-bind:name="alertAnimation">
                  <div class="alert alert-info" v-show="show">
                    Animated from dropdown!
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
              show: true,
              alertAnimation: 'myElement'
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
    opacity: 0;
  }

  .slideElement-enter-active {
    animation: slideAnimation-in 1s ease-out forwards;
    transition: opacity 0.5s;
  }

  .slideElement-leave {

  }

  .slideElement-leave-active {
    animation: slideAnimation-out 1s ease-out forwards;
    transition: opacity 3s;
    opacity: 0;
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
