<template>
  <div class="m-3">
    <Navbar />
    <h1>
        TRANSFER PAGE
    </h1>
    <b-form @submit="onSubmit" inline>
      <b-form-select v-model="form.receiverId" required>
        <option v-for="friend in friends" :value="friend.id" :key="friend.friendid">{{friend.firstName+" "+friend.lastName}}</option>
        </b-form-select>
        <b-form-input v-model="form.amount" placeholder="amount" type="number"></b-form-input>
      <b-button variant="primary" type="submit">Send</b-button>
    </b-form>
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue'

export default {
name: 'Transfer', 
   components : {
     Navbar
   },
   data(){
     return{
      form:{
        amount : 0,
        receiverId : null,
        userId : this.$store.state.userdata.userid,
        description : "hello"
      },
      friends:[]
     }
   },methods: {
      onSubmit(event) {
        console.log(this.form);
        event.preventDefault()
        fetch("http://localhost:9090/addtransaction",{
            method: 'POST',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            },
            body: JSON.stringify(this.form)
      })
       .then(response => response.json())
        .then((response) => {
            console.log(response)
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          });
      }},
     mounted(){
       let url = "http://localhost:9090/getconnectionusersbyid?id="+this.$store.state.userdata.userid;
       fetch(url,{
            method: 'GET',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            }
      })
       .then(response => response.json())
        .then((response) => {
           this.friends = response;
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          });
      }
   }
</script>

<style>

</style>