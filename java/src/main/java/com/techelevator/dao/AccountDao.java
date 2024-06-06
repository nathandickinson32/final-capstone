package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;

import java.util.List;

public interface AccountDao {
    public List<Recipe> getRecipeByUserId(int userId); //gets authored recipes
    public List<Recipe> getAuthoredRecipesByUserId(int userId); //gets authored recipes
    public List<Recipe> getRecipeLibraryByUserId(int userId);
    public List<Ingredient> getIngredientsByUserId(int userId); //gets current ingredients
    public boolean addRecipeToLibrary(int id, int userId); //adds recipe to user library
}
