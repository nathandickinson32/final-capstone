package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;

import java.util.List;

public interface IngredientDao {
    public List<Ingredient> getIngredientsByRecipeId(int recipeId);
    public Ingredient getIngredientByIngredientId(int ingredientId);
    public List<Ingredient> getAllIngredients();


}
