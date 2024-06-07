<template>
  <div class='container'>
    <back-button />
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
import Instructions from '../components/Instructions.vue';
import BackButton from '../components/BackButton.vue';
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
    BackButton,
    Instructions
   
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

.details {
  display: flex;
  justify-content: space-between;
}

.ingredients {
  flex: 1;
  margin-right: 20px;
  border: 2px solid black;
  border-radius: 25px;
  padding: none;
  


}

.instructions {
  flex: 1;
  margin-left: 20px;
}

</style>