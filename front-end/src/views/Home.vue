<template>
  <div>
      <Navbar />
      <b-container>
        <MyTransactions />
      </b-container>
  </div>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import MyTransactions from '../components/MyTransactions.vue'

export default {
name: 'Home', 
   components : {
     Navbar,
     MyTransactions
   }/*,methods:{
     updateBalance(){
       this.$store.dispach('updateBalance');
     }
   }*/,mounted(){
     this.$store.commit('UPDATE_BALANCE');
   }
}
</script>

<style>

</style>