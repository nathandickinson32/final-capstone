<template>
  <div>
    {{recipe}}
    <p>picture ?</p>
    <h1>{{recipe.recipeName}}</h1>
    <div>{{recipe.description}}</div>
    <div>
      Ingredients List (v-for, maybe)
      <p>v-for ingredient in ingredients</p>
    </div>
  </div>
</template>

<script>
import RecipeService from '../services/RecipeService';
import IngredientsService from '../services/IngredientsService';

export default {
  created() {
    RecipeService.getRecipeByRecipeId(this.$route.params.id).then(
      (response) => {
        if(response.status === 200) {
          this.recipe = response.data;
        }
      }
    );
    IngredientsService.getIngredientsByRecipeId(this.$route.params.id).then(
      (response) => {
        if(response.status === 200) {
          this.ingredients = response.data;
        }
      }
    );
  },
  data() {
    return {
      recipe : {},
      ingredients: []
    }
  }
}
</script>

<style>

</style>