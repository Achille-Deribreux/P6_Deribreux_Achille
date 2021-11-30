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
   }
}
</script>

<style>

</style>