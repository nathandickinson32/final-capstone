package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
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

    @RequestMapping(path="/get-recipes", method = RequestMethod.GET)
    public List<Recipe> getRecipes() {
        return recipeDao.getRecipes();
    }

    @RequestMapping(path="/get-recipe-by-recipe-id/{id}", method = RequestMethod.GET)
    public Recipe getRecipeByRecipeId(@PathVariable int id) {
        return recipeDao.getRecipe(id);
    }

    @RequestMapping(path="/get-recipes-by-category-id/{id}", method = RequestMethod.GET)
    public List<Recipe> getRecipesByCategoryId(@PathVariable int id ) {
        return recipeDao.getRecipesByCategoryId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/add-custom-recipe", method = RequestMethod.POST)
    public Recipe addRecipe(@RequestBody Recipe recipe, Principal principal) {
        return recipeDao.addRecipe(recipe,  userDao.getUserIdByUsername(principal.getName()));
    }

        @RequestMapping(path="/get-ingredients-by-recipe-id/{id}", method = RequestMethod.GET)
    public List<Ingredient> getIngredientsByRecipeId(@PathVariable int id){
        return ingredientDao.getIngredientsByRecipeId(id);
    }
    @RequestMapping(path="/get-ingredient-by-ingredient-id/{id}", method = RequestMethod.GET)
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



    /* USER FUNCTIONS */

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/save-recipe/{id}", method = RequestMethod.POST)
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

    @RequestMapping(path="/get-authored-recipes", method = RequestMethod.GET)
    public List<Recipe> getRecipesByAuthorId(Principal principal) {
        return accountDao.getAuthoredRecipesByUserId(userDao.getUserIdByUsername(principal.getName()));
    }

    @RequestMapping(path="/update-instruction/{step}/{id}", method = RequestMethod.PUT)
    public void updateInstruction(@PathVariable int step, @PathVariable int id, @RequestBody  RecipeInstruction recipeInstruction) {
        recipeDao.updateInstruction(recipeInstruction, step, id);
    }



    @RequestMapping(path="/get-grocery-list", method = RequestMethod.GET)
    public List<Ingredient> getGroceryList(Principal principal) {
        return accountDao.getIngredientsByUserId(userDao.getUserIdByUsername(principal.getName()));
    }

    @RequestMapping(path="/remove-from-grocery-list", method = RequestMethod.DELETE)
    public boolean removeGroceryListItem(Principal principal) {
        return false;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/new-grocery-list-item/{id}", method = RequestMethod.POST)
    public boolean addGroceryListItem(@PathVariable int id, Principal principal) {
        return accountDao.addIngredientToGroceryList(id, userDao.getUserIdByUsername(principal.getName()));
    }

    @RequestMapping(path="/update-grocery-list-item/{id}", method = RequestMethod.PUT)
    public boolean updateGroceryListItem(@PathVariable int id, Principal principal) {
        return accountDao.addIngredientToGroceryList(id, userDao.getUserIdByUsername(principal.getName()));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/add-instruction", method = RequestMethod.POST)
    public RecipeInstruction addRecipeInstruction(@RequestBody RecipeInstruction recipeInstruction,Principal principal) {
        return recipeDao.addRecipeInstruction(recipeInstruction, userDao.getUserIdByUsername(principal.getName()));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/add-user-recipe", method = RequestMethod.POST)
    public Recipe addRecipeInstruction(@RequestBody UserRecipeDTO userRecipeDTO, Principal principal) {
        Recipe recipe = userRecipeDTO.getRecipe();
        List <RecipeInstruction> recipeInstructions = userRecipeDTO.getRecipeInstructions();
        return recipeDao.addNewUserRecipe(recipe, recipeInstructions, userDao.getUserIdByUsername(principal.getName()));
    }

    @RequestMapping(path="/get-ingredients", method = RequestMethod.GET)
    public List<Ingredient> getAllIngredients() {
        return ingredientDao.getAllIngredients();
    }
}
