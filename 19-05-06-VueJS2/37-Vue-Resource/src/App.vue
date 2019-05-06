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
                <hr>
                <button class="btn btn-primary" v-on:click="fetchData">Get Data</button>
                <ul class="list-group">
                  <li class="list-group-item"
                      v-for="u in users"
                      v-bind:key="u">
                      {{ u.username }} - {{ u.email }}
                  </li>
                </ul>
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
          },
          users: []
        }
      },
      methods: {
        submit(){
          console.log(this.user);
          // HTTP from vue-resource
          this.$http.post('', this.user)
            .then(response => {
              console.log('Response:');
              console.log(response);
            }, error => {
              console.log('Error:');
              console.log(error);
            });
        },
        fetchData(){
          this.$http.get('')
          .then(response => {
            return response.json();
          }, error => {
            console.log('Error:');
            console.log(error);
          }).then(data => {
            // Key-value pair to Array
            let resultArray = [];
            for (let key in data){
              resultArray.push(data[key]);
            }
            this.users = resultArray;
          });
        }
      }
    }
</script>

<style>
</style>
