<template>
  <div class='container'>
    <h2 id='grocery-list-header'>To Buy</h2>
    <div class='grocery-list'>
      <table id='grocery-tbl'>
        <thead>
          <tr>
            <th id='th1'>&nbsp;</th>
            <th id='th2'><h3>Ingredient</h3></th>
            <th id='th3'><h3>Quantity to Buy</h3></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for='ingredient in groceryList' v-bind:key='ingredient.ingredient_id' class='ingredient-row'>
            <td class='checkbox-col'>
              <input type='checkbox' />
            </td>
            <td>
              <h4 class='ingredientName'>{{ ingredient.ingredientName }}</h4>
            </td>
            <td>
              {{ ingredient.quantity }}
            </td>
          </tr>
        </tbody>
      </table>
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
          this.groceryList = response.data;
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
    );
  },
  data() {
    return {
      groceryList: []
    }
  }
}
</script>

<style>

#grocery-list-header {
  text-decoration: underline;
  margin-top: 0;
}

table#grocery-tbl {
  margin-bottom: 20px;
  border-spacing: 0px;
}

th#th2 {
  border: 1px solid black;
  padding-left: 5px;
  padding-right: 5px;
}

#th3 {
  border-right: 1px solid black;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
  padding-right: 3px;
  padding-left: 5px;
}

td {
  padding: 10px;
}

h4.ingredientName {
  margin: 2px;
}


</style>