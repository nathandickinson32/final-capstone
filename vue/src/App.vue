<template>
  <div id="capstone-app">
    <header>  </header>
    <div id="nav">
      
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;-->
      <router-link v-bind:to="{ name: 'login'}" v-if="this.$store.state.token == ''">Login</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>
      |
      <router-link v-bind:to="{ name: 'register' }">Register</router-link>
      

    </div>
    <Navigation />
    <router-view />
  </div>
</template>

<script>
import Navigation from './components/navigation.vue';

export default {
    components: { Navigation }
    

};

</script>

<style>

body {
  background-color: #8A9A5B;
}
#nav {
 text-align: left;
}


</style>