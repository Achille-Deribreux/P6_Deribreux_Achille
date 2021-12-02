<template>
  <b-container class="w-50 border rounded mt-5 p-3 border-dark">
    <div class="w-50 text-center mx-auto">
      <PayMyBuddy />
    </div>

    <b-form @submit="onSubmit">
      <b-row class="w-50 mx-auto text-center m-3">
        <b-input-group class="w-75 mx-auto">
          <b-input-group-prepend is-text>
            <font-awesome-icon icon="fa-solid fa-envelope-open" />
          </b-input-group-prepend>
          <b-form-input id="input-1" v-model="email" type="email" placeholder="Enter email" required></b-form-input>
        </b-input-group>
      </b-row>

      <b-row class="w-50 mx-auto text-center m-3">
        <b-input-group class="w-75 mx-auto">
          <b-input-group-prepend is-text>
              <font-awesome-icon icon="fa-solid fa-user" />
          </b-input-group-prepend>
          <b-form-input id="input-2" v-model="firstname" placeholder="Enter firstname" required></b-form-input>
        </b-input-group>
      </b-row>

      <b-row class="w-50 mx-auto text-center m-3">
        <b-input-group class="w-75 mx-auto">
          <b-input-group-prepend is-text>
              <font-awesome-icon icon="fa-solid fa-user" />
          </b-input-group-prepend>
          <b-form-input id="input-3" v-model="lastname" placeholder="Enter lastname" required></b-form-input>
        </b-input-group>
      </b-row>

      <b-row class="w-50 mx-auto text-center m-3">
        <b-input-group class="w-75 mx-auto">
          <b-input-group-prepend is-text>
              <font-awesome-icon icon="fa-solid fa-key" />
          </b-input-group-prepend>
          <b-form-input id="input-4" v-model="password" type="password" placeholder="Enter password" required></b-form-input>
        </b-input-group>
      </b-row>

      <b-form-row class="mx-auto center">
        <b-col class="mx-auto text-center my-3">
          <b-button type="submit" class="mx-auto text-center w-25" variant="primary">Sign In</b-button>
        </b-col>
      </b-form-row>
    </b-form>
  </b-container>
</template>

<script>
import PayMyBuddy from '../components/PayMyBuddy.vue'
export default {
    name: 'signinForm',
    data() {
      return {
          email: '',
          firstname: '',
          lastname:'',
          password:''
      }
    },
    components : {
     PayMyBuddy
   },
    methods: {
      onSubmit(event) {
        event.preventDefault()
        const data = {
            firstName : this.firstname,
            lastName : this.lastname,
            email : this.email,
            password : this.password
        }
        fetch("http://localhost:9090/signup",{
            method: 'POST',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            },
            body: JSON.stringify(data)
      })
       .then(response => response.json())
        .then((response) => {
          this.$bvToast.toast('Account created !', {
              title: "Success",
              variant: "success",
              solid: true
        });
            this.$store.commit("SET_USERDATA",response);
            this.$router.push('home');
        })
        .catch(function() {
           this.$bvToast.toast('Impossible to create an account', {
              title: "Error",
              variant: "danger",
              solid: true
        });
          });
      }
  }
}
</script>

<style>

</style>