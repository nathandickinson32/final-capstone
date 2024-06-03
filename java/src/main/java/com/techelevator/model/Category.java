package com.techelevator.model;

public class Category {

    private int categoryId;
    private String categoryType;
    private String categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
