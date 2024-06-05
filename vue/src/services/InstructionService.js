import axios from 'axios';

export default {


    getInstructionsByRecipeId(recipeId) {
        return axios.get(`/get-recipe-instructions-by-recipe-id/${recipeId}`);
    },

    addInstructions(instruction) {
        return axios.post('/add-instruction', instruction);
    }
}