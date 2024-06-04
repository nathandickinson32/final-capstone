import axios from 'axios';

export default {

    getIngredients() {
        return axios.get('/get-ingredients');
    },

    getIngredientsByRecipeId(recipeId) {
        return axios.post(`/get-recipes-by-recipe-id/`, recipeId);
    },

    getIngredientByIngredientId(ingredientId) {
        return axios.post(`/get-ingredient-by-ingredient-id/`, ingredientId);
    },

    addIngredient(ingredient) {
        return axios.post('/add-ingredient', ingredient);
    }
    
}