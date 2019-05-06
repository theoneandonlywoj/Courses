<template>
  <div id="signup">
    <div class="signup-form">
      <form @submit.prevent="onSubmit">
        <div class="input" v-bind:class="{'invalid': $v.email.$error}">
          <label for="email">Mail</label>
          <input
                  type="email"
                  id="email"
                  v-model="email"
                  v-on:blur="$v.email.$touch()"
                  >
          <!-- Using v-on:input will check the validation on every input.
               Using v-on:blur will check the validation when the user leaves the input field.
          -->
          <p v-if="!$v.email.mail">Please provide a valid email address.</p>
          <p v-if="!$v.email.req">Please provide minimal required information.</p>
          <!--
          <div class="">
            {{ $v }}
          </div>
          -->
        </div>
        <div class="input" v-bind:class="{'invalid': $v.age.$error}">
          <label for="age">Your Age</label>
          <input
                  type="number"
                  id="age"
                  v-on:blur="$v.age.$touch()"
                  v-model.number="age">
          <p v-if="!$v.age.minVal">You have to be at least {{ $v.age.$params.minVal.min }} years old.</p>
        </div>
        <div class="input" v-bind:class="{'invalid': $v.password.$error}">
          <label for="password">Password</label>
          <input
                  type="password"
                  id="password"
                  v-on:blur="$v.password.$touch()"
                  v-model="password">
        </div>
        <div class="input" v-bind:class="{'invalid': $v.confirmPassword.$error}">
          <label for="confirm-password">Confirm Password</label>
          <input
                  type="password"
                  id="confirm-password"
                  v-on:blur="$v.confirmPassword.$touch()"
                  v-model="confirmPassword">
          <p>{{ $v.confirmPassword }}</p>
        </div>
        <div class="input">
          <label for="country">Country</label>
          <select id="country" v-model="country">
            <option value="usa">USA</option>
            <option value="india">India</option>
            <option value="uk">UK</option>
            <option value="germany">Germany</option>
          </select>
        </div>
        <div class="hobbies">
          <h3>Add some Hobbies</h3>
          <button @click="onAddHobby" type="button">Add Hobby</button>
          <div class="hobby-list">
            <div
                    class="input"
                    v-for="(hobbyInput, index) in hobbyInputs"
                    v-bind:class="{'invalid': $v.hobbyInputs.$each[index].value.$error}"
                    :key="hobbyInput.id">
              <label :for="hobbyInput.id">Hobby #{{ index }}</label>
              <input
                      type="text"
                      :id="hobbyInput.id"
                      v-on:blur="$v.hobbyInputs.$each[index].value.$touch()"
                      v-model="hobbyInput.value">
              <button @click="onDeleteHobby(hobbyInput.id)" type="button">X</button>
            </div>
          </div>
        </div>
        <div class="input inline" v-bind:class="{'invalid': $v.terms.$invalid}">
          <!-- Required unless condition -->
          <input
                 type="checkbox"
                 id="terms"
                 v-model="terms"
                 v-on:change="$v.terms.$touch()">
          <label for="terms">Accept Terms of Use</label>
          <p>{{ country }}</p>
          <p>{{ $v.terms }}</p>
          <p>{{ terms }}</p>
        </div>
        <div class="submit">
          <button type="submit" :disabled="$v.$invalid">Submit</button>
          <p>Invalid: {{ $v.$invalid }}</p>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  import { required, email, numeric, minValue, minLength, sameAs, requiredUnless } from 'vuelidate/lib/validators'
  export default {
    data () {
      return {
        email: '',
        age: null,
        password: '',
        confirmPassword: '',
        country: 'usa',
        hobbyInputs: [],
        terms: false
      }
    },
    validations: {
      // Name of the validation must be the same as the name of the property
      // (in this case 'email')
      // Validator 'required' check if the property is not empty
      email: {
        req: required,
        mail: email,
        unique: value => {
          // Custom validator must return true or false
          // true - the input is valid
          // false - the input is invalid
          if (value === '') return true
          return new Promise((resolve, reject) => {
            setTimeout(() => {
              console.log('1s expired!')
            }, 1000)
            resolve(value !== 'test@test.com')
          })
        }
      },
      age: {
        req: required,
        num: numeric,
        minVal: minValue(18)
      },
      password: {
        req: required,
        minLen: minLength(6)
      },
      confirmPassword: {
        // Passing a property name
        //sameAs: sameAs('password')
        // Using an arrow function and passing the Vue instance
        sameAs: sameAs(vm => {
          return vm.password
        })
      },
      terms: {
        required: requiredUnless(vm => {
          return vm.terms === true
        })
      },
      hobbyInputs: {
        $each: {
          value: {
            req: required,
            minLen: minLength(6)
          }
        }
      }
    },
    methods: {
      onAddHobby () {
        const newHobby = {
          id: Math.random() * Math.random() * 1000,
          value: ''
        }
        this.hobbyInputs.push(newHobby)
      },
      onDeleteHobby (id) {
        this.hobbyInputs = this.hobbyInputs.filter(hobby => hobby.id !== id)
      },
      onSubmit () {
        const formData = {
          email: this.email,
          age: this.age,
          password: this.password,
          confirmPassword: this.confirmPassword,
          country: this.country,
          hobbies: this.hobbyInputs.map(hobby => hobby.value),
          terms: this.terms
        }
        this.$store.dispatch('signup', formData)
      }
    }
  }
</script>

<style scoped>
  .signup-form {
    width: 400px;
    margin: 30px auto;
    border: 1px solid #eee;
    padding: 20px;
    box-shadow: 0 2px 3px #ccc;
  }

  .input {
    margin: 10px auto;
  }

  .input label {
    display: block;
    color: #4e4e4e;
    margin-bottom: 6px;
  }

  .input.inline label {
    display: inline;
  }

  .input input {
    font: inherit;
    width: 100%;
    padding: 6px 12px;
    box-sizing: border-box;
    border: 1px solid #ccc;
  }

  .input.inline input {
    width: auto;
  }

  .input input:focus {
    outline: none;
    border: 1px solid #521751;
    background-color: #eee;
  }

  .input.invalid label {
    color: red;
  }

  .input.invalid input {
    border: 1px solid red;
    background-color: #ffc9aa;
  }

  .input select {
    border: 1px solid #ccc;
    font: inherit;
  }

  .hobbies button {
    border: 1px solid #521751;
    background: #521751;
    color: white;
    padding: 6px;
    font: inherit;
    cursor: pointer;
  }

  .hobbies button:hover,
  .hobbies button:active {
    background-color: #8d4288;
  }

  .hobbies input {
    width: 90%;
  }

  .submit button {
    border: 1px solid #521751;
    color: #521751;
    padding: 10px 20px;
    font: inherit;
    cursor: pointer;
  }

  .submit button:hover,
  .submit button:active {
    background-color: #521751;
    color: white;
  }

  .submit button[disabled],
  .submit button[disabled]:hover,
  .submit button[disabled]:active {
    border: 1px solid #ccc;
    background-color: transparent;
    color: #ccc;
    cursor: not-allowed;
  }
</style>
