import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Signin from '../views/Signin.vue'
import Home from '../views/Home.vue'
import Contact from '../views/Contact.vue'
import Profile from '../views/Profile.vue'
import Transfer from '../views/Transfer.vue'
import AddConnection from '../views/AddConnection.vue'
import AddBankAccount from '../views/AddBankAccount'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/signin',
    name: 'Signin',
    component : Signin
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/contact',
    name: 'Contact',
    component: Contact
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/transfer',
    name: 'Transfer',
    component: Transfer
  },
  {
    path: '/addconnection',
    name: 'AddConnection',
    component: AddConnection
  },
  {
    path: '/addbankaccount',
    name: 'AddBankAccount',
    component: AddBankAccount
  }
]

const router = new VueRouter({
  routes
})

export default router
