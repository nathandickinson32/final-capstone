<template>
    <div> 
      <div>this is the favorite recipes page</div>
      {{ library }}
    </div>

  </template>
  
  <script>
import RecipeService from '../services/RecipeService'

  export default {
    data() {
      return {
        library: []
      }
    },
    created() {
      RecipeService.getRecipeLibraryByUser().then(
        (response) => {
          if (response.status === 200) {
            this.library = response.data;
          }
        }
      );
    }
  }
  </script>
  
  <style>
  
  </style>