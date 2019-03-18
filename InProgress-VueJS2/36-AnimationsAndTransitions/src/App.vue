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
                <!-- Transitioning between Multiple Elements -->
                <!-- v-show will not work, we need to use v-if -->
                <!-- We need to specify a unique key to each element -->
                <!-- Mode changes the default behaviour:
                     - out-in => let the old element animate out
                                 and then animate the incoming element
                     - in-out => let the new element animate in
                                 and then animate out the new element-->
                <transition v-bind:name="alertAnimation" mode="out-in">
                  <div class="alert alert-info" v-if="show" key="element1">
                    Info!
                  </div>
                  <div class="alert alert-warning" v-if="!show" key="element2">
                    Warning!
                  </div>
                </transition>
                <hr>
                <button class="btn btn-primary" v-on:click="load = !load">Load / Remove Element</button>
                <br><br>
                <!-- Listening to transition events:
                    1.before-enter
                    2.enter
                    3.enter-cancelled (optional)
                    4.after-enter
                    5.before-leave
                    6.leave
                    7.leave-cancelled (optional)
                    8.after-leave
                -->
                <transition
                  v-on:before-enter="beforeEnter"
                  v-on:enter="enter"
                  v-on:after-enter="afterEnter"
                  v-on:enter-cancelled="enterCancelled"

                  v-on:before-leave="beforeLeave"
                  v-on:leave="leave"
                  v-on:after-leave="afterLeave"
                  v-on:leave-cancelled="leaveCancelled"
                >
                  <div style="width: 300px; height: 100px; background-color: lightgreen;" v-if="load"></div>
                </transition>
                <hr>
                <button class="btn btn-primary" v-on:click="selectedComponent == 'app-success-alert'
                                                            ? selectedComponent = 'app-danger-alert'
                                                            : selectedComponent = 'app-success-alert'">Toggle Component</button>
                <hr>
                <transition name="myElement" mode="out-in">
                  <component v-bind:is="selectedComponent"></component>
                </transition>
            </div>
        </div>
    </div>
</template>

<script>
    import DangerAlert from './DangerAlert.vue'
    import SuccessAlert from './SuccessAlert.vue'
    export default {
        components: {
          'app-danger-alert': DangerAlert,
          'app-success-alert': SuccessAlert
        },
        data() {
            return {
              show: false,
              alertAnimation: 'myElement',
              load: true,
              elementWidth: 100,
              selectedComponent: 'app-success-alert'
            }
        },
        methods: {
          beforeEnter(el){
            console.log('Before Enter!');
            this.elementWidth = 100;
            el.style.width = this.elementWidth + 'px';
          },
          enter(el, done) {
            console.log('Enter!');
            // Using done methods, we communicate to VueJS that we have finished
            // Done does not need to be called when a CSS animation is used.
            let round = 1;
            const interval = setInterval(() => {
              el.style.width = (this.elementWidth +  10 * round) + 'px';
              round ++;
              if (round > 20){
                clearInterval(interval);
                done();
              }
            }, 20);
          },
          afterEnter(el) {
            console.log('After Done!');
          },
          enterCancelled(el){
            console.log('Enter Cancelled!');
          },
          beforeLeave(el){
            console.log('Before Leave!');
            this.elementWidth = 300;
            el.style.width = this.elementWidth + 'px';
          }
          ,
          leave(el, done){
            console.log('Leave!');
            let round = 1;
            const interval = setInterval(() => {
              el.style.width = (this.elementWidth -  10 * round) + 'px';
              round ++;
              if (round > 30){
                clearInterval(interval);
                done();
              }
            }, 20);
          },
          afterLeave(el){
            console.log('After Leave!');
          },
          leaveCancelled(){
            console.log('Leave Cancelled!');
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
