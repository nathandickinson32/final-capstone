<template>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <back-button id="back-button"/>
  <div class='container' id='recipe-details-container'>
    
    <div id='broad-details'>
      <div class='icon-holder'>
        <div v-on:click="favoriteUnfavorite(recipe.recipeId)" class='recipe icon details-icon' v-bind:class="this.idLibrary.includes(recipe.recipeId) ? 'favorite' : 'unfavorite'" v-show="this.$store.state.token != ''">
          </div>
      </div>
      <div id="details"><h1>{{recipe.recipeName}}</h1> <div>{{recipe.description}}</div></div>
      
    </div>
    
    <div class="details">
      
      <div id='instructions'>
        <instructions />
      </div>
      <div id="ingredients">
        <h3>Ingredients List</h3>
        <button class='grocery-btn'>
          <img id='add-to-cart' src='../images/add-to-cart.png'>
        </button>
        <div v-for='ingredient in ingredients' v-bind:key='ingredient.ingredientId' class='ingredientCard'>
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
    IngredientsService.getIngredientsByUserId().then(
      (response) => {
        if (response.status === 200) {
          this.groceryList = response.data;
          this.groceryList.forEach(
            (ingredient) => {
              this.groceryIdList.push(ingredient.ingredientId);
            }
          );
        }
      }
    ).catch(
      (error) => {
        if (error.response) {
          if (error.response.status == 404) {
            this.$router.push( {name: 'NotFoundView'} );
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
      idLibrary: [],
      groceryList: [],
      groceryIdList: []
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
    },
    addAllToGroceries() {
      this.ingredients.forEach(
        (item) => {
          if (!this.groceryIdList.includes(item.ingredientId)) {
            IngredientsService.addGroceryListItem(item.ingredientId).then(
              (response) => {
                if (response.status === 201) {
                  console.log("success!");
                }
              }
            )
          } else if (this.groceryIdList.includes(item.ingredientId)) {
            //Update grocery list row by 1
            IngredientsService.updateGroceryListItem(item.ingredientId).then(
              (response) => {
                if (response.status === 200) {
                  console.log("success!");
                }
              }
            )
          }
        }
      );
    }
  }


}

</script>

<style scoped>
div.container {
  display: grid;
  justify-content: center;
  /*grid-template-columns: 1fr 1fr;
  grid-template-areas: "details details"
                       "instructions ingredients";*/
  margin-top: 20px;
  position: relative;
  
}

div {
  margin: auto;
}
#back-button{
  position: absolute;
  left: 20%;
}

.broad-details {
  justify-self: center;
}

/*.details {
  display: grid;
  justify-content: space-between;
}

/*.ingredients {
  flex: 1;
  margin-right: 20px;
  border: 2px solid black;
  border-radius: 25px;
  padding: none;
}*/

#details {
  justify-content: center;
  grid-area: details;
  text-align: center;
  border: 1px black solid;
  border-radius: 10px;
  height: 20vh;
  background-color: rgb(127, 170, 127);
}

#ingredients {
 
  grid-area: ingredients;
  margin-right: 20px;
  justify-content: center;
  border: 1px black solid;
  border-radius: 10px;
  padding-left: 5px;
  padding-right: 5px;
  background-color: rgb(127, 170, 127);
  
}

#instructions {
  grid-area: instructions;
  margin-left: 20px;
  justify-content:center; 
  /** frontend 002 test */
}

@media only screen and (max-width: 600px) {
  div.container {
    image-rendering: auto;
    grid-template-columns: 1fr;
    grid-template-areas:
    "details"
    "ingredients"
    "instructions";
  }
  #details {
    height: 20vh;
    
  }
  

  
}

.broad-details {
  width: 600px;
  height: 250px;
}

#add-to-cart {
  height: 40px;
}

</style>