<template>
    <div class='container'>
      <back-button />
      <div v-for='recipe in recipes' v-bind:key='recipe.recipeId' class='recipeCard'>
        <div class='recipe icon-holder'>
          <div v-on:click="favoriteUnfavorite(recipe.recipeId)" class='recipe icon' v-bind:class="this.idLibrary.includes(recipe.recipeId) ? 'favorite' : 'unfavorite'" v-show="this.$store.state.token != ''">
          </div>
        </div>
        <div class="recipe text-boxes">
          <div class="recipe name">
            <h1 class="recipe-head-item">{{ recipe.recipeName }}</h1>
          </div>
          <div class="recipe description">{{ recipe.description }}
          </div>
        </div>
        <div>
          <button class="btn">
            <router-link v-bind:to="{ name: 'recipe', params: { id: recipe.recipeId } }"
            >View Details</router-link>
          </button>
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
        recipes: [],
        library: [],
        idLibrary: []
      }
    },
    created() {
      RecipeService.getRecipeByAuthorId().then(
        (response) => {
          if (response.status === 200) {
            this.recipes = response.data;
          }
        }
      ).catch(error => {
          if (error.response) {
            if (error.response.status == 404) {
              this.$router.push( {name: 'NotFoundView'} );
            } else {
              // this.$store.commit('SET_NOTIFICATION',
              // `Error getting message. Response received was "${error.response.statusText}".`);
            }
          } else if (error.request) {
            // this.$store.commit('SET_NOTIFICATION', `Error getting message. Server could not be reached.`);
          } else {
            // this.$store.commit('SET_NOTIFICATION', `Error getting message. Request could not be created.`);
          }
        }
      );
      RecipeService.getRecipeLibraryByUser().then(
        (response) => {
          if (response.status === 200) {
            this.library = response.data;
            this.library.forEach(
              (recipe) => {
                this.idLibrary.push(recipe.recipeId);
              }
            );
          }
        }
      ).catch(error => {
          if (error.response) {
            if (error.response.status == 404) {
              this.$router.push({name: 'NotFoundView'});
            } else {
              // this.$store.commit('SET_NOTIFICATION',
              // `Error getting message. Response received was "${error.response.statusText}".`);    
            }
          } else if (error.request) {
            // this.$store.commit('SET_NOTIFICATION', `Error getting message. Server could not be reached.`);
          } else {
            // this.$store.commit('SET_NOTIFICATION', `Error getting message. Request could not be created.`);
          }
        }
      );
    },
    methods: {
      favoriteUnfavorite(id) {
        if(!this.idLibrary.includes(id)) {
          RecipeService.addRecipeToLibrary(id).then(
            (response) => {
              if(response.status === 201) {
                this.idLibrary.push(id);
              }
            }
          ).catch(error => {
              if (error.response) {
                if (error.response.status == 404) {
                  this.$router.push({name: 'NotFoundView'});
                } else {
                  // this.$store.commit('SET_NOTIFICATION',
                // `Error getting message. Response received was "${error.response.statusText}".`);
                }
              } else if (error.request) {
                // this.$store.commit('SET_NOTIFICATION', `Error getting message. Server could not be reached.`);
              } else {
                // this.$store.commit('SET_NOTIFICATION', `Error getting message. Request could not be created.`);
              }
            }
          )
        } else {
          RecipeService.deleteRecipeFromLibrary(id).then(
            response => {
              if (response.status === 200) {
                this.idLibrary = this.idLibrary.filter((item) => item != id);
              }
            }
          ).catch(error => 
            {
              if (error.response) {
                if (error.response.status == 404) {
                  this.$router.push({name: 'NotFoundView'});
                } else {
                  // this.$store.commit('SET_NOTIFICATION',
                  // `Error getting message. Response received was "${error.response.statusText}".`);
                }
              } else if (error.request) {
                // this.$store.commit('SET_NOTIFICATION', `Error getting message. Server could not be reached.`);
              } else {
                // this.$store.commit('SET_NOTIFICATION', `Error getting message. Request could not be created.`);
              }
            }
          )
        }
      }
    }
  }
  </script>
  
  <style>
  
  </style>