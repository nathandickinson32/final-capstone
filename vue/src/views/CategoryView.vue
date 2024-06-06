<template>
  <div class='container'>
    <!-- <h1>TEST</h1> -->
    <!-- {{ recipes }} -->
    {{ library }}
    <div v-for='recipe in recipes' v-bind:key='recipe.id' class='recipeCard'>
      <img class='recipe unfavorite' src='/star_outline.png' v-show="this.$store.state.token != ''"><img class='recipe favorite' src='/star_full.png' v-show="this.$store.state.token != ''">
      <!-- <div class='recipe favorite'></div> -->
      <div class='recipe name'><h1 class='recipe-head-item'>{{ recipe.recipeName }}</h1></div>
      <div class='recipe description'>{{ recipe.description }}</div>
      <div><button class='btn'><router-link v-bind:to="{name: 'recipe', params: {id: recipe.id}}">View Details</router-link></button></div>
    </div>
  </div>
</template>

<script>
import RecipeService from '../services/RecipeService';

export default {
  props: {
    library: []
  },
  data() {
    return {
      recipes: []
    }
  },
  created() {
    RecipeService.getRecipesByCategoryId(this.$route.params.id).then(
      (response) => {
        if(response.status === 200) {
          this.recipes = response.data;
        }
      }
    );
  }
}
</script>

<style>

.container {
  display: grid;
  justify-content: center;
  margin-top: 20px;
}

.recipeCard {
  border: 1px black solid;
  border-radius: 25px;
  width: 75vw;
  height: 250px;
}

.container div {
  text-align: center;
  margin: 15px;
}

h1 {
  text-align: center;
}

.recipe-head {
  display: inline-block;
  height: auto;
}

img.unfavorite {
  /* display: none; */
  height: fit-content;
  width: 50px;
  border: none;
  float:right;
  margin: 0;
}

img.favorite {
  display: none;
  height: fit-content;
  width: 50px;
  border: none;
  float:right;
}

.btn {
  text-align: center;
  top:50%;
  background-color:pink;
  color: black;
  height: 20px;
  width: 90px;
  border: 1px solid;
  border-color: black;
  border-radius: 10px;
  margin-left: 10px;
  margin-top: auto;
  margin-bottom: auto;
}

</style>