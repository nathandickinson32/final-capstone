<template>
    <div> 
      <div class="container">
    <back-button/>
    <!-- <h1>TEST</h1> -->
    <!-- {{ recipes }} -->
    {{ library }}
    <div v-for='recipe in library' v-bind:key='recipe.recipeId' class='recipeCard'>
      <div class='recipe icon-holder'>
        <div v-on:click="favoriteUnfavorite" class='recipe icon' v-bind:class="this.idLibrary.includes(recipe.recipeId) ? 'favorite' : 'unfavorite'" v-show="this.$store.state.token != ''">
        </div>
      </div>
      <!-- <img class='recipe favorite' src='/star_full.png' v-show="this.$store.state.token != ''"> -->
      <!-- <div class='recipe favorite'></div> -->
      <div class="recipe text-boxes">
        <div class="recipe name">
          <h1 class="recipe-head-item">{{ recipe.recipeName }}</h1>
        </div>
        <div class="recipe description">{{ recipe.description }}</div>
      </div>
      <div>
        <button class="btn">
          <router-link v-bind:to="{ name: 'recipe', params: { id: recipe.recipeId } }"
            >View Details</router-link
          >
        </button>
      </div>
    </div>
  </div>
    </div>

  </template>
  
  <script>

import RecipeService from '../services/RecipeService'
import BackButton from '../components/BackButton.vue';

  export default {
    components: {
        BackButton
    },
    data() {
      return {
        library: [],
        idLibrary: []
      }
    },
    created() {
      RecipeService.getRecipeLibraryByUser().then(
        (response) => {
          if (response.status === 200) {
            this.library = response.data;
            this.library.forEach(
            (recipe) => {
              console.log('almost there!');
              this.idLibrary.push(recipe.recipeId);
              console.log('pushed');
            }
          );
          }
        }
      );
    },

  }
  </script>
  
  <style>
  
  </style>