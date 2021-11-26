<template>
<div class="m-3">
    <Navbar />
    <div class="card" >
      <ContactCard v-for="friend in friends" :letter="friend.firstName.charAt(0)+friend.lastName.charAt(0)"  v-bind:key="friend.id" :firstname="friend.firstName" :lastname="friend.lastName" :email="friend.email"/>
    </div>
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import ContactCard from '../components/ContactCard.vue'

export default {
name: 'Contact', 
   components : {
     Navbar,
     ContactCard
   },
    data(){
     return{
      friends:[]
     }
   }, 
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
.card{
  display: flex;
  justify-content: space-around;
  flex-direction: row;
}
</style>