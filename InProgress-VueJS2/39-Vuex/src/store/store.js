import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    counter: 0
  },
  getters: {
    doubleCounter: state => {
      return state.counter * 2;
    },
    stringCounter: state => {
      return state.counter + ' click(s)';
    }
  },
  mutations: {
    increment: (state, payload) => {
      state.counter += payload;
    },
    decrement: state => {
      state.counter--;
    }
  },
  actions: {
    asyncIncrement: (context, payload) => {
      setTimeout(() => {
        context.commit('increment', payload);
      }, 1000);
    },
    asyncDecrement: context => {
      setTimeout(() => {
        context.commit('decrement');
      }, 1000);
    }
  }
});
