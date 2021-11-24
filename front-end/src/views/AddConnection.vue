<template>
    <div>
        <Navbar />
        <b-form @submit="onSubmit">
            <b-row class="w-50 mx-auto text-center m-3">
                <b-input-group class="w-75 mx-auto">
                    <b-input-group-prepend is-text>
                        <font-awesome-icon icon="fa-solid fa-envelope-open" />
                    </b-input-group-prepend>
                    <b-form-input id="input-1" v-model="email" type="email" placeholder="Enter email" required></b-form-input>
                </b-input-group>
                <b-button type="submit" class="mx-auto text-center w-25" variant="primary">Add</b-button>
            </b-row>
        </b-form>
    </div>
</template>

<script>
import Navbar from '../components/Navbar.vue'
export default {
name: 'AddConnection', 
    data() {
      return {
           email: ''
      };
    },
   components : {
     Navbar
   },
   methods: {
      onSubmit(event) {
        event.preventDefault()
        const data = {
            friendemail : this.email,
            userid : this.$store.state.userdata.userid
        };
        fetch("http://localhost:9090/addconnection",{
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