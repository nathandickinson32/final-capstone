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

         updateRecipe(recipe) {
    return axios.put(`/update-recipe/${recipe}`);
  },
     
      updateRecipeInstructions(step, id) {
    return axios.put(`/update-instruction/${step}/${id}`);
  }

}