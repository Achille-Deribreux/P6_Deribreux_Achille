<template>
    <div id="navDiv">
        <PayMyBuddy />
        <nav>
            <span class="mx-2">
                <router-link to="/home">Home</router-link>
            </span>

            <span class="mx-2">
                <router-link to="/transfer">Transfer</router-link>
            </span>

            <span class="mx-2">
                <router-link to="/profile">Profile</router-link>
            </span>

            <span class="mx-2">
                <router-link to="/contact">Contact</router-link>
            </span>

            <span class="mx-2">
                Log off
            </span>
        </nav>
    </div>
</template>

<script>
import PayMyBuddy from '../components/PayMyBuddy.vue'

export default {
    name: 'navigation',
    components : {
     PayMyBuddy
   }
}
</script>

<style>
#navDiv{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom :3rem;
}
</style>