package com.techelevator.model;

import java.util.List;

public class UserRecipeDTO {

    private Recipe recipe;
    private List<RecipeInstruction> recipeInstructions;
    private List <Integer> ingredientIds;
    private List <Ingredient> ingredients;
    private List<String> ingredientNames;

    public Recipe getRecipe() {
        return recipe;
    }

    public List<Integer> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<Integer> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

    public void setRecipeInstructions(List<RecipeInstruction> recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }


    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List <RecipeInstruction> getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstruction(RecipeInstruction recipeInstruction) {
        this.recipeInstructions.add(recipeInstruction);
    }
}
