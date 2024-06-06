import axios from 'axios';

export default {

    getRecipes() {
        return axios.get('/get-recipes');
    },

    getRecipesByCategoryId(categoryId) {
        return axios.get(`/get-recipes-by-category-id/${categoryId}`);
    },

    getRecipeByRecipeId(id) {
        return axios.get(`/get-recipe-by-recipe-id/${id}`);
    },

    addRecipe(recipe) {
        return axios.post('/add-recipe', recipe);
    },

    // REGISTERED USER FUNCTIONS //

    getRecipeLibraryByUser() {
        return axios.get('/get-library');
    }

}
