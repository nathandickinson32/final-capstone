<template>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <div class='container'>
    
    <!-- <div>
     <div>{{recipe}}
    {{ ingredients }}
     <p>picture ?</p>
    </div> -->
    <div><h1>{{recipe.recipeName}}</h1></div>
    <div>{{recipe.description}}</div>
    <div class="details">
      <div class="instructions">
        <instructions/>
      </div>
    

      <div class="ingredients">
        <h3>Ingredients List</h3>
        <div v-for='ingredient in ingredients' v-bind:key='ingredient.id' class='ingredientCard'>
        <p>{{ ingredient.ingredientName }}</p>
        </div>
      
      </div>
    </div>
   
   

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
  },

  components: {
    Instructions
  }


}

</script>

<style>
div.container {
  display: grid;
  justify-content: center;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "details details"
                       "instructions ingredients";
  margin-top: 10%;
  position: relative;
  
}

div {
  margin: auto;
}

.details {
  justify-content: center;
  grid-area: details;
}

.ingredients {
  flex: 1;
  grid-area: ingredients;
  margin-right: 20px;
  justify-content: center
}

.instructions {
  grid-area: instructions;
  flex: 1;
  margin-left: 20px;
  justify-content:center; 
  /** frontend 002 test */
}
@media only screen and (max-width: 600px) {
  .container {
    image-rendering: auto;
    grid-template-columns: 1fr;
    grid-template-areas:
    "details"
    "ingredients"
    "instructions";
  }
}




</style>