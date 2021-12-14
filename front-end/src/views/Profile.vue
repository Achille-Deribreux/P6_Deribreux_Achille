<template>
  <div class="m-3">
    <Navbar />
    <b-container>
      <b-row class="bg-success m-3" align-v="center">
        <b-col>
          <b-avatar class="m-3" variant="light">{{this.$store.state.userdata.firstname.charAt(0)+this.$store.state.userdata.lastname.charAt(0)}}</b-avatar>
        </b-col>
        <b-col cols="8">
          <p class="m-3 text-white h1">{{this.$store.state.userdata.firstname+" "+this.$store.state.userdata.lastname}}</p>
        </b-col>
      </b-row>
    </b-container>

    <b-row>
      <b-col align="center">
        <b-button variant="danger" @click="deleteBankAccountRedirect()" class="mx-2 w-50">Delete bankaccount</b-button>
      </b-col>

      <b-col align="center">
        <b-button variant="primary" @click="addBankAccountRedirect()" class="mx-2 w-50">Add bankaccount</b-button>
      </b-col>
    </b-row>

    
    <b-container align="center" class="py-3">
      <span class="h5">Get Money from bank</span>
      <div class="bg-light mb-4 mx-5 p-4">
         <AddMoneyFromBank />
      </div>
    </b-container>

    
    <b-container align="center" class="py-3">
      <span class="h5">Send Money To bank</span>
      <div class="bg-light mb-4 mx-5 p-4">
         <SendMoneyToBank />
      </div>
    </b-container>

    <b-container>
      <MyBankAccounts />
    </b-container>
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import MyBankAccounts from '../components/MyBankAccounts.vue'
import AddMoneyFromBank from '../components/AddMoneyFromBank.vue'
import SendMoneyToBank from '../components/SendMoneyToBank.vue'


export default {
name: 'Profile', 
   components : {
     Navbar,
     MyBankAccounts,
     AddMoneyFromBank,
     SendMoneyToBank
   },
   methods:{
     deleteBankAccountRedirect(){
      this.$router.push('DeleteBankAccount');
     },
     addBankAccountRedirect(){
      this.$router.push('AddBankAccount');
     },
     mounted(){
       this.$store.commit('UPDATE_BALANCE');
     }
   }
}
</script>

<style>

</style>