package com.techelevator.model;

import java.util.List;

public class Recipe_IngredientDTO {
    private int recipeId;

    private List<Integer> ingredientIds;



    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public List<Integer> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<Integer> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }
}
