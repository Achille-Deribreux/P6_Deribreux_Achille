<template>
  <div class="m-3">
    <Navbar />
    <b-toaster name="example-toast"></b-toaster>
    <b-row align-v="center">
      <b-col align="center">
        <span class="h5">Send Money</span>
      </b-col>
      <b-col align="center">
        <b-button variant="primary" @click="addConnectionRedirect()" class="mx-2 w-50">Add Connection</b-button>
      </b-col>
    </b-row>
    <div class="bg-light mb-4 mx-5 p-5">
      <b-form @submit="onSubmit" id="form-flex" class="w-75 mx-auto">
        <b-form-select v-model="form.receiverId" required class="mx-2">
          <option value=null>Please select an option</option>
          <option v-for="friend in friends" :value="friend.id" :key="friend.friendid">{{friend.firstName+" "+friend.lastName}}</option>
          </b-form-select>
          <b-form-input v-model="form.amount" placeholder="amount" type="number" step="0.01" class="mx-2"></b-form-input>
           <b-form-input v-model="form.description" placeholder="description" type="text" class="mx-2"></b-form-input>
        <b-button variant="success" type="submit" class="mx-2">Pay</b-button>
      </b-form>
    </div>
    <div>
      <MyTransactions />
    </div>
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import MyTransactions from '../components/MyTransactions.vue'

export default {
name: 'Transfer', 
   components : {
     Navbar,
     MyTransactions
   },
   data(){
     return{
      form:{
        amount : 0,
        receiverId : null,
        senderId : this.$store.state.userdata.userid,
        description : ""
      },
      friends:[]
     }
   },methods: {
     addConnectionRedirect(){
       this.$router.push('addconnection');
     },
      onSubmit(event) {
        event.preventDefault()
        fetch("http://localhost:9090/addtransaction",{
            method: 'POST',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
             'Authorization': localStorage.getItem('token')
            },
            body: JSON.stringify(this.form)
      })
       .then(response => response.json())
        .then((response) => {
          if(response.httpStatus == 'UNAUTHORIZED'){
            this.$bvToast.toast('Not enough money', {
              title: "Error",
              variant: "danger",
              solid: true
        });
          }
          else{
            this.$bvToast.toast('Money has been send', {
                title: "Success",
                variant: "success",
                solid: true
            });
          }
          this.$store.commit('UPDATE_BALANCE');
            console.log(response)
        })
        .catch(function(error) {
          console.log('error', error)
          });
      }},
     mounted(){
       this.$store.commit('UPDATE_BALANCE');
       let url = "http://localhost:9090/getconnectionusersbyid?id="+this.$store.state.userdata.userid;
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
           this.friends = response;
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          });
      }
   }
</script>

<style>
#form-flex{
  display: flex;
  flex-direction: row;
  justify-content: center;
}

</style>