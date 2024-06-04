package com.techelevator.model;

public class Ingredient {

    private int ingredientId;
    private String ingredientName;


    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
}