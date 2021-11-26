<template>
    <b-card border-variant="success" align="center" class="w-50 m-3" >
        <b-row align-v="center">
            <b-avatar variant="success">{{letter}}</b-avatar>
            <b-card-text class="mx-3">{{firstname+" "+lastname}} <br>{{email}}</b-card-text>
        </b-row>
    </b-card>
</template>

<script>
export default {
name: 'ContactCard',
props: ['letter', 'firstname','lastname','email'] 
};

</script>

<style>

</style>