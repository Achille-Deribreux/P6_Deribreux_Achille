<template>
    <div>
        <Navbar />

        <b-form @submit="onSubmit" align="center">
            <p class="h5 my-4">Add connection : </p>
            <b-row class="w-50 mx-auto text-center m-3">
                <b-form-select v-model="email" required class="mx-2">
                    <option value=null>Please select an option</option>
                    <option v-for="p in people" :value="p.email" :key="p.id">{{p.email}}</option>
                </b-form-select>
                <b-button type="submit" class="mx-auto my-4 text-center w-25" variant="primary">Add</b-button>
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
           email: '',
           people:[]
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
        .then(() => {
            this.$bvToast.toast('Connection added', {
              title: "Success",
              variant: "success",
              solid: true
        });
        })
        .catch(function() {
             this.$bvToast.toast('Impossible to add connection', {
              title: "Error",
              variant: "danger",
              solid: true
        });
          });
      }
    },
     mounted(){
       let url = "http://localhost:9090/getAllNonConnectionUsers?userId="+this.$store.state.userdata.userid;
       fetch(url,{
            method: 'GET',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            }
      })
       .then(response => response.json())
        .then((response) => {
            console.log(response)
           this.people = response;
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          });
      }
}
</script>

<style>

</style>