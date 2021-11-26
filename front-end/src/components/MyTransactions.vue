<template>
<h1>Transac</h1>
</template>

<script>
export default {
    name: 'MyTransactions', 
    mounted(){
       let url = "http://localhost:9090/getalltransactionsbyid?id="+this.$store.state.userdata.userid;
       fetch(url,{
            method: 'GET',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            }
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
</script>

<style>

</style>