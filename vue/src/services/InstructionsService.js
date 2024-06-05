import axios from 'axios';

export default {

    getInstructionsByRecipeId(recipeId) {
        return axios.get(`/get-recipe-instructions-by-recipe-id/${recipeId}`);
    },

    addInstruction(instruction) {
        return axios.post('/add-instruction', instruction);
    }
}