package com.techelevator.dao;
import com.techelevator.model.IdDto;
import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeDao {

    public List<Recipe> getRecipes();
    public Recipe getRecipe(int recipeId);
    public List<Recipe> getRecipesByCategoryId(int categoryId);
    public Recipe addRecipe( Recipe recipe);

}
