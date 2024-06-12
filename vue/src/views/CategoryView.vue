<template>
  <div class="container">
    
    <back-button/>
    <div v-for='recipe in recipes' v-bind:key='recipe.recipeId' class='recipeCard'>
      <!--<RandomImage  id="randomImage" />-->
      <img v-if="recipe.recipeId == 1" src="/public/sausageballs.webp"/>
      <img v-if="recipe.recipeId == 2" src="/public/moussemiddle-1-of-1.webp"/>
      <img v-if="recipe.recipeId == 3" src="/public/img_4961-copy.webp"/>
      <img v-if="recipe.recipeId == 4" src="/public/pexels-h-a-m-e-official-1493053-4728230.jpg"/>
      <img v-if="recipe.recipeId == 5" src="/public/pexels-enginakyurt-1552635.jpg"/>
      <img v-if="recipe.recipeId == 6" src="/public/pexels-adrian-dorobantu-989175-2300034.jpg"/>
      <img v-if="recipe.recipeId == 7" src="/public/"/>
      <img v-if="recipe.recipeId == 8" src="/public/"/>
      <img v-if="recipe.recipeId == 9" src="/public/"/>
      <img v-if="recipe.recipeId == 10" src="/public/"/>
      <img v-if="recipe.recipeId == 11" src="/public/"/>
      <img v-if="recipe.recipeId == 12" src="/public/pexels-dacapture-19359972.jpg"/>
      <img v-if="recipe.recipeId == 13" src="/public/pexels-sude-akpinar-48866908-13803825.jpg"/>
      <img v-if="recipe.recipeId == 14" src="/public/pexels-valeriya-1639557.jpg"/>
      <img v-if="recipe.recipeId == 15" src="/public/pexels-ofir-eliav-2397652-6806028.jpg"/>
      <img v-if="recipe.recipeId == 16" src="/public/pexels-kyleroxas-2122294.jpg"/>
      <img v-if="recipe.recipeId == 17" src="/public/pexels-elletakesphotos-1483780.jpg"/>
      <img v-if="recipe.recipeId == 18" src="/public/pexels-adana-eisagholian-1093385826-20666398.jpg"/>
      <img v-if="recipe.recipeId == 19" src="/public/pexels-george-piskov-289673052-15047513.jpg"/>
      <img v-if="recipe.recipeId == 20" src="/public/pexels-valeriya-7474366.jpg"/>
      <div class='recipe icon-holder'>
        <div v-on:click="favoriteUnfavorite(recipe.recipeId)" class='recipe icon' v-bind:class="this.idLibrary.includes(recipe.recipeId) ? 'favorite' : 'unfavorite'" v-show="this.$store.state.token != ''">
        </div>
      </div>
      <div class="recipe text-boxes">
        <div class="recipe name">
          <h1 class="recipe-head-item">{{ recipe.recipeName }}</h1>
        </div>
        <div id="description" class="recipe description">{{ recipe.description }}</div>
      </div>
      <div>
        <button class="btn">
          <router-link v-bind:to="{ name: 'recipe', params: { id: recipe.recipeId } }"
            >View Details</router-link
          >
        </button>
        |
        <button class='btn'>
          <router-link v-bind:to="{name: 'editRecipe', params: {id: recipe.recipeId}}">Customize</router-link>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import BackButton from '../components/BackButton.vue';
import RecipeService from "../services/RecipeService";
import RandomImage from '../components/RandomImage.vue';

export default {
  components: {
    BackButton,
    RandomImage
  },
  data() {
    return {
      category: {},
      recipes: [],
      library: [],
      idLibrary: []
    }
  },
  created() {
    RecipeService.getRecipesByCategoryId(this.$route.params.id).then(
      (response) => {
        if (response.status === 200) {
          this.recipes = response.data;
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
    );}
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

<style >

.container {
  display: grid;
  justify-content: center;
  margin-top: 20px;
}
.recipe-head-item {
  font-family: 'LibreBaskerville', serif;
}

.recipeCard {
  border: 1px black solid;
  border-radius: 25px;
  width: 75vw;
  min-height: 250px;
  background-color: rgb(127, 170, 127);
  
}
#description {
  font-family: 'Montserrat', serif;
  font-size: 20px;
  text-decoration: none;
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
  top: 50%;
  background-color: pink;
  color: black;
  height: 30px;
  width: 120px;
  border: 1px solid;
  border-color: black;
  border-radius: 10px;
  /* margin-left: 10px; */
  margin-top: auto;
  margin-bottom: auto;
  font-family: 'Montserrat', serif;
  font-size: 17px;
}
img {
    max-height: auto;
    width: 70%;
    border: 5px solid pink;
    border-radius: 25px;
}

</style>