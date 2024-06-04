package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.IdDto;
import com.techelevator.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class AppController {

    @Autowired
    private RecipeDao recipeDao;

    @RequestMapping(path="get-recipes", method = RequestMethod.GET)
    public List<Recipe> getRecipes() {
        return recipeDao.getRecipes();
    }

    @RequestMapping(path="get-recipe-by-recipe-id/{id}", method = RequestMethod.GET)
    public Recipe getRecipeByRecipeId(@PathVariable int id) {
        return recipeDao.getRecipe(id);
    }

    @RequestMapping(path="get-recipes-by-category-id/{id}", method = RequestMethod.GET)
    public List<Recipe> getRecipesByCategoryId(@PathVariable int id ) {
        return recipeDao.getRecipesByCategoryId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="add-recipe", method = RequestMethod.POST)
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeDao.addRecipe(recipe);
    }
}
