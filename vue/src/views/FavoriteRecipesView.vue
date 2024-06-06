<template>
    <div> 
      <div>this is the favorite recipes page</div>
      {{ library }}
      <category-view-vue v-bind:library="library"/>
    </div>

  </template>
  
  <script>
import RecipeService from '../services/RecipeService'
import CategoryViewVue from './CategoryView.vue'

  export default {
    components: {
      CategoryViewVue
    },
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