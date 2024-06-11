package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate template;

    public JdbcAccountDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }
    @Override
    public List<Recipe> getRecipeByUserId(int userId) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT recipe_id, recipe_name, description FROM recipe " +
                "JOIN recipe_users ON recipe_users.recipe_id = recipe.recipe_id " +
                "WHERE user_id = ?";

        try {
            SqlRowSet results = template.queryForRowSet(sql, userId);

            while (results.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipeId(results.getInt("recipe_id"));
                recipe.setRecipeName(results.getString("recipe_name"));
                recipe.setDescription(results.getString("description"));
                recipes.add(recipe);
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return recipes;
    }

    @Override
    public List<Recipe> getAuthoredRecipesByUserId(int userId) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT recipe_id, recipe_name, description FROM recipe " +
                "WHERE author_id = ?";

        try {
            SqlRowSet results = template.queryForRowSet(sql, userId);

            while (results.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipeId(results.getInt("recipe_id"));
                recipe.setRecipeName(results.getString("recipe_name"));
                recipe.setDescription(results.getString("description"));
                recipes.add(recipe);
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return recipes;
    }

    @Override
    public List<Recipe> getRecipeLibraryByUserId(int userId) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT recipe_id, recipe_name, description FROM recipe " +
                "WHERE recipe_id IN (SELECT recipe_id FROM recipe_users WHERE user_id = ?);";

        try {
            SqlRowSet results = template.queryForRowSet(sql, userId);

            while (results.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipeId(results.getInt("recipe_id"));
                recipe.setRecipeName(results.getString("recipe_name"));
                recipe.setDescription(results.getString("description"));
                recipes.add(recipe);
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return recipes;
    }

    @Override
    public List<Ingredient> getIngredientsByUserId(int userId) {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT ingredients_users.ingredient_id, ingredient_name, quantity " +
                "FROM ingredients_users " +
                "JOIN ingredients ON ingredients_users.ingredient_id = ingredients.ingredient_id " +
                "WHERE user_id = ?;";
        try {
            SqlRowSet results = template.queryForRowSet(sql, userId);

            while(results.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setIngredientId(results.getInt("ingredient_id"));
                ingredient.setIngredientName(results.getString("ingredient_name"));
                ingredient.setQuantity(results.getInt("quantity"));
                ingredients.add(ingredient);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return ingredients;
    }

    @Override
    public boolean addRecipeToLibrary(int id, int userId) {
        int receiptId = -1;
        String sql = "INSERT INTO recipe_users (recipe_id, user_id) " +
                "VALUES (?,?) RETURNING recipe_id;";

        try {

            receiptId = template.queryForObject(sql, Integer.class, id, userId);

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return receiptId != -1;
    }

    public boolean deleteRecipeFromLibraryByRecipeId(int id, int userId){
        int receiptId = -1;
        String sql = "DELETE FROM recipe_users WHERE recipe_id = ? AND user_id =?;";

        try {

            receiptId = template.update(sql, id, userId);

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return receiptId != -1;
    }

    @Override
    public boolean addIngredientToGroceryList(int ing_id, int userId) {
        int receiptId = -1;
        String sql = "INSERT INTO ingredients_users VALUES (?, ?, 1) RETURNING ingredient_id;";

        try {

            receiptId = template.queryForObject(sql, Integer.class, ing_id, userId);

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return receiptId != -1;
    }

    @Override
    public boolean updateIngredientInGroceryList(int ing_id, int userId) {
        int receiptId = -1;
        String sql = "UPDATE ingredients_users SET quantity = quantity + 1 " +
                "WHERE ingredient_id = ? AND user_id = ?;";

        try {

            receiptId = template.update(sql, ing_id, userId);

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return receiptId != -1;
    }

    @Override
    public boolean removeIngredientInGroceryList(int ing_id, int userId) {
        int receiptId = -1;
        String sql = "DELETE FROM ingredients_users WHERE ingredient_id = ? AND user_id = ?;";


        try {

            receiptId = template.update(sql, ing_id, userId);

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return receiptId != -1;
    }
}
