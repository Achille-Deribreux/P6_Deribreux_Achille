<template>

  <b-container class="w-50 border rounded mt-5 p-3 border-dark">

    <div class="w-50 text-center mx-auto">
      <PayMyBuddy />
    </div>
    <b-form @submit="onSubmit" class="text-center">

      <b-row class="w-50 mx-auto text-center m-3">
            <b-input-group class="w-75 mx-auto">
               <b-input-group-prepend is-text>
                  <font-awesome-icon icon="fa-solid fa-envelope-open" />
                </b-input-group-prepend>
              <b-form-input id="input-1" v-model="username" type="email" placeholder="Enter email" required></b-form-input>
            </b-input-group>
      </b-row>

      <b-row class="w-50 mx-auto text-center m-3"> 
              <b-input-group class="w-75 mx-auto">
                <b-input-group-prepend is-text>
                  <font-awesome-icon icon="fa-solid fa-key" />
                </b-input-group-prepend>
                <b-form-input id="input-2" v-model="password" type="password" placeholder="Enter password" required></b-form-input>
              </b-input-group>
              <p class="w-75 mx-auto text-primary text-decoration-underline" @click="signInRedirect()"><u>Ou inscrivez-vous ! </u></p>
      </b-row>

      <b-form-row class="mx-auto center">
        <b-col class="mx-auto text-center my-3">
          <b-button class="mx-auto text-center w-25" type="submit" variant="primary">Login</b-button>
        </b-col>
      </b-form-row>
    </b-form>
  </b-container>
</template>

<script>
 import PayMyBuddy from '../components/PayMyBuddy.vue'
 
export default {
    name: 'LoginForm',
    data() {
      return {
          username: '',
          password:''
      }
    },
    components : {
     PayMyBuddy
   },
    methods: {
      signInRedirect(){
        this.$router.push('signin')
      },
      onSubmit(event) {
        event.preventDefault()
        const data = {
            username : this.username,
            password : this.password
        }
        fetch("http://localhost:9090/login",{
            method: 'POST',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            },
            body: JSON.stringify(data)
      })
       //.then(response => response.json())
        .then((response) => {
          this.$store.commit("SET_EMAIL",this.username);
          this.$store.commit("SET_TOKEN",response.headers.get("Authorization"));
          localStorage.setItem('token', response.headers.get("Authorization"));
          this.$store.commit("GET_USERINFO");
          setTimeout(() => this.$router.push('home'), 1000)
        })
        .catch(function() {
          console.log("catch")
          });
      }
  }
}
</script>

<style>

</style>