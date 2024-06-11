<template>

  <div id="login">
    <form v-on:submit.prevent="login">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button id="submit" type="submit">Sign in</button>
      <p>
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import RecipeService from '../services/RecipeService';


export default {
  
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    // importUserData() {
    //   // RecipeService.getRecipeLibraryByUser().then(
    //   //   (response) => {
    //   //     if (response.status === 200) {
    //   //       console.log("success");
    //   //       this.$store.commit("SET_USER_LIBRARY", response.data);
    //   //       console.log("complete success");
    //   //     }
    //   //   }
    //   // );
    // }
  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
  
}
label {
  margin-right: 0.5rem;
}

#login {
  text-align: center;
}

#username {
  background-color: pink;;
}
#password {
  background-color: pink;
}
#submit {
  background-color: pink;
  border-radius: 10px;
  height: 30px;
  width: 60px;
}

</style>