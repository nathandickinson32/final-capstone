package com.techelevator.model;

public class Ingredient {

    private int ingredientId;
    private String ingredientName;
    private int quantity;

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}