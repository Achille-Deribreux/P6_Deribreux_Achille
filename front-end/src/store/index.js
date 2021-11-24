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
    }
  },
  actions: {
  },
  modules: {
  }
})
