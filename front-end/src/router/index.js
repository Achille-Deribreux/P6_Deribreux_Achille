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
import DeleteBankAccount from '../views/DeleteBankAccount'
import store from '../store'

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
    component: Home,
    meta: { requiresAuth: true }
  },
  {
    path: '/contact',
    name: 'Contact',
    component: Contact,
    meta: { requiresAuth: true }
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
    meta: { requiresAuth: true }
  },
  {
    path: '/transfer',
    name: 'Transfer',
    component: Transfer,
    meta: { requiresAuth: true }
  },
  {
    path: '/addconnection',
    name: 'AddConnection',
    component: AddConnection,
    meta: { requiresAuth: true }
  },
  {
    path: '/addbankaccount',
    name: 'AddBankAccount',
    component: AddBankAccount,
    meta: { requiresAuth: true }
  },
  {
    path: '/deletebankaccount',
    name: 'DeleteBankAccount',
    component: DeleteBankAccount,
    meta: { requiresAuth: true }
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (store.state.userdata.userid == 0) {
      next({
        path: '/'
      })
    } else {
      next()
    }
  } else {
    next() // make sure to always call next()!
  }
})

export default router
