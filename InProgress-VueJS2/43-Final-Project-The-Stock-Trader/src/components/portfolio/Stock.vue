<template>
  <div class="col-sm-6 col-md-4">
    <div class="panel panel-info">
      <div class="panel-heading">
        <h3 class="panel-title">
          {{ stock.name }}
          <small>(Price: {{ stock.price }} | Quantity: {{ stock.quantity }})</small>
        </h3>
      </div>
      <div class="panel-body">
        <div class="pull-left">
          <input type="number"
                 class="form-control"
                 v-model="quantity"
                 v-bind:class="{'danger': insufficientQuantity}">
        </div>
        <div class="pull-right">
          <button class="btn btn-success"
                  v-on:click="placeSellOrder"
                  v-bind:disabled="quantity <= 0 || insufficientQuantity">
                  {{ insufficientQuantity ? 'Not enough stock': 'Sell'}}
                </button>
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
    computed: {
      insufficientQuantity(){
        return this.quantity > this.stock.quantity;
      }
    },
    methods:{
      placeSellOrder() {
        const order = {
          stockId: this.stock.id,
          stockPrice: this.stock.price,
          quantity: parseInt(this.quantity)
        };
        console.log(order);
        this.$store.dispatch('sellStock', order);
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
