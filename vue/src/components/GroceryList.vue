<template>
  <div>
    {{ ingredients }}
    <h2>To Buy</h2>
    <div v-for='ingredient in ingredients' v-bind:key='ingredient.ingredient_id' class='ingredient'>
      <p>{{ ingredient.ingredient_name }} {{ ingredient.quantity }}</p>
    </div>
  </div>
</template>

<script>
import IngredientsService from '../services/IngredientsService';

export default {
  created() {
    IngredientsService.getIngredientsByUserId().then(
      (response) => {
        if (response.status === 200) {
          this.ingredients = response.data;
        }
      }
    ).catch(
      (error) => {
        if (error.response) {
          if (error.response.status == 404) {
            this.$router.push( {name: 'NotFoundView'} );
          }
        } else if (error.request) {
          // this.$store.commit('SET_NOTIFICATION', `Error getting message. Server could not be reached.`);
        } else {
          // this.$store.commit('SET_NOTIFICATION', `Error getting message. Request could not be created.`);
        }
      }
    )
  },
  data() {
    return {
      ingredients: []
    }
  }
}
</script>

<style>

</style>