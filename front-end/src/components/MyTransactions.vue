<template>
    <div>
        <b-container class="my-2">
            <b-row>
            <b-col cols="8">
                <p class="h4">Solde actuel : <b>{{$store.state.userdata.userbalance}}€</b></p>
            </b-col>
            <b-col>
                <b-button variant="primary" @click="addMoneyRedirect()" class="mx-5 w-50">Add Money</b-button>
            </b-col>
            </b-row>
        </b-container>
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
   },methods: {
     addMoneyRedirect(){
       this.$router.push('profile');
     }},
    mounted(){
       let url = "http://localhost:9090/getalltransactionsbyid?id="+this.$store.state.userdata.userid;
       fetch(url,{
            method: 'GET',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
             'Authorization': localStorage.getItem('token')
            }
      })
       .then(response => response.json())
        .then((response) => {
           this.transactions = response;
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          });
         this.$store.commit('UPDATE_BALANCE');
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