<template>
  <div>
      <Navbar />
      <h1>HOME PAGE</h1>
      <h2> HELLO : {{$store.state.userdata}}</h2>
  </div>
</template>

<script>
import Navbar from '../components/Navbar.vue'

export default {
name: 'Home', 
   components : {
     Navbar
   }
}
</script>

<style>

</style>