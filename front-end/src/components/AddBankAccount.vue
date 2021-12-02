<template>
    <div>
        <b-form @submit="onSubmit" class="w-75 mx-auto">
            <b-form-input v-model="form.bank" placeholder="Bank Name" type="text" class="m-2"></b-form-input>
            <b-form-input v-model="form.accountNumber" placeholder="Account Number" type="number" class="m-2"></b-form-input>
             <b-button variant="success" type="submit" class="m-4 w-25">Add</b-button>
        </b-form>
    </div>
</template>

<script>
export default {
    name: 'AddBankAccount',
     data(){
        return{
            form:{
                bank : "",
                userId : this.$store.state.userdata.userid,
                accountNumber : ""
            }
        }
    },
    methods: {
        onSubmit(event){
            console.log( JSON.stringify(this.form))
            event.preventDefault();
            fetch("http://localhost:9090/addBankAccount",{
            method: 'POST',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            },
            body: JSON.stringify(this.form)
      })
        .then(() => {
           this.$bvToast.toast('Bankaccount added', {
              title: "Success",
              variant: "success",
              solid: true
        });
        })
        .catch(function() {
           this.$bvToast.toast('Impossible to add bankaccount', {
              title: "Error",
              variant: "danger",
              solid: true
        });
          });
        }
    }

}
</script>

<style>

</style>