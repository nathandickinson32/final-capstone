<template>
  <div class='container'>
    <!-- <h1>TEST</h1> -->
    <!-- {{ recipes }} -->
    <div v-for='recipe in recipes' v-bind:key='recipe.id' class='recipeCard'>
      <div><h1>{{ recipe.recipeName }}</h1></div>
      <div>{{ recipe.description }}</div>
      <div><button><router-link v-bind:to="{name: 'recipe', params: {id: recipe.id}}">View Details</router-link></button></div>
    </div>
  </div>
</template>

<script>
import RecipeService from '../services/RecipeService';

export default {
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
    )
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

</style>