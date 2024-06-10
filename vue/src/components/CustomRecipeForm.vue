<template>
  {{ recipe }}
   <form v-on:submit.prevent="submitForm" class="recipeForm">
    <div class="form-group">
      <label for="recipeName">Recipe Name:</label>
      <input
        id="recipeName"
        type="text"
        class="form-control"
        v-model="recipe.recipeName"
        autocomplete="off"
      />
    </div>
 
    <div class="form-group">
      <label for="description">Description:</label>
      <textarea
        id="description"
        class="form-control"
        v-model="recipe.description"
      ></textarea>
    </div>
    <button class="btn btn-submit">Submit</button>
  </form>
</template>

<script>
import RecipeService from '../services/RecipeService';
export default {
    data() {
     return {
        // isLoading: true,
         recipeId : -1,
         recipe : {}
        
     }
 },

 created() {
    this.recipeId = this.$route.params.id;

    RecipeService.getRecipeByRecipeId(this.recipeId).then((response) => {
      this.recipe = response.data;
     // this.isLoading = false;
    });
  },
  methods : {
    submitForm() {
      RecipeService.addCustomRecipe(this.recipe).then(
        (response) => {
          if(response.status === 201) {
            this.$router.push(`/`);
          }
        }
      )
      
    }
  }

}

</script>

<style>

</style>