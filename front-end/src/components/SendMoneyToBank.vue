<template>
    <b-form @submit="onSubmit" inline class="w-75 mx-auto">
        <b-form-select v-model="form.accountNumber" required class="mx-2">
          <option value=null>Please select an account</option>
          <option v-for="account in bankaccounts" :value="account.accountNumber" :key="account.accountNumber">{{account.accountNumber}}</option>
          </b-form-select>
          <b-form-input v-model="form.amount" placeholder="amount" type="number" class="mx-2"></b-form-input>
        <b-button variant="success" type="submit" class="mx-2 w-25">Send money</b-button>
    </b-form>
</template>

<script>
export default {
    name: 'SendMoneyToBank',
    data(){
     return{
      form:{
        accountNumber : "",
        amount:"",
        userId : this.$store.state.userdata.userid
      },
      bankaccounts:[]
     }
   },methods: {
      onSubmit(event) {
        event.preventDefault()
        fetch("http://localhost:9090/sendMoneyToAccount",{
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
       let url = "http://localhost:9090/getAllBankAccountsByUserId?userId="+this.$store.state.userdata.userid;
       fetch(url,{
            method: 'GET',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            }
      })
       .then(response => response.json())
        .then((response) => {
           this.bankaccounts = response;
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          });
      }

}
</script>
<style>

</style>