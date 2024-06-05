package com.techelevator.model;

public class RecipeInstruction {
    private int instructionsId;
    private int step;
    private String instruction;
    private int recipeId;

    public int getInstructionsId() {
        return instructionsId;
    }

    public void setInstructionsId(int instructionsId) {
        this.instructionsId = instructionsId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
