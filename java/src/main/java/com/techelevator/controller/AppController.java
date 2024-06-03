package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.IdDto;
import com.techelevator.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(path="get-recipes-by-category-id/{idDto}", method = RequestMethod.GET)
    public List<Recipe> getRecipesByCategoryId(@PathVariable("id") IdDto idDto) {
        return recipeDao.getRecipesByCategoryId(idDto);
    }
}
