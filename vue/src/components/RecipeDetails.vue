<template>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  
  <div class='container' id='recipe-details-container'>
    <back-button id="back-button"/>
    <!-- <div id='broad-details'> -->
      <!-- <div class='icon-holder'>
        <div v-on:click="favoriteUnfavorite(recipe.recipeId)" class='recipe icon details-icon' v-bind:class="this.idLibrary.includes(recipe.recipeId) ? 'favorite' : 'unfavorite'" v-show="this.$store.state.token != ''">
        </div>
      </div> -->
    <!-- </div> -->

    <div id="details">
      <div class='icon-holder'>
        <div v-on:click="favoriteUnfavorite(recipe.recipeId)" class='recipe icon details-icon' v-bind:class="this.idLibrary.includes(recipe.recipeId) ? 'favorite' : 'unfavorite'" v-show="this.$store.state.token != ''">
        </div>
      </div>
      <div class='recipe-name'>
        <h1>{{recipe.recipeName}}</h1> 
      </div>
      <div id='description'>
        <p>{{recipe.description}}</p>
        <div id='customize-btn'>
      <button class='btn'>
          <router-link v-bind:to="{name: 'editRecipe', params: {id: recipe.recipeId}}">Customize</router-link>
        </button>
    </div>
      </div>
    </div>

    
    
    <!-- <div class="details"> -->
      
      <div id='instructions'>
        <instructions />
      </div>
      <div id="ingredients">
        <h3>Ingredients List</h3>
        <div id='add-to-cart-container' v-show="this.$store.state.token != ''">
          <label for='add-to-cart-btn' id='add-to-cart-label'>Add to Groceries:</label>
          <button v-on:click='addAllToGroceries' id='add-to-cart-btn' class='grocery-btn'>
            <img id='add-to-cart' src='../images/add-to-cart.png'>
          </button>
        </div>
        
        <div v-for='ingredient in ingredients' v-bind:key='ingredient.ingredientId' class='ingredientCard'>
          <p>{{ ingredient.ingredientName }}</p>
        </div>
      
      </div>
    <!-- </div> -->
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
    if (this.$store.state.token != '') {
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
    }
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
                  //
                }
              }
            );
          } else if (this.groceryIdList.includes(item.ingredientId)) {
            IngredientsService.updateGroceryListItem(item.ingredientId).then(
              (response) => {
                if (response.status === 200) {
                  //
                }
              }
            );
          }
        }
      );
      window.alert('Added to Grocery List!');
    }
  }


}

</script>

<style scoped>
div.container {
  display: grid;
  justify-content: center;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "button button"
                       "details details"
                       "customize customize"
                       "instructions ingredients";
  margin-top: 20px;
  align-items: start;
  /* position: relative; */
}

div.icon-holder {
  margin-top: 2%;
  display: grid;
  height: 40px;
  justify-content: end;
}

div.icon {
  width: 50px;
  height: 50px;
  border: none;
  margin-top: 5px;
  background-repeat: no-repeat;
  background-size: 40px 40px;
}
h3{
  font-family: 'LibreBaskerville', serif;
}

div.unfavorite {
  background-image: url(/star_outline.png);
}

div.favorite {
  background-image: url(/star_full.png);
}

#broad-details {
  grid-area: details;
  display: flex;
  width : 75vw;
  justify-self: center;
  align-items: center;
  height: fit-content;
}

#back-button {
  grid-area: button;
}

#details {
  display: grid;
  justify-content: center;
  grid-template-rows: 1fr 3fr 4fr;
  grid-area: details;
  text-align: center;
  border: 1px black solid;
  border-radius: 10px;
  height: 20vh;
  min-height: 350px;
  background-color: rgb(127, 170, 127);
  margin-bottom: 20px;
  width: 75vw;
  justify-self: center;
}

div.recipe-name {
  padding-top: 10px;
  height: fit-content;
  font-family: 'LibreBaskerville', serif;
}

#description {
  width: 50vw;
  text-align: center;
  height: fit-content;
  justify-self: center;
}

#instructions > div {
  margin-left: 0px;
}

#description > p {
  margin: 0px;
}

#ingredients {
  display: flex;
  flex-direction: column;
  grid-area: ingredients;
  margin: auto;
  justify-content: center;
  border: 1px black solid;
  border-radius: 10px;
  padding-left: 5px;
  padding-right: 5px;
  background-color: rgb(127, 170, 127);
  width: 75%;
  font-family: 'Montserrat', serif;
  font-size: 20px;
}

#instructions {
  grid-area: instructions;
  margin: auto;
  justify-content:center; 
  /** frontend 002 test */
  font-family: 'Montserrat', serif;
  font-size:20px;
  text-decoration: none;
}

#customize-btn {
  grid-area: customize;
  justify-self: center;
}

#customize-btn > button {
  background-color:rgb(255, 192, 203);
  color: black;
  border: 1px solid;
  border-color: black;
  border-radius: 10px;
}

@media only screen and (max-width: 700px) {
  div.container {
    image-rendering: auto;
    grid-template-columns: 1fr;
    grid-template-areas:
    "button"
    "details"
    "customize"
    "ingredients"
    "instructions";
  }
  #details {
    height: 30vh;
    height: fit-content;
    box-sizing: border-box;
    width: 75%;
    grid-template-rows: 1fr 2fr 3fr;
  }
  #back-button {
    margin-left: 40%;
  }
}

#add-to-cart {
  height: 40px;

}

h3, p {
  text-align: center;
}

h1 {
  margin: 0px
}

.ingredientCard {
  margin-top: 3px;
  margin-bottom: 3px;
}

.ingredientCard > p {
  margin-top: 3px;
  margin-bottom: 3px;
}

.ingredientCard:nth-last-child(1) {
  margin-bottom: 10px;
}

#add-to-cart-label {
  grid-area: label;
  display: inline-block;
  align-self: center;
  justify-self: end;
  padding-right: 5px;
}

#add-to-cart-btn {
  grid-area: button;
  background-color:rgb(255, 192, 203);
  border: 1px solid;
  border-color: antiquewhite;

  border-radius: 10px;
  width: 50%;
  height: 45px;
  cursor: pointer;
}

#add-to-cart-container {
  /* padding-right: 25%;
  padding-left: 25%; */
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "label button";
  justify-content: center;
  padding-bottom: 5px;
}

</style>