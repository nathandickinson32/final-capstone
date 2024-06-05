<template>
  <div class='container'>
    <!-- <div>
     <div>{{recipe}}
    {{ ingredients }}
     <p>picture ?</p>
    </div> -->
    <div><h1>{{recipe.recipeName}}</h1></div>
    <div>{{recipe.description}}</div>
    <div>{{ recipe.instructions }}</div>
    <div>
      <h3>Ingredients List</h3>
      <div v-for='ingredient in ingredients' v-bind:key='ingredient.id' class='ingredientCard'>
        <p>{{ ingredient.ingredientName }}</p>
      </div>
    </div>
    <instructions/>

  </div>
</template>

<script>
import RecipeService from '../services/RecipeService';
import IngredientsService from '../services/IngredientsService';
import Instructions from './Instructions.vue';
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
div.container {
  display: grid;
  justify-content: center;
}

div {
  margin: auto;
}

</style>