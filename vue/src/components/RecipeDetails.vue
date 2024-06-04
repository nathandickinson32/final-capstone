<template>
  <div class='container'>
    <div>{{recipe}}
    {{ ingredients }}
    <p>picture ?</p>
    </div>
    <div><h1>{{recipe.recipeName}}</h1></div>
    <div>{{recipe.description}}</div>
    <div>
      <h3>Ingredients List</h3>
      <div v-for='ingredient in ingredients' v-bind:key='ingredient.id' class='ingredientCard'>
        <p>{{ ingredient.ingredientName }}</p>
      </div>
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
div.container {
  display: grid;
  justify-content: center;
}

div {
  margin: auto;
}

.ingredientCard {
  overflow: scroll;
  width: 300px;
  height: 100px;
}

</style>