package com.techelevator.dao;

import com.techelevator.model.IdDTO;
import com.techelevator.model.Recipe;
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
        recipe.setCategoryId(rowSet.getInt("category_id"));
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
    public List<Recipe> getRecipesByCategoryId(IdDTO idDTO) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipe WHERE category_id = ?;";

        SqlRowSet results = template.queryForRowSet(sql, idDTO.getId());

        while (results.next()) {
            Recipe recipe = mapRowToRecipe(results);
            recipes.add(recipe);
        }
        return recipes;
    }


}
