package com.techelevator.dao;
import com.techelevator.model.IdDto;
import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeDao {

    public List<Recipe> getRecipes();
    public List<Recipe> getRecipesByCategoryId(IdDto idDto);

}
