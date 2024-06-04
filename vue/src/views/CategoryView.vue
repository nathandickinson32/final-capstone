<template>
  <div class='container'>
    <h1>TEST</h1>
    {{ recipes }}
    <div v-for='recipe in recipes' v-bind:key='recipe.id' class='recipeCard'>
      
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
}

h1 {
  text-align: center;
}

</style>