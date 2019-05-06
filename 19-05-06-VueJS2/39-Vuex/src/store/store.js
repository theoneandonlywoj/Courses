import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    counter: 0,
    value: 0
  },
  getters: {
    doubleCounter: state => {
      return state.counter * 2;
    },
    stringCounter: state => {
      return state.counter + ' click(s)';
    },
    valueGetter: state => {
      return state.value;
    }
  },
  mutations: {
    increment: (state, payload) => {
      state.counter += payload;
    },
    decrement: state => {
      state.counter--;
    },
    updateValue: (state, payload) => {
      state.value = payload;
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
    },
    updateValueAction: ({ commit }, payload) => {
      commit('updateValue', payload);
    }
  }
});
