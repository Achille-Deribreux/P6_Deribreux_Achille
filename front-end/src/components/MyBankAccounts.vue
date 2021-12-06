<template>
  <div>
      <h3 align="center">My Accounts : </h3>
      <b-table striped hover :items="accounts" :fields="fields"></b-table>
  </div>
</template>

<script>
export default {
    name: 'MyBankAccounts', 
    data(){
     return{
    fields: ['accountNumber', 'bank'],
    accounts:[]
     }
   },
    mounted(){
       let url = "http://localhost:9090/getAllBankAccountsByUserId?userId="+this.$store.state.userdata.userid;
       fetch(url,{
            method: 'GET',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
             'Authorization': this.state.userdata.token
            }
      })
       .then(response => response.json())
        .then((response) => {
           this.accounts = response;
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          });
      }

}
</script>

<style>

</style>