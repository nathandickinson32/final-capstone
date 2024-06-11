<template>
  <div>
  <!--<div class="media">
    
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>-->
    <header>  </header>
    <div id="nav">
      
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;-->
      <router-link id="login-btn" v-bind:to="{ name: 'login'}" v-if="this.$store.state.token == ''">Login</router-link>
      <router-link id="logout-btn" v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>
      |
      <router-link id="register-btn" v-bind:to="{ name: 'register' }">Register</router-link>
      

    </div>
    <Navigation />
    <router-view v-bind:key="$route.fullPath"/> <!--This refreshed page so dropdown works appropriately-->
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
#login-btn {
  border: 1px solid black;
  background-color: pink;
  border-radius: 10px;
  padding-left: 5px;
  padding-right: 5px;
  padding-bottom: 2px;
}
#logout-btn {
  border: 1px solid black;
  background-color: pink;
  border-radius: 10px;
  padding-left: 5px;
  padding-right: 5px;
  padding-bottom: 2px;
}
#register-btn {
  border: 1px solid black;
  background-color: pink;
  border-radius: 10px;
  padding-left: 5px;
  padding-right: 5px;
  padding-bottom: 2px;
}

/* Extra small devices (phones, 600px and down) */
@media only screen and (max-width: 600px) {
  .media {
    width: 100px;
  }
}

/* Small devices (portrait tablets and large phones, 600px and up) */
@media only screen and (min-width: 600px) {
  .media {
    width: 200px;
  }
}

/* Medium devices (landscape tablets, 768px and up) */
@media only screen and (min-width: 768px) {
  .media {
    width: 300px;
  }
}

/* Large devices (laptops/desktops, 992px and up) */
@media only screen and (min-width: 992px) {
  .media{
    width: 400px;
  }
}

/* Extra large devices (large laptops and desktops, 1200px and up) */
@media only screen and (min-width: 1200px) {
  .media {
    width: 100px;
  }
}


</style>