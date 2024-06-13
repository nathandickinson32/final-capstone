package com.techelevator.dao;

import com.techelevator.model.IdDto;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeInstruction;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeDao implements RecipeDao {

    private JdbcTemplate template;

    public JdbcRecipeDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    private Recipe mapRowToRecipe(SqlRowSet rowSet) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rowSet.getInt("recipe_id"));
        recipe.setRecipeName(rowSet.getString("recipe_name"));
        recipe.setDescription(rowSet.getString("description"));
        recipe.setAuthorId(rowSet.getInt("author_id"));
        return recipe;
    }

    @Override
    public List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipe ORDER BY recipe_id;";

        SqlRowSet results = template.queryForRowSet(sql);

        while (results.next()) {
            Recipe recipe = mapRowToRecipe(results);
            recipes.add(recipe);
        }
        return recipes;
    }

    @Override
    public Recipe getRecipe(int recipeId) {
        Recipe recipe =null;

        String sql = "SELECT * FROM recipe WHERE recipe_id = ?";

        try {
            SqlRowSet results = template.queryForRowSet(sql, recipeId);

            if(results.next()) {
                recipe = mapRowToRecipe(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return recipe;
    }

    @Override
    public List<Recipe> getRecipesByCategoryId(int categoryId) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipe JOIN recipe_categories ON recipe.recipe_id = recipe_categories.recipe_id " +
        "WHERE  recipe_categories.category_id = ?;";

        SqlRowSet results = template.queryForRowSet(sql, categoryId);

        while (results.next()) {
            Recipe recipe = mapRowToRecipe(results);
            recipes.add(recipe);
        }
        return recipes;
    }

    @Override
    public Recipe addRecipe(Recipe recipeToSave, int id) {

        String sql = "INSERT INTO recipe(recipe_name, description, author_id) VALUES (?, ?, ?) RETURNING recipe_id";

        int newRecipeId = -1;


        try {
            newRecipeId = template.queryForObject(sql, Integer.class,
                    recipeToSave.getRecipeName(),
                    recipeToSave.getDescription(),
                    id
                    );
        } catch(CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }


        return getRecipe(newRecipeId);
    }
    @Override
    public void updateRecipe(Recipe recipeToUpdate) {
        String sql = "UPDATE recipe SET recipe_name=?, description=?, author_id=? WHERE recipe_id = ?";
        try {
            template.update(sql, recipeToUpdate.getRecipeName(), recipeToUpdate.getDescription(), recipeToUpdate.getAuthorId(), recipeToUpdate.getRecipeId());
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

    }

    @Override
    public List<RecipeInstruction> getRecipeInstructionsByRecipeId(int recipeId) {
        List<RecipeInstruction> recipeInstructions = new ArrayList<>();
        String sql = "SELECT * FROM recipe_instructions " +
                "INNER JOIN recipe ON recipe_instructions.recipe_id = recipe.recipe_id " +
                "WHERE recipe_instructions.recipe_id = ? ORDER BY step;";


        try {
            SqlRowSet results = template.queryForRowSet(sql, recipeId);

            while (results.next()) {
                RecipeInstruction recipeInstruction = new RecipeInstruction();
                recipeInstruction.setInstructionsId(results.getInt("instructions_id"));
                recipeInstruction.setStep(results.getInt("step"));
                recipeInstruction.setInstruction(results.getString("instruction"));
                recipeInstruction.setRecipeId(results.getInt("recipe_id"));
                recipeInstructions.add(recipeInstruction);

            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return recipeInstructions;
    }

    @Override
    public RecipeInstruction getRecipeInstruction(int instructionsId) {
        RecipeInstruction recipeInstruction = null;

        String sql = "SELECT * FROM recipe_instructions WHERE instructions_id = ?";

        try {
            SqlRowSet results = template.queryForRowSet(sql, instructionsId);

            if(results.next()) {
                recipeInstruction = new RecipeInstruction();
                recipeInstruction.setInstructionsId(results.getInt("instructions_id"));
                recipeInstruction.setStep(results.getInt("step"));
                recipeInstruction.setInstruction(results.getString("instruction"));
                recipeInstruction.setRecipeId(results.getInt("recipe_id"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return recipeInstruction;
    }

    @Override
    public void updateInstruction(RecipeInstruction instructionToUpdate, int step, int id) {
        String sql = "UPDATE recipe_instructions SET instruction=? WHERE step = ? AND recipe_id = ?";
        try {
            template.update(sql, instructionToUpdate.getInstruction(),step, id );
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
    }

    @Override
    public RecipeInstruction addRecipeInstruction(RecipeInstruction recipeInstructionToSave, int id){
        String sql = "INSERT INTO recipe_instructions(step, instruction, recipe_id) VALUES (?, ?, ?) RETURNING instructions_id";

        int newInstructionsId = -1;


        try {
            newInstructionsId = template.queryForObject(sql, Integer.class,
                    recipeInstructionToSave.getStep(),
                    recipeInstructionToSave.getInstruction(),
                    recipeInstructionToSave.getRecipeId()

            );
        } catch(CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }


        return getRecipeInstruction(newInstructionsId);
    }


    @Override
    public Recipe addNewUserRecipe(Recipe recipeToSave, List <RecipeInstruction> recipeInstructionsToSave, List <Integer> ingredientIds, List<Ingredient> ingredients, int id) {

        String sql = "INSERT INTO recipe(recipe_name, description, author_id) VALUES (?, ?, ?) RETURNING recipe_id";

        int newRecipeId = -1;


        try {
            newRecipeId = template.queryForObject(sql, Integer.class,
                    recipeToSave.getRecipeName(),
                    recipeToSave.getDescription(),
                    id
            );
        } catch(CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }


        String sql2 = "INSERT INTO recipe_instructions(step, instruction, recipe_id) VALUES (?, ?, ?) RETURNING instructions_id";

        int newInstructionsId = -1;

        for(RecipeInstruction recipeInstruction : recipeInstructionsToSave) {
            try {
                newInstructionsId = template.queryForObject(sql2, Integer.class,
                        recipeInstruction.getStep(),
                        recipeInstruction.getInstruction(),
                        newRecipeId

                );
            } catch (CannotGetJdbcConnectionException e) {
                System.out.println("Problem connecting");
            } catch (DataIntegrityViolationException e) {
                System.out.println("Data problems");
            }
        }

<<<<<<< HEAD
        String sql3 = "INSERT INTO recipe_ingredients(recipe_id, ingredient_id, numerator,denominator,measurement_type) VALUES (?, ?, ?, ?, ?) RETURNING ingredient_id";
=======
        String sql3 = "INSERT INTO recipe_ingredients(recipe_id, ingredient_id, numerator, denominator,) VALUES (?, ?) RETURNING ingredient_id";
>>>>>>> dc1389c5f7f5b141a2859347fad72ac7c0b282c5
            int returnId=-1;

        for( Ingredient ingredient: ingredients)
            try {

              returnId= template.queryForObject(sql3, Integer.class,


                        newRecipeId,
                      ingredient.getIngredientId(),
                      ingredient.getNumerator(),
                      ingredient.getDenominator(),
                      ingredient.getMeasurementType()


                );
            } catch (CannotGetJdbcConnectionException e) {
                System.out.println("Problem connecting");
            } catch (DataIntegrityViolationException e) {
                System.out.println("Data problems");
            }





        return getRecipe(newRecipeId);



    }



}

