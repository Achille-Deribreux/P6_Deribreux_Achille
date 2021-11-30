<template>
  <div>
      <Navbar />
      <b-container align="center">
        <p class="h5 my-4">Ajouter un compte : </p>
        <AddBankAccount />
      </b-container>
  </div>
</template>

<script>
import AddBankAccount from '../components/AddBankAccount.vue'
import Navbar from '../components/Navbar.vue' 
export default {
    name: 'Home', 
   components : {
     AddBankAccount,
     Navbar
   }
}
</script>

<style>

</style>