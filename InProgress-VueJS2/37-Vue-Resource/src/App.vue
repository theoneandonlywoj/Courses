<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h1>Http</h1>
                <div class="form-group">
                  <label>Username</label>
                  <input class="form-control" type="text" v-model="user.username">
                </div>
                <div class="form-group">
                  <label>Mail</label>
                  <input class="form-control" type="text" v-model="user.email">
                </div>
                <button class="btn btn-primary" v-on:click="submit">Submit</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
      data(){
        return {
          user:{
            username: '',
            email: ''
          }
        }
      },
      methods: {
        submit(){
          console.log(this.user);
          // HTTP from vue-resource
          this.$http.post('https://myvueproject-8a8df.firebaseio.com/myDataPackage.json', this.user)
            .then(response => {
              console.log('Response:');
              console.log(response);
            }, error => {
              console.log('Error:');
              console.log(error);
            });
        }
      }
    }
</script>

<style>
</style>
