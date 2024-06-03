package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcRecipeDao implements RecipeDao{

private JdbcTemplate template;

    public JdbcRecipeDao(DataSource ds)  {
       template = new JdbcTemplate(ds);
    }

    @Override
    public List<Recipe> getRecipes() {
        return null;
    }


}
