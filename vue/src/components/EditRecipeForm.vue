<template>
  
  <div class="container">
    <!-- {{ recipe }}
  {{ instructions }} -->

    <!-- {{ ingredients }}
    {{ allIngredients }} -->
        <!-- {{ this.userRecipeDTO.ingredientIds }} <br>
    {{ this.newIngredient }}
    <br> <br>
    {{ this.ingredients }} -->
    
    <hr id="separator">
    <hr id="separator">
    <label id="editInstructionsLabel1">Edit Recipe</label>
    <hr id="separator">

    <form v-on:submit.prevent="submitForms" class="recipeForm">
      <div class="form-group">
        <label for="recipeName">Recipe Name:</label>
        <input
          id="recipeName"
          type="text"
          class="form-control"
          v-model="userRecipeDTO.recipe.recipeName"
          autocomplete="off"
        />
        <!-- dto.recipe.recipeName -->
      </div>

      <div class="form-group">
        <label for="description">Description:</label>
        <textarea
          id="description"
          class="form-control"
          v-model="userRecipeDTO.recipe.description"
        ></textarea>
      </div>

      <hr id="separator">
    <hr id="separator">
    <label id="editInstructionsLabel">Edit Instructions</label>
    <hr id="separator">

      <div
        v-for="(instruction, index) in userRecipeDTO.recipeInstructions"
        :key="instruction.id"
        class="instructionCard"
      >
        <label :for="'step-' + index"
          >Step {{ userRecipeDTO.recipeInstructions[index].step }}:
        </label>
        <input
          class="stepText"
          :id="'step-' + index"
          type="text"
          v-model="userRecipeDTO.recipeInstructions[index].step"
        />

        <label :for="'instruction-' + index">
          Instruction
          <!-- {{ userRecipeDTO.recipeInstructions[index].instruction }} -->
          </label
        >
        <textarea
          class="instructionText"
          :id="'instruction-' + index"
          type="text"
          v-model="userRecipeDTO.recipeInstructions[index].instruction"
        ></textarea>
      </div>
      <!-- click calls method to add new instruction -->

      <!-- <div class="form-group">
        <label for="step">Step: </label>
        <input
          id="step"
          type="text"
          v-model="userRecipeDTO.recipeInstructions.step"
        />
        dto.recipe.recipeName
      </div> -->

      <!-- <div class="form-group">
        <label for="instruction">Instruction:</label>
        <textarea
          id="instruction"
          class="form-control"
          v-model="userRecipeDTO.recipe.instruction"
        ></textarea>
      </div> -->

      <div id="buttons">
        <button class="add-btn" v-on:click.prevent="addStep">
          Add Instruction
        </button>
        <hr id="separator">
        <hr id="separator">
        <hr id="separator">
        <label id="editIngredientsLabel">Edit Ingredients</label>
        <hr id="separator">

        <!-- {{ this.userRecipeDTO.ingredients }} <br> <br>
        {{ this.ingredients }} <br> <br>
        {{ this.userRecipeDTO.ingredientIds }} <br> <br>
        {{ this.selectedIngredients }} <br> <br> -->

        <div
          v-for="(ingredient, index) in ingredients"
          :key="ingredient.ingredientId"
          class="ingredientCard"
        >
          <label :for="'ingredientName-' + index"
            > {{ ingredients[index].ingredientName }}</label
          >
          <input
            :id="'ingredientName-' + index"
            type="checkbox"
            :value="ingredient.ingredientId"
            v-model="selectedIngredients"
            readonly
          />
          <input
            :id="'ingredientName-' + index + '-numerator'"
            class='ing-text-box'
            type="text" v-model="ingredients[index].numerator"
          />
          <a
          id="show-fraction-button"
          href="#"
          v-on:click.prevent="showFraction = !showFraction"
          >
            Fraction?
          </a>
          <div v-show="showFraction === true">
            <input
              :id="'ingredientName-' + index + '-denominator'"
              class='ing-text-box'
              type="text" v-model="ingredients[index].denominator"
            />
          </div>
          
          <div class="dropdown">
          <label for="allMeasurements"></label>
          <select
            name="allMeasurements"
            id="allMeasurements"
            v-model="ingredients[index].measurementType"
          >
            <!-- on:click needs to add ingredient to this.ingredients -->
            <option value="">-Unit of Measurement-</option>
            <!-- keep this value empty, select can't be selected -->

            <option
              v-for="measurement in allMeasurements"
              :key="measurement.measurementType"
              :value="measurement.measurementType"
              
            >
              {{ measurement.measurementType }}
            </option>
          </select>
        </div>

          

          <!-- {{ selectedIngredients}} -->
          <!-- v-model="ingredients[index].ingredientName" -->
        </div>

        <!-- <td
    v-for="ingredient in ingredients" 
    v-bind:key="ingredient.id"
    
    >
      
            <input
              type="checkbox"
              v-bind:id="ingredient.id"
              v-bind:value="ingredient.id"
              v-model="selectedIngredientIds"
             
            />
          </td> -->
        <button id="remove" v-on:click.prevent="removeSelectedIngredient">
          Remove Ingredients
        </button>

        <div class="dropdown">
          <label for="allIngredients"></label>
          <select
            name="allIngredients"
            id="allIngredients"
            v-on:change="addIngredient"
          >
            <!-- on:click needs to add ingredient to this.ingredients -->
            <option value="">-Choose Ingredient to add-</option>
            <!-- keep this value empty, select can't be selected -->

            <option
              v-for="ingredient in allIngredients"
              :key="ingredient.ingredientId"
              :value="ingredient.ingredientName"
            >
              {{ ingredient.ingredientName }}
            </option>
          </select>
        </div>

        <a
          id="show-form-button"
          href="#"
          v-on:click.prevent="showForm = true"
          v-if="showForm === false"
        >
          Don't see your ingredient?
        </a>
        <div v-show="showForm === true">
          <label for="ingredientText">New Ingredient Name</label>
          <input id="ingredientText" type="text" v-model="newIngredient.ingredientName" />
          <br>
          <br>
          <button class="add-btn" v-on:click.prevent="addNewIngredient">
          Add Ingredient
        </button>
        </div>

       

        <div>
          <button  class="btn btn-submit">Submit</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import RecipeService from "../services/RecipeService";
