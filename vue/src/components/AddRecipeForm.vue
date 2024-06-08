<!-- <template>

  <div id="addRecipe">
    <form v-on:submit.prevent="addRecipe">
      <h1 >Add A New Recipe</h1>
   
     
      <div class="form-input-group">
        <label for="recipe-name">Recipe Name</label>
        <input type="text" id="recipe-name" v-model="recipe.recipeName" required/>
      </div>
      <div class="form-input-group">
        <label for="description">Description</label>
        <input type="text" id="description" v-model="recipe.description" required/>
      </div>
      <button id="addRecipe" type="submit">Add Recipe</button>
    
    </form>

    <form v-on:submit.prevent="addRecipe">
      <h1 >Add A New Recipe</h1>
   
     
      <div class="form-input-group">
        <label for="recipe-name">Recipe Name</label>
        <input type="text" id="recipe-name" v-model="recipe.recipeName" required/>
      </div>
      <div class="form-input-group">
        <label for="description">Description</label>
        <input type="text" id="description" v-model="recipe.description" required/>
      </div>
      <button id="addRecipe" type="submit">Add Recipe</button>
    
    </form>

  </div>
</template>

<script>
import authService from "../services/AuthService";
import RecipeService from '../services/RecipeService';
export default {
  data() {
    return {
      recipe: {
        recipeName: "",
        description: ""
      }
    }
  },

  methods: {
    addRecipe() {
      RecipeService.addRecipe(this.recipe).then(
        (response) => {
          if(response.status === 201) {
            console.log("success")
            this.$router.push("/customRecipes")
          }
        }
      )
    }
  }
}

</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
  
}
label {
  margin-right: 0.5rem;
}


#recipe-name{
  background-color: pink;;
}
#description {
  background-color: pink;
}
#submit {
  background-color: pink;
  border-radius: 10px;
  height: 30px;
  width: 60px;
}

</style> -->