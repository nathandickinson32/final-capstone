import axios from 'axios';

export default {

    getRecipes() {
        return axios.get('/get-recipes');
    },

    getRecipesByCategoryId(categoryId) {
        return axios.post(`/get-recipes-by-category-id/`, categoryId);
    },

    getRecipeByRecipeId(id) {
        return axios.get(`/get-recipe-by-recipe-id/${id}`);
    },

    addRecipe(recipe) {
        return axios.post('/add-recipe', recipe);
    }

}
