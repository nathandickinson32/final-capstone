<template>
  <div >

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

<style scoped>

.instructionCard{
  margin:30px 15px 15px 0px;
 
  list-style: none;
}

.instructionCard li{
  margin-bottom: 10px;
  background-image: url(../images/knife-and-fork.png);
  background-repeat:no-repeat;
  background-size:35px;
  padding-left: 48px;
  background-position: 0px 10px;
  width: 300px;
  min-height: 48px;
  font-family: 'Montserrat', serif;
}


</style>