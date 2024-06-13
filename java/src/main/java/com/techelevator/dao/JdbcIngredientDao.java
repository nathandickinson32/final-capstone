package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeInstruction;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcIngredientDao implements IngredientDao {

    private JdbcTemplate template;

    public JdbcIngredientDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public List<Ingredient> getIngredientsByRecipeId(int recipeId) {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT ingredients.ingredient_id, ingredient_name, numerator, denominator, measurement_type FROM ingredients " +
                "JOIN recipe_ingredients ON ingredients.ingredient_id = recipe_ingredients.ingredient_id " +
                "WHERE  recipe_ingredients.recipe_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, recipeId);

            while (results.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setIngredientId(results.getInt("ingredient_id"));
                ingredient.setIngredientName(results.getString("ingredient_name"));
                ingredient.setNumerator(results.getInt("numerator"));

                int denominator = results.getInt("denominator");
                if (denominator > 0) {
                    ingredient.setDenominator(denominator);
                }

                ingredient.setMeasurementType(results.getString("measurement_type"));
                ingredients.add(ingredient);

            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return ingredients;
    }

    public Ingredient getIngredientByIngredientId(int ingredientId) {
        Ingredient ingredient = new Ingredient();
        String sql = "SELECT * FROM ingredients WHERE ingredient_id = ?;";


        try {
            SqlRowSet results = template.queryForRowSet(sql, ingredientId);

            if (results.next()) {

                ingredient.setIngredientId(results.getInt("ingredient_id"));
                ingredient.setIngredientName(results.getString("ingredient_name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return ingredient;
    }

    public List<Ingredient> getAllIngredients() {

        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredients ORDER BY ingredient_name;";

        SqlRowSet results = template.queryForRowSet(sql);

        while (results.next()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientId(results.getInt("ingredient_id"));
            ingredient.setIngredientName(results.getString("ingredient_name"));

            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public boolean deleteIngredientsFromRecipeIngredients(int recipeId, List<Integer> ingredientIds) {
        String sql = "DELETE FROM recipe_ingredients WHERE recipe_id = ? AND ingredient_id = ? ";

        try {

            for (Integer ingredientId : ingredientIds) {
                template.update(sql, recipeId, ingredientId);

            }

            return true;

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return false;
    }

}
