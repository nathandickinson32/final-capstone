<template>
  <div class="container">

<ul v-for="instruction in instructions" v-bind:key="instruction.id" class ='instructionCard'>
<li>{{instruction.step  }} <br> {{ instruction.instruction }}</li>
</ul>

  </div>
</template>

<script>
import InstructionService from '../services/InstructionService'
export default {
    created() {

        InstructionService.getInstructionsByRecipeId(this.$route.params.id).then(
      (response) => {
        if(response.status === 200) {
          this.instructions = response.data;
        }
      }
    );
    },


    data() {
        return {
            instructions : []
        }
    }

}
</script>

<style>

</style>