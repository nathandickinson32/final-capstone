<template>
  <div class='container'>
    <!-- {{ toDeleteList }}<br>
    {{ groceryList }} -->
    <h2 id='grocery-list-header'>To Buy</h2>
    <div class='grocery-list'>
      <button class='del-btn top-btn' v-on:click='deleteSelected'>Delete Selected</button>
      <table id='grocery-tbl'>
        <thead>
          <tr>
            <th id='th1'>&nbsp;</th>
            <th id='th2'><h3>Ingredient</h3></th>
            <th id='th3'><h3>Quantity to Buy</h3></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for='ingredient in groceryList' v-bind:key='ingredient.ingredientId' class='ingredient-row'>
            <td class='checkbox-col'>
              <input type='checkbox' 
                v-bind:id='ingredient.ingredientId' 
                v-bind:value='ingredient.ingredientId' 
                v-bind:checked='toDeleteList.includes(ingredient.ingredientId)' 
                v-on:click='toggleSelected(ingredient.ingredientId)'/>
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
      <button class='del-btn bottom-btn' v-on:click='deleteSelected'>Delete Selected</button>
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
      groceryList: [],
      toDeleteList: []
    }
  },
  methods: {
    toggleSelected(ingredientId) {
      if (this.toDeleteList.includes(ingredientId)) {
        this.toDeleteList = this.toDeleteList.filter(
          (id) => {
            return id != ingredientId;
          }
        );
      } else {
        this.toDeleteList.push(ingredientId);
      }
    },
    deleteSelected() {
      this.toDeleteList.forEach(
        (item) => {
          console.log(item);
          IngredientsService.deleteGroceryListItem(item).then(
            (response) => {
              if (response.status === 200) {
                // this.$router.push({name: 'favoriteRecipes'});
                console.log("deleted");
              }
            }
          );
        }
      );
    }
  }
}
</script>

<style scoped>



#grocery-list-header {
  text-decoration: underline;
  margin-top: 0;
  font-family: 'LibreBaskerville', serif;
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

thead th {
  
  font-family: 'LibreBaskerville', serif;

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
  font-family: 'Montserrat', serif;
}

.del-btn {
  background-color:rgb(255, 192, 203);
  color: black;
  border: 1px solid;
  border-color: black;
  border-radius: 10px;
}

.top-btn {
  margin-bottom: 15px;
}


</style>