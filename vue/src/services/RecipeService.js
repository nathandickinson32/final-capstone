import axios from 'axios';

export default {

    getRecipes() {
        return axios.get('/get-recipes');
    },

    getRecipesByCategoryId(categoryId) {
        return axios.post(`/get-recipes-by-category-id/`, categoryId);
    },

    getRecipeByRecipeId(recipeId) {
        return axios.post(`/get-recipe-by-recipe-id/`, recipeId);
    },

    addRecipe(recipe) {
        return axios.post('/add-recipe', recipe);
    }
    
}
