<template>
  <div>
    <b-form @submit="onSubmit">
      <b-form-group id="input-group-1" label="Email address:" label-for="input-1" description="We'll never share your email with anyone else.">
        <b-form-input id="input-1" v-model="email" type="email" placeholder="Enter email" required></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Your password:" label-for="input-2">
        <b-form-input id="input-2" v-model="password" type="password" placeholder="Enter password" required></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Login</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
    name: 'LoginForm',
    data() {
      return {
          email: '',
          password:''
      }
    },
    methods: {
      onSubmit(event) {
        event.preventDefault()
        const data = {
            email : this.email,
            password : this.password
        }
        console.log(data);
        fetch("http://localhost:9090/login",{
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