<template>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  </head>
  <div class="container">
    <!-- {{ recipe }}
  {{ instructions }} -->

    <!-- {{ ingredients }}
    {{ allIngredients }} -->
    {{ this.userRecipeDTO.ingredientIds }}

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
          {{ userRecipeDTO.recipeInstructions[index].instruction }}</label
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
<h2>Ingredients</h2>
        <div
          v-for="(ingredient, index) in ingredients"
          :key="ingredient.ingredientId"
          class="ingredientCard"
        >
          <label :for="'ingredientName-' + index"
            >{{ ingredients[index].ingredientName }}</label
          >
          <input
            :id="'ingredientName-' + index"
            type="checkbox"
            v-model="selectedIngredients[index]"
            readonly
          />
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
        <button>Remove Ingredients</button>
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

        <div>
          <button class="btn btn-submit">Submit</button>
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
      },
    };
  },

  created() {
    this.recipeId = this.$route.params.id;

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
          });
        }
      }
    );
  },

  methods: {
    submitForms() {
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
    addIngredient(event) {
      const ingredientName = event.target.value.trim();
      const ingredient = this.allIngredients.find(
        (ing) => ing.ingredientName === ingredientName
      );
      if (ingredient) {
        let newIngredientId = ingredient.ingredientId;

        this.ingredients.push({ ...ingredient, quantity: "" });
        this.userRecipeDTO.ingredientIds.push(newIngredientId);
      }
      event.target.value = "";
    },

    //     addNewIngredientToRecipe(selectedIngredient) {
    //   console.log("Selected Ingredient:", selectedIngredient);
    //  let newIngredientId = selectedIngredient.ingredientId;
    //   console.log("New Ingredient ID:", newIngredientId);
    //   this.userRecipeDTO.ingredientIds.push(newIngredientId);
    //   console.log("User Recipe DTO:", this.userRecipeDTO);
    // },
  },
};
</script>

<style scoped>
.add-btn {
  border: 1px solid black;
  border-radius: 10px;
  background-color: pink;
  font-family: 'Montserrat', serif;
}

.container {
  border: 2px solid black;
  border-radius: 25px;
  padding-bottom: 5px;
  background-color: rgb(127, 170, 127);
}

#recipeName {
  background-color: pink;
  font-family: 'Montserrat', serif;
}

#description {
  background-color: pink;
  font-family: 'Montserrat', serif;
}

#step {
  background-color: pink;
  max-width: 20px;
  font-family: 'Montserrat', serif;
}

#instruction {
  background-color: pink;
  padding-bottom: 20px;
  font-family: 'Montserrat', serif;
}

.instructionText {
  background-color: pink;
  font-family: 'Montserrat', serif;
}

.stepText {
  background-color: pink;
  font-family: 'Montserrat', serif;
  max-width: 20px;
}
textarea {
  width: 200px;
  height: 100px;
  font-family: 'Montserrat', serif;
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