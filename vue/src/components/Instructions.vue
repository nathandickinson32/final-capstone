<template>
  <div class="container">
<div><h3>{{ instruction }}</h3></div>

  </div>
</template>

<script>
import InstructionService from '../services/InstructionService'
export default {
    created() {

        InstructionService.getInstructionsByRecipeId(this.$route.params.id).then(
      (response) => {
        if(response.status === 200) {
          this.instruction = response.data;
        }
      }
    );
    },


    data() {
        return {
            instruction : {}
        }
    }

}
</script>

<style>

</style>