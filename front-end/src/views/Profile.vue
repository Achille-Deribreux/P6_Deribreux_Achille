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
    <MyBankAccounts />
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import MyBankAccounts from '../components/MyBankAccounts.vue'

export default {
name: 'Profile', 
   components : {
     Navbar,
     MyBankAccounts
   }
}
</script>

<style>

</style>