package com.techelevator.dao;

import com.techelevator.model.IdDto;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeInstruction;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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
        String sql = "SELECT * FROM recipe;";

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
    public Recipe addRecipe(Recipe recipeToSave) {

        String sql = "INSERT INTO recipe(recipe_name, description, author_id) VALUES (?, ?, ?) RETURNING recipe_id";

        int newRecipeId = -1;


        try {
            newRecipeId = template.queryForObject(sql, Integer.class,
                    recipeToSave.getRecipeName(),
                    recipeToSave.getDescription(),
                    recipeToSave.getAuthorId()

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
            template.update(sql, recipeToUpdate.getRecipeName(), recipeToUpdate.getDescription(), recipeToUpdate.getAuthorId() );
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
                "WHERE recipe_instructions.recipe_id = ?;";


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
    public RecipeInstruction addRecipeInstruction( RecipeInstruction recipeInstructionToSave){
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

}

