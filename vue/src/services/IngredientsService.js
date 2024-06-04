import axios from 'axios';

export default {

    getIngredients() {
        return axios.get('/get-ingredients');
    },

    getIngredientsByRecipeId(recipeId) {
        return axios.get(`/get-recipes-by-recipe-id/${recipeId}`);
    },

    getIngredientByIngredientId(ingredientId) {
        return axios.get(`/get-ingredient-by-ingredient-id/${ingredientId}`);
    },

    addIngredient(ingredient) {
        return axios.post('/add-ingredient', ingredient);
    }
}