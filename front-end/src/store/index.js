import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userdata: {
      firstname : "",
      lastname :"",
      userid:0,
      userbalance:0,
      email:"",
      token : ""
    }
  },
  mutations: {
    SET_USERDATA(state,user){
      state.userdata.firstname = user.firstName,
      state.userdata.lastname = user.lastName,
      state.userdata.userid = user.id,
      state.userdata.email = user.email,
      state.userdata.userbalance=user.balance
    },
    SET_BALANCE(state, balance){
      state.userdata.userbalance=balance
    },
    SET_TOKEN(state, token){
      state.userdata.token = token
    },
    SET_EMAIL(state, email){
      state.userdata.email = email
    },
    UPDATE_BALANCE(){
      fetch("http://localhost:9090/userById?id="+this.state.userdata.userid,{
            method: 'GET',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json',
            'Authorization': this.state.userdata.token
            }
      })
       .then(response => response.json())
        .then((response) => {
          console.log(response);
          this.commit("SET_BALANCE",response.balance);
        })
        .catch(function(error) {
            alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
          })
    },GET_USERINFO(){
      fetch("http://localhost:9090/userByEmail?email="+this.state.userdata.email,{
            method: 'GET',
            headers: { 
            'Accept': 'application/json', 
            'Content-Type': 'application/json' ,
            'Authorization': this.state.userdata.token
            }
      })
      .then(response => response.json())
      .then((response) => {
        this.commit("SET_USERDATA",response);
        console.log(this.state.userdata);
      })
      .catch(function(error) {
          alert('Il y a eu un problème avec l\'opération fetch: ' + error.message);
        })
    }
  },
  actions: {
    },
  modules: {
  }
})
