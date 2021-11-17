<template>
  <div>
    <b-form @submit="onSubmit">
      <b-form-group id="input-group-1" label="Email address:" label-for="input-1" description="We'll never share your email with anyone else.">
        <b-form-input id="input-1" v-model="email" type="email" placeholder="Enter email" required></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Your firstname:" label-for="input-2">
        <b-form-input id="input-2" v-model="firstname" placeholder="Enter firstname" required></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Your lastname:" label-for="input-3">
        <b-form-input id="input-3" v-model="lastname" placeholder="Enter lastname" required></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-4" label="Your password:" label-for="input-4">
        <b-form-input id="input-4" v-model="password" type="password" placeholder="Enter password" required></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Sign In</b-button>
    </b-form>
  </div>
</template>

<script>
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
    methods: {
      onSubmit(event) {
        event.preventDefault()
        const data = {
            firstName : this.firstname,
            lastName : this.lastname,
            email : this.email,
            password : this.password
        }
        console.log(data);
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
            console.log(response);
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          });
      }
  }
}
</script>

<style>

</style>