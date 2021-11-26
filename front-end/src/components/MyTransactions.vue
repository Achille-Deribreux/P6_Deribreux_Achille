<template>
    <div>
        <b-table striped hover :items="transactions" :fields="fields"></b-table>
    </div>
</template>

<script>
export default {
    name: 'MyTransactions', 
    data(){
     return{
         fields: ['connection', 'amount', 'description'],
      transactions:[]
     }
   },
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
           this.transactions = response;
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          });
      }
}
</script>

<style>
thead{
    background-color: rgb(84, 186, 96);
}
th{
    color:white;
}

</style>