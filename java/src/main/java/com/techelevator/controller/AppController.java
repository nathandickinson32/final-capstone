package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.IdDto;
import com.techelevator.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@PreAuthorize("isAuthenticated()")
public class AppController {

    @Autowired
    private RecipeDao recipeDao;

    @RequestMapping(path="get-recipes", method = RequestMethod.GET)
    public List<Recipe> getRecipes() {
        return recipeDao.getRecipes();
    }

    @RequestMapping(path="get-recipe-by-recipe-id", method = RequestMethod.POST)
    public Recipe getRecipeByRecipeId(@RequestBody IdDto idDto) {
        return recipeDao.getRecipe(idDto);
    }

    @RequestMapping(path="get-recipes-by-category-id", method = RequestMethod.POST)
    public List<Recipe> getRecipesByCategoryId(@RequestBody IdDto idDto) {
        return recipeDao.getRecipesByCategoryId(idDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="add-recipe", method = RequestMethod.POST)
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeDao.addRecipe(recipe);
    }
}
