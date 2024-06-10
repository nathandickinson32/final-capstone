<template>
  <div class='container' id='recipe-details-container'>
    <back-button />
    <!-- <div>
     <div>{{recipe}}
    {{ ingredients }}
     <p>picture ?</p>
    </div> -->
    <div class='broad-details'>
      <div class='icon-holder'>
        <div v-on:click="favoriteUnfavorite(recipe.recipeId)" class='recipe icon details-icon' v-bind:class="this.idLibrary.includes(recipe.recipeId) ? 'favorite' : 'unfavorite'" v-show="this.$store.state.token != ''">
          </div>
      </div>
      <div><h1>{{recipe.recipeName}}</h1></div>
      <div>{{recipe.description}}</div>
    </div>
    
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
  data() {
    return {
      recipe : {},
      ingredients: [],
      library: [],
      idLibrary: []
    }
  },

  components: {
    BackButton,
    Instructions
  },
  methods: {
    favoriteUnfavorite(id) {
      if(!this.idLibrary.includes(id)){
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
        );      
      } else {
        RecipeService.deleteRecipeFromLibrary(id).then(
          response => {
            if(response.status === 200) {
              this.idLibrary= this.idLibrary.filter((item) => item != id);
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
      }
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

.broad-details {
  justify-self: center;
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

.broad-details {
  width: 600px;
  height: 250px;
}

</style>