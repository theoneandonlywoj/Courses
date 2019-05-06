<template>
  <div class="col-sm-6 col-md-4">
    <div class="panel panel-success">
      <div class="panel-heading">
        <h3 class="panel-title">
          {{ stock.name }}
          <small>(Price: {{ stock.price }})</small>
        </h3>
      </div>
      <div class="panel-body">
        <div class="pull-left">
          <input type="number"
                 class="form-control"
                 v-model="quantity"
                 v-bind:class="{'danger': insufficientFunds}">
        </div>
        <div class="pull-right">
          <button class="btn btn-success"
                  v-on:click="buyStock"
                  v-bind:disabled="quantity <= 0 || insufficientFunds">
                {{ insufficientFunds ? 'Insufficient Funds': 'Buy'}}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: ['stock'],
    data(){
      return {
        quantity: 0
      }
    },
    computed:{
      funds(){
        return this.$store.getters.funds;
      },
      insufficientFunds(){
        return this.quantity * this.stock.price > this.funds;
      }
    },
    methods:{
      buyStock() {
        const order = {
          stockId: this.stock.id,
          stockPrice: this.stock.price,
          quantity: parseInt(this.quantity)
        };
        console.log(order);
        this.$store.dispatch('buyStock', order);
        this.quantity = 0;
      }
    }
  }

</script>
<style scoped>
  .danger {
    border: 1px solid red;
  }

</style>
