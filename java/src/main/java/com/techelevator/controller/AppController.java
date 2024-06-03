package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.IdDTO;
import com.techelevator.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping(path="get-recipes-by-category-id/{idDTO}", method = RequestMethod.POST)
    public List<Recipe> getRecipesByCategoryId(@PathVariable IdDTO idDTO) {
        return recipeDao.getRecipesByCategoryId(idDTO);
    }
}
