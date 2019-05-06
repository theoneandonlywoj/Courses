const state = {
  funds: 10000,
  stocks: []
};

const mutations = {
  'BUY_STOCK'(state, {stockId, quantity, stockPrice}){
    // Find if I already own this specific stock
    const record = state.stocks.find(element => element.id == stockId)
    if (record){
      record.quantity += quantity;
    } else {
      state.stocks.push({
        id: stockId,
        quantity: quantity
      })
    }
    state.funds -= stockPrice * quantity;
  },
  'SELL_STOCK'(state, {stockId, quantity, stockPrice}){
    // Find if I already own this specific stock
    const record = state.stocks.find(element => element.id == stockId)
    if (record.quantity > quantity){
      record.quantity -= quantity;
    } else {
      state.stocks.splice(state.stocks.indexOf(record), 1);
    }

    state.funds += stockPrice * quantity;
  }
};

const actions = {
  // buyStock action is in the stocks.js file
  sellStock({ commit }, payload){
    // Payload = order
    commit('SELL_STOCK', payload);
  }
};

const getters = {
  stockPortfolio(state, getters){
    return state.stocks.map(stock => {
      const record = getters.stocks.find(element => element.id == stock.id);
      return {
        id: stock.id,
        quantity: stock.quantity,
        name: record.name,
        price: record.price
      }
    })
  },
  funds(state){
    return state.funds;
  }
}

export default {
  state: state,
  mutations: mutations,
  actions: actions,
  getters: getters
}
