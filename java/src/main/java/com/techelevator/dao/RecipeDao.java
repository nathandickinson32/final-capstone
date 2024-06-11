package com.techelevator.dao;
import com.techelevator.model.IdDto;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeInstruction;

import java.util.List;

public interface RecipeDao {

    public List<Recipe> getRecipes();
    public Recipe getRecipe(int recipeId);
    public List<Recipe> getRecipesByCategoryId(int categoryId);
    public Recipe addRecipe( Recipe recipe, int id);
    public void updateRecipe( Recipe recipeToUpdate);
    public List<RecipeInstruction> getRecipeInstructionsByRecipeId(int recipeId);
    public RecipeInstruction addRecipeInstruction( RecipeInstruction recipeInstruction, int id);
    public RecipeInstruction getRecipeInstruction(int instructionsId);
    public void updateInstruction( RecipeInstruction instructionToUpdate, int step, int id);
    public Recipe addNewUserRecipe(Recipe recipeToSave,List<RecipeInstruction> recipeInstructionsToSave, int id);

}
