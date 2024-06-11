package com.techelevator.model;

import java.util.List;

public class UserRecipeDTO {

    private Recipe recipe;
    private List<RecipeInstruction> recipeInstructions;

    public Recipe getRecipe() {
        return recipe;
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
