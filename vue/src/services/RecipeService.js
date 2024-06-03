import axios from 'axios';

export default {

    getRecipes() {
        return axios.get('/get-recipes');
    },

    getRecipesByCategoryId(categoryId) {
        return axios.post(`/get-recipes-by-category-id/${categoryId}`);
    },

    getRecipeById(recipeId) {
        return axios.get(`/get-recipe-by-id/${recipeId}`);
    }

}
