<template>
  <div class="m-3">
    <Navbar />
    <h1>
        TRANSFER PAGE
    </h1>
    <button @click="load">click</button>
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue'

export default {
name: 'Transfer', 
   components : {
     Navbar
   },
   methods:{
     load(){
       fetch("http://localhost:9090/getconnectionsbyid?id=2",{
            method: 'GET',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            }
      })
       .then(response => response.json())
        .then((response) => {
          console.log("response");
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