import axios from 'axios';

export default {

//   login(user) {
//     return axios.post('/login', user)
//   },

//   register(user) {
//     return axios.post('/register', user)
//   }

    getRecipes() {
        return axios.get('/get-recipes');
    },

    getRecipesByCategoryId(categoryId) {
        return axios.post('/get-recipes-by-category-id', categoryId);
    },

    getRecipeById(recipeId) {
        return axios.post('/get-recipe-by-id', recipeId);
    }

}
