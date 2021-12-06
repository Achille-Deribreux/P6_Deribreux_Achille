<template>
<div>
    <Navbar />
    <b-container align="center">
        <p class="h5 my-4">Supprimer un compte : </p>
        <b-form @submit="onSubmit" class="w-50 mx-auto">
            <b-form-select v-model="form.accountNumber" required class="mx-2">
                <option value=null>Please select an account</option>
                <option v-for="account in bankaccounts" :value="account.accountNumber" :key="account.accountNumber">{{account.accountNumber}}</option>
            </b-form-select>
            <b-button variant="danger" type="submit" class="m-4 w-25">Delete</b-button>
        </b-form>
    </b-container>
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue' 
export default {
 name: 'AddMoneyFromBank',
    components:{
        Navbar
    },
    data(){
     return{
      form:{
        accountNumber : "",
        userId : this.$store.state.userdata.userid
      },
      bankaccounts:[]
     }
   },methods: {
      onSubmit(event) {
        event.preventDefault()
        fetch("http://localhost:9090/deleteBankAccount",{
            method: 'DELETE',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
             'Authorization': localStorage.getItem('token')
            },
            body: JSON.stringify(this.form)
      })
        .then(() => {
            this.$bvToast.toast('Account has been deleted', {
              title: "Success",
              variant: "success",
              solid: true
        });
            this.$router.push('Profile');
        })
        .catch(function() {
           this.$bvToast.toast('Impossible to delete account', {
              title: "Error",
              variant: "danger",
              solid: true
        });
          });
      }},
      mounted(){
       let url = "http://localhost:9090/getAllBankAccountsByUserId?userId="+this.$store.state.userdata.userid;
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