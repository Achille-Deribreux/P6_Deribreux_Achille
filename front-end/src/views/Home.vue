<template>
  <div>
      <Navbar />
      <h1>HOME PAGE</h1>
      <h2> HELLO : {{$store.state.userdata}}</h2>
      <MyTransactions />
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