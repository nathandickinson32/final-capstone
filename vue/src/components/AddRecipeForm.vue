<template>
  <div class="container">
    <hr id="separator" />
    <hr id="separator" />
    <label id="addInstructionsLabel1">Add Recipe</label>
    <hr id="separator" />

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
      </div>

      <div class="form-group">
        <label for="description">Description:</label>
        <textarea
          id="description"
          class="form-control"
          v-model="userRecipeDTO.recipe.description"
        ></textarea>
      </div>

      <hr id="separator" />
      <hr id="separator" />
      <label id="addInstructionsLabel">Add Instructions</label>
      <hr id="separator" />

      <div
        v-for="(instruction, index) in userRecipeDTO.recipeInstructions"
        :key="index"
        class="instructionCard"
      >
        <label :for="'step-' + index">
          Step {{ index + 1 }}:
        </label>
        <input
          class="stepText"
          :id="'step-' + index"
          type="text"
          v-model="userRecipeDTO.recipeInstructions[index].step"
        />

        <label :for="'instruction-' + index">
          Instruction
        </label>
        <textarea
          class="instructionText"
          :id="'instruction-' + index"
          type="text"
          v-model="userRecipeDTO.recipeInstructions[index].instruction"
        ></textarea>
      </div>

      <div id="buttons">
        <button class="add-btn" v-on:click.prevent="addStep">
          Add Instruction
        </button>
        <hr id="separator" />
        <hr id="separator" />
        <hr id="separator" />
        <label id="addIngredientsLabel">Add Ingredients</label>
        <hr id="separator" />

        <div
          v-for="(ingredient, index) in ingredients"
          :key="ingredient.ingredientId"
          class="ingredientCard"
        >
          <label :for="'ingredientName-' + index">
            {{ ingredients[index].ingredientName }}
          </label>
          <input
            :id="'ingredientName-' + index"
            type="checkbox"
            :value="ingredient.ingredientId"
            v-model="selectedIngredients"
          />
          <input
            :id="'ingredientName-' + index + '-numerator'"
            class="ing-text-box"
            type="text"
            v-model="ingredients[index].numerator"
          />
          <a
            id="show-fraction-button"
            href="#"
            v-on:click.prevent="showFraction = !showFraction"
            ><strong>Show Fraction</strong>
          </a>
          <div v-show="showFraction === true">
            <input
              :id="'ingredientName-' + index + '-denominator'"
              class="ing-text-box"
              type="text"
              v-model="ingredients[index].denominator"
            />
          </div>

          <div class="dropdown">
            <label for="allMeasurements"></label>
            <select
              name="allMeasurements"
              id="allMeasurements"
              v-model="ingredients[index].measurementType"
            >
              <option value="">-Unit of Measurement-</option>
              <option
                v-for="measurement in allMeasurements"
                :key="measurement.measurementType"
                :value="measurement.measurementType"
              >
                {{ measurement.measurementType }}
              </option>
            </select>
          </div>
        </div>

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
            <option value="">-Choose Ingredient to add-</option>
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
          ><strong> Add new ingredient to list </strong>
        </a>
        <div v-show="showForm === true">
          <label for="ingredientText">New Ingredient Name</label>
          <input
            id="ingredientText"
            type="text"
            v-model="newIngredient.ingredientName"
          />
          <br />
          <br />
          <button class="add-btn" v-on:click.prevent="addNewIngredient">
            Add Ingredient
          </button>
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
      newIngredient: {
        ingredientName: "",
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

        ingredients: [],
      
      },
    };
  },

  created() {
    IngredientsService.getAllMeasurements().then((response) => {
      if (response.status === 200) {
        this.allMeasurements = response.data;
      }
    });

    IngredientsService.getAllIngredients().then((response) => {
      if (response.status === 200) {
        this.allIngredients = response.data;
      }
    });
},
methods: {
    submitForms() {
      this.userRecipeDTO.ingredients = this.ingredients;
      RecipeService.addCustomUserRecipe(this.userRecipeDTO)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push(`/customRecipes`);
          }
        })
        .catch((error) => {
          console.error("Error adding recipe:", error);
        });
    },

    addStep() {
      const newInstruction = {
        step: "",
        instruction: "",
      };
      this.userRecipeDTO.recipeInstructions.push(newInstruction);
    },

    addNewIngredient() {
      if (this.newIngredient.ingredientName != "") {
        IngredientsService.addIngredient(this.newIngredient).then(
          (response) => {
            if (response.status === 201) {
              this.newIngredient.ingredientId = response.data;
              this.ingredients.push(this.newIngredient);
              this.userRecipeDTO.ingredientIds.push(
                this.newIngredient.ingredientId
              );
              this.showForm = false;
              this.newIngredient = {
                ingredientName: "",
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
          (ingredient) => ingredient.ingredientId === id
        );
        this.ingredients.splice(index, 1);
        this.userRecipeDTO.ingredients.splice(index, 1);
        let index2 = this.userRecipeDTO.ingredientIds.findIndex(
          (ingredientId) => ingredientId === id
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
  font-family: "LibreBaskerville";
  font-size: 40px;
  text-decoration: underline;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#editInstructionsLabel {
  font-family: "LibreBaskerville";
  font-size: 30px;
  text-decoration: underline;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#editIngredientsLabel {
  font-family: "LibreBaskerville";
  font-size: 30px;
  text-decoration: underline;
}
#separator {
  opacity: 0;
}
#show-form-button {
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
  font-family: "Montserrat", serif;
  font-size: 18px;
  height: 30px;
  width: 170px;
  cursor: pointer;
}
#allIngredients {
  background-color: pink;
  font-family: "Montserrat", serif;
  font-size: 18px;
  border-radius: 10px;
}
h2 {
  font-family: "libreBaskerville", serif;
}
.ingredientCard {
  font-family: "Montserrat", serif;
  font-size: 20px;
    border-radius: 10px;

}
.ing-text-box{
  border-radius: 10px;
}
.ingredientCard input {
  background-color: pink;
  border-radius: 10px;
}
#show-fraction-button {
  color: black;
  margin-left: 15px;
}
#show-form-button {
  color: black;
}
#allMeasurements{
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