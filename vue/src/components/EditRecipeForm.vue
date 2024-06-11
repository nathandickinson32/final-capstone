<template>
  <!-- {{ recipe }}
  {{ instructions }} -->
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

    <div v-for="(instruction, index) in userRecipeDTO.recipeInstructions" :key="instruction.id" class="instructionCard">
      <label :for="'step-' + index">Step {{ userRecipeDTO.recipeInstructions[index].step }}</label>
      <input
      :id="'step-' + index"
      type="text"
      v-model="userRecipeDTO.recipeInstructions[index].step"
      readonly
      />

      <label :for="'instruction-' + index">Instruction {{ userRecipeDTO.recipeInstructions[index].instruction }}</label>
      <textarea
      :id="'instruction-' + index"
      type="text"
      v-model="userRecipeDTO.recipeInstructions[index].instruction"
      ></textarea>
    </div>
<!-- click calls method to add new instruction -->

<div class="form-group">
      <label for="step">Step:</label>
      <input
        id="step"
        type="text"
       
      v-model="userRecipeDTO.recipeInstructions.step"
      />
      <!-- dto.recipe.recipeName -->
    </div>
 
    <div class="form-group">
      <label for="instruction">Instruction:</label>
      <textarea
        id="instruction"
        class="form-control"
        v-model="userRecipeDTO.recipe.instruction"
      ></textarea>
    </div>
    <button v-on:click.prevent="addStep">Add Instruction</button>

    
    <button class="btn btn-submit">Submit</button>
  </form>
</template>

<script>
import RecipeService from '../services/RecipeService';
import InstructionService from '../services/InstructionService';
export default {
    data() {
     return {
        // isLoading: true,
         recipeId : -1,
         recipe : {},
         instructions: [],
        

        userRecipeDTO: {
          recipe:{
            recipeName:"",
            description:""
          },
        recipeInstructions: [
          {
          step:0,
          instruction:""
          
        }
      ]
        }
     }
 },

 created() {
    this.recipeId = this.$route.params.id;

    RecipeService.getRecipeByRecipeId(this.recipeId).then((response) => {
      this.userRecipeDTO.recipe = response.data;
     // this.isLoading = false;
    });

    InstructionService.getInstructionsByRecipeId(this.$route.params.id).then(
      (response) => {
        if(response.status===200) {
          this.userRecipeDTO.recipeInstructions = response.data;
        }
      }
    )
  },
  methods : {

    submitForms() {
      InstructionService.addCustomUserRecipe(this.userRecipeDTO).then(
        (response) => {
          if(response.status === 201) {
            this.$router.push(`/customRecipes`);
            //add instruction
          }
        }
      ).catch((error) => {
          console.error('Error adding recipe:', error);
        });
      
    },

    addInstructions(recipeId) {
      // Prepare instructions data to be added
      const instructionsData = this.userRecipeDTO.recipeInstructions.map((instruction) => ({
        step: instruction.step,
        instruction: instruction.instruction,
        recipeId: recipeId
      }));

      // Add instructions using InstructionService
      InstructionService.addInstructions(instructionsData)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push(`/customRecipes`);
          }
        })
        .catch((error) => {
          console.error('Error adding instructions:', error);
        });
    },

    addStep() {
      const newInstruction = {
        step : '',
        instruction: ''
      };
      this.userRecipeDTO.recipeInstructions.push(newInstruction);
    }
  }
  }



</script>

<style scoped>

</style>