import InstructionService from "../services/InstructionService";
import IngredientsService from "../services/IngredientsService";
export default {
  data() {
    return {
      // isLoading: true,
      recipeId: -1,
      recipe: {},
      instructions: [],
      ingredients: [],
      selectedIngredients: [],
      allIngredients: [],
      newIngredient: {
        ingredientName: ""
      },
      showForm: false,
      showFraction: false,
      allMeasurements: [],


      userRecipeDTO: {
        recipe: {
          recipeName: "",
          description: "",
        },
        recipeInstructions: [
          {
            step: 0,
            instruction: "",
          },
        ],
        ingredientIds: [],

        ingredients: []
          // [
          //   {
          //       ingredientId: "",
          //   numerator: "",
          //   denominator: "",
          //   measurementType : ""
          //   }
            
          // ]

      },
    };
  },

  created() {
    this.recipeId = this.$route.params.id;

    IngredientsService.getAllMeasurements().then((response) => {
      if (response.status === 200) {
        this.allMeasurements = response.data;
      }
    });

    RecipeService.getRecipeByRecipeId(this.recipeId).then((response) => {
      this.userRecipeDTO.recipe = response.data;
      // this.isLoading = false;
    });

    IngredientsService.getAllIngredients().then((response) => {
      if (response.status === 200) {
        this.allIngredients = response.data;
      }
    });

    InstructionService.getInstructionsByRecipeId(this.$route.params.id).then(
      (response) => {
        if (response.status === 200) {
          this.userRecipeDTO.recipeInstructions = response.data;
        }
      }
    );

    RecipeService.getRecipeByRecipeId(this.$route.params.id).then(
      (response) => {
        if (response.status === 200) {
          this.recipe = response.data;
        }
      }
    );
    IngredientsService.getIngredientsByRecipeId(this.$route.params.id).then(
      (response) => {
        if (response.status === 200) {
          this.ingredients = response.data;
          this.ingredients.forEach((ingredient) => {
            this.userRecipeDTO.ingredientIds.push(ingredient.ingredientId);
            this.userRecipeDTO.ingredients.push(ingredient);
          });
        }
      }
    );
  },

  methods: {
    submitForms() {
      this.userRecipeDTO.ingredients = this.ingredients;
      RecipeService.addCustomUserRecipe(this.userRecipeDTO)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push(`/customRecipes`);
            //add instruction
          }
        })
        .catch((error) => {
          console.error("Error adding recipe:", error);
        });
    },

    addInstructions(recipeId) {
      // Prepare instructions data to be added
      const instructionsData = this.userRecipeDTO.recipeInstructions.map(
        (instruction) => ({
          step: instruction.step,
          instruction: instruction.instruction,
          recipeId: recipeId,
        })
      );

      // Add instructions using InstructionService
      InstructionService.addInstructions(instructionsData)
        .then((response) => {
          if (response.status === 201) {
            console.log("success");
          }
        })
        .catch((error) => {
          console.error("Error adding instructions:", error);
        });
    },

    addStep() {
      const newInstruction = {
        step: "",
        instruction: "",
      };
      this.userRecipeDTO.recipeInstructions.push(newInstruction);
    },

    // add, remove, create ingredient

    addNewIngredient() {
   
      if(this.newIngredient.ingredientName!=""){
        let checkId = this.allIngredients.length + 1;
        IngredientsService.addIngredient(this.newIngredient).then(
          (response) => {
            if (response.status === 201) {
              this.newIngredient.ingredientId = response.data;
              this.ingredients.push(this.newIngredient);
              this.userRecipeDTO.newIngredients.push(checkId);
              this.userRecipeDTO.ingredientIds.push(this.newIngredient.ingredientId);
              this.showForm = false;
              this.newIngredient = {
                ingredientName:""
              };
            }
          }
        );
        
      }
    },
    addIngredient(event) {
      const ingredientName = event.target.value.trim();
      const ingredient = this.allIngredients.find(
        (ing) => ing.ingredientName === ingredientName
      );
      if (ingredient) {
        let newIngredientId = ingredient.ingredientId;

        this.ingredients.push({ ...ingredient, quantity: "" });
        this.userRecipeDTO.ingredients.push({ ...ingredient, quantity: "" });

        this.userRecipeDTO.ingredientIds.push(newIngredientId);
      }
      event.target.value = "";
    },

    removeSelectedIngredient() {
      this.selectedIngredients.forEach((id) => {
        let index = this.ingredients.findIndex(
          (ingredient) => (ingredient.ingredientId === id)
        );
        this.ingredients.splice(index, 1);
        this.userRecipeDTO.ingredients.splice(index, 1);
        let index2 = this.userRecipeDTO.ingredientIds.findIndex(
          (ingredientId) => (ingredientId === id)
        );
        this.userRecipeDTO.ingredientIds.splice(index2, 1);
      });

      this.selectedIngredients = [];
    },


  },
};
</script>

