package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.IdDto;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class AppController {

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

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

        @RequestMapping(path="/get-ingredients-by-recipe-id/{id}", method = RequestMethod.GET)
    public List<Ingredient> getIngredientsByRecipeId(@PathVariable int id){
        return ingredientDao.getIngredientsByRecipeId(id);
    }
    @RequestMapping(path="get-ingredient-by-ingredient-id/{id}", method = RequestMethod.GET)
    public Ingredient getIngredientByIngredientId(@PathVariable int id) {
        return ingredientDao.getIngredientByIngredientId(id);
    }
    @RequestMapping(path="/update-recipe", method = RequestMethod.PUT)
    public void updateRecipe(@RequestBody Recipe recipe) {
        recipeDao.updateRecipe(recipe);
    }

    @RequestMapping(path="/get-recipe-instructions-by-recipe-id/{id}", method = RequestMethod.GET)
    public List<RecipeInstruction> getRecipeInstructionsByRecipeId(@PathVariable int id){
        return recipeDao.getRecipeInstructionsByRecipeId(id);
    }

//    @RequestMapping(path="get-recipe-instruction-by-instruction-id/{id}", method = RequestMethod.GET)
//    public RecipeInstruction getRecipeInstructionByInstructionId(@PathVariable int id) {
//        return recipeDao.getRecipeInstruction(id);
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="add-instruction", method = RequestMethod.POST)
    public RecipeInstruction addRecipeInstruction(@RequestBody RecipeInstruction recipeInstruction) {
        return recipeDao.addRecipeInstruction(recipeInstruction);
    }

    /* USER FUNCTIONS */

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="save-recipe/{id}", method = RequestMethod.POST)
    public boolean addRecipeToLibrary(@PathVariable int id, Principal principal) {
        return accountDao.addRecipeToLibrary(id, userDao.getUserIdByUsername(principal.getName()));
    }

    @RequestMapping(path="/get-library", method = RequestMethod.GET)
    public List<Recipe> getRecipeLibraryByUserId(Principal principal) {
        return accountDao.getRecipeLibraryByUserId(userDao.getUserIdByUsername(principal.getName()));
    }

    @RequestMapping(path="/remove-recipe-from-library/{id}", method = RequestMethod.DELETE)
    public boolean deleteRecipeFromLibraryById(@PathVariable int id, Principal principal) {
        return accountDao.deleteRecipeFromLibraryByRecipeId(id, userDao.getUserIdByUsername(principal.getName()));
    }

}
