package com.techelevator.dao;

import com.techelevator.model.IdDto;
import com.techelevator.model.Recipe;
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
        recipe.setInstructions(rowSet.getString("instructions"));
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

        String sql = "INSERT INTO recipe(recipe_name, description, instructions) VALUES (?, ?, ?) RETURNING recipe_id";

        int newRecipeId = -1;


        try {
            newRecipeId = template.queryForObject(sql, Integer.class,
                    recipeToSave.getRecipeName(),
                    recipeToSave.getDescription(),
                    recipeToSave.getInstructions()

                    );
        } catch(CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }


        return getRecipe(newRecipeId);
    }


}