<style scoped>

.ing-text-box {
  background-color: pink;
  font-family: "Montserrat", serif;
  font-size: 20px;
  max-width: 20px;
  border-radius: 10px;
}
.add-btn {
  border: 1px solid black;
  border-radius: 10px;
  background-color: pink;
  font-family: "Montserrat", serif;
  font-size: 20px;
  cursor: pointer;
  border-radius: 10px;
}
button {
  cursor: pointer;
}

#editInstructionsLabel1 {
  font-family: 'LibreBaskerville';
  font-size: 40px;
  text-decoration: underline;
  display: flex;
  flex-direction: column;
  align-items: center;
  
}

#editInstructionsLabel {
  font-family: 'LibreBaskerville';
  font-size: 30px;
  text-decoration: underline;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#editIngredientsLabel {
  font-family: 'LibreBaskerville';
  font-size: 30px;
  text-decoration: underline;
  
}
#separator {
  opacity: 0;
}
#show-form-button{
  font-size: 20px;
}
#measurements {
  background-color: pink;
  
}


.container {
  border: 2px solid black;
  border-radius: 25px;
  padding-bottom: 5px;
  background-color: rgb(127, 170, 127);
  margin-top: 10px;
}
#ingredientText {
  background-color: pink;
  
}

#recipeName {
  background-color: pink;
  font-family: "Montserrat", serif;
  font-size: 20px;
  border-radius: 10px;
}

#description {
  background-color: pink;
  font-family: "Montserrat", serif;
  font-size: 20px;
  border-radius: 10px;
}

#step {
  background-color: pink;
  max-width: 20px;
  font-family: "Montserrat", serif;
  font-size: 20px;
  border-radius: 10px;
}

#instruction {
  background-color: pink;
  padding-bottom: 20px;
  font-family: "Montserrat", serif;
  font-size: 20px;
  border-radius: 10px;
}

.instructionText {
  background-color: pink;
  font-family: "Montserrat", serif;
  font-size: 20px;
  border-radius: 10px;
}

.stepText {
  background-color: pink;
  font-family: "Montserrat", serif;
  font-size: 20px;
  max-width: 20px;
  border-radius: 10px;
}



textarea {
  width: 400px;
  height: 100px;
  font-family: "Montserrat", serif;
  font-size: 20px;
  border-radius: 10px;
}
.dropdown option {
  background-color: pink;
  font-family: "Montserrat";
  font-size: 18px;
  border-radius: 10px;
}
#remove {
  background-color: pink;
  border-radius: 10px;
  font-family: 'Montserrat', serif;
  font-size: 18px;
  height: 30px;
  width: 170px;
  cursor: pointer;
}
#allIngredients {
  background-color: pink;
  font-family: 'Montserrat', serif;
  font-size: 18px;
  border-radius: 10px;
}
h2{
  font-family: 'libreBaskerville', serif;
}
.ingredientCard {
  font-family: 'Montserrat', serif;
  font-size: 20px;
}
.ingredientCard input {
  background-color: pink;
  border-radius: 10px;
}

@media only screen and (max-width: 600px) {
  .form-group {
    border-bottom: 2px dotted black;
    padding: 5px;
  }
  .instructionCard {
    border-bottom: 2px dotted black;
    padding: 5px;
  }
  textarea {
    font-size: 16px;
  }

  .container {
    font-size: 18px;
  }

  #recipeName {
    font-size: 16px;
  }

  #description {
    font-size: 16px;
  }
  #step {
    font-size: 16px;
  }
  .stepText {
    font-size: 16px;
  }
}
</style>