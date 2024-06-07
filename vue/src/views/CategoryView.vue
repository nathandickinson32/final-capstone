<template>
  <div class='container'>
    <!-- <h1>TEST</h1> -->
    <!-- {{ recipes }} -->
    {{ library }}
    <div v-for='recipe in recipes' v-bind:key='recipe.id' class='recipeCard'>
      <div class='recipe icon-holder'><div class='recipe icon' v-bind:class="checkFavorite(recipe) ? 'favorite' : 'unfavorite'" v-show="this.$store.state.token != ''"></div></div>
      <!-- <img class='recipe favorite' src='/star_full.png' v-show="this.$store.state.token != ''"> -->
      <!-- <div class='recipe favorite'></div> -->
      <div class='recipe text-boxes'>
        <div class='recipe name'><h1 class='recipe-head-item'>{{ recipe.recipeName }}</h1></div>
      <div class='recipe description'>{{ recipe.description }}</div>
      </div>
      <div><button class='btn'><router-link v-bind:to="{name: 'recipe', params: {id: recipe.id}}">View Details</router-link></button></div>
    </div>
  </div>
  
</template>

<script>
import RecipeService from '../services/RecipeService';

export default {
  data() {
    return {
      recipes: [],
      library: [],
      image1 : '/star_outline.png',
      image2 : '/star_full.png'
    }
  },
  created() {
    RecipeService.getRecipesByCategoryId(this.$route.params.id).then(
      (response) => {
        if(response.status === 200) {
          this.recipes = response.data;
        }
      }
    );
    RecipeService.getRecipeLibraryByUser().then(
      (response) => {
        if (response.status === 200) {
          this.library = response.data;
        }
      }
    );
  },
  methods: {
    favoriteUnfavorite() {
      const image1 = '/star_outline.png';
      const image2 = '/star_full.png';
    },
    checkFavorite(recipe) {
      return this.library.includes(recipe);
    }
  }
}
</script>

<style>

.container {
  display: grid;
  justify-content: center;
  margin-top: 20px;
}

.recipeCard {
  border: 1px black solid;
  border-radius: 25px;
  width: 75vw;
  height: 250px;
}

.container div {
  text-align: center;
  margin: 15px;
}

h1 {
  text-align: center;
}

.recipe-head {
  display: inline-block;
  height: auto;
}

div.icon-holder {
  display: grid;
  height: 40px;
  justify-content: end;
}

div.icon {
  width: 50px;
  height: 50px;
  border: none;
  margin: 0;
  background-repeat: no-repeat;
  background-size: 40px 40px;
}

div.unfavorite {
  background-image: url(/star_outline.png);
}

div.favorite {
  background-image: url(/star_full.png);
}

.btn {
  text-align: center;
  top:50%;
  background-color:pink;
  color: black;
  height: 20px;
  width: 90px;
  border: 1px solid;
  border-color: black;
  border-radius: 10px;
  margin-left: 10px;
  margin-top: auto;
  margin-bottom: auto;
}

</style>