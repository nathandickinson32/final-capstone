package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
