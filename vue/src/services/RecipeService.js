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

   

    // REGISTERED USER FUNCTIONS //

    getRecipeLibraryByUser() {
        return axios.get('/get-library');
    },

    getRecipeByAuthorId() {
        return axios.get('/get-authored-recipes');
    },

    addRecipeToLibrary(id) {
        return axios.post(`/save-recipe/${id}`);
    },
     
    deleteRecipeFromLibrary(id) {
        return axios.delete(`/remove-recipe-from-library/${id}`);
    },

         addCustomRecipe(recipe) {
    return axios.post(`/add-custom-recipe`, recipe);
  },

  addCustomUserRecipe(userRecipeDTO) {
    return axios.post(`/add-user-recipe`, userRecipeDTO);
  }
     
 


}