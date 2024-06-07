<template>
<div>
<div class="home">
  
    <h1>My Foodie Friend</h1>
    <p>Both guests and registered users can access this static display and navigation</p>
  </div>
  <nav class="static">

    <a class="home-btn" href="/">Home</a>

    <div class="dropdown">
      <label for="categories">Choose a category:</label>
        <select name="categories" id="categories" v-on:change="changeRoute($event)">
          <option value="">-Select-</option> <!-- keep this value empty, select can't be selected-->
          <option value="/category/1">Appetizers</option>
          <option value="/category/2">Breakfast</option>
          <option value="/category/3">Lunch</option>
          <option value="/category/4">Dinner</option>
          <option value="/category/5">Desserts</option>
          <option value="/category/6">High Protein</option>
          <option value="/category/7">Low-Carb</option>
          <option value="/category/8">Vegetarian/Vegan</option>
          <option value="/category/9">Asian</option>
          <option value="/category/10">Italian</option>
          <option value="/category/11">Mexican</option>
          <option value="/category/12">Middle-Eastern</option>
        </select>
      </div>
      <div class="off-screen-menu">
        <ul>
          <a href="/groceryList">
            <li>Grocery List</li>
          </a>
          <a href="/favoriteMeals">
            <li>Favorite Meals</li>
          </a>
          <a href="/favoriteRecipes">
            <li>Favorite Recipes</li>
          </a>
          <a href="/customRecipes">
            <li>Custom Recipes</li>
          </a>
          <a href="">
            <li>Add Recipe</li>
          </a>
        </ul>
      </div>
      <nav>
        <div class="ham-menu" v-show="this.$store.state.token != ''">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </nav>
    

  </nav>
</div>
</template>

<script>
export default {
mounted() {
  const hamMenu = document.querySelector('.ham-menu');
  const offScreenMenu = document.querySelector('.off-screen-menu');
  
  
  
    hamMenu.addEventListener('click', () => {
    hamMenu.classList.toggle('active');
    offScreenMenu.classList.toggle('active');
    })
  
  
  },

  methods : {
    changeRoute(e) {
      this.$router.push(e.target.value);
    },

  
  }
};






</script>


<style>
.home {
  background-image: url("/pexels-monicore-1391487.jpg");
  background-size: cover;
  text-align: center;
  color: white;
  border: 2px solid black;
  border-radius: 25px;
  height: 110px;
  width: 80vw;
  margin-left: auto;
  margin-right: auto;
}

.static {
  display: flex;
  text-align: center;
  border: 2px solid;
  border-radius: 25px;
  border-top: 1px;
  width: 80vw;
  height: 30px;
  margin-left: auto;
  margin-right: auto;
}

.home-btn {
  text-align: center;
  top:50%;
  background-color:rgb(255, 192, 203);
  color: black;
  height: 20px;
  width: 60px;
  border: 1px solid;
  border-color: black;
  border-radius: 10px;
  margin-left: 10px;
  margin-top: auto;
  margin-bottom: auto;
}

.dropdown{
display: flex;
margin-left: auto;
margin-right: auto;
margin-top: auto;
margin-bottom: auto;

}

#categories {
  text-align: left;
  background-color: pink;
}

.off-screen-menu {
  z-index: 100;
  background-color: lavender;
  height: 100vh;
  width: 100%;
  max-width: 450px;
  position: fixed;
  top: 0;
  right: -450px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: left;
  font-size: 2.5rem;
  transition: .7s ease;
}

.off-screen-menu.active{
  right: 0;
}
nav {
  padding: 1rem;
  display: flex;

}

.ham-menu {
  display: flex;
  height: 50px;
  width: 50px;
  margin-right: 10px;
  position: relative;
  top: -25px;
}

.ham-menu span {
  z-index: 100;
  height: 5px;
  width: 100%;
  background-color: pink;
  border-radius: 25px;
  position: absolute;
  top: 50%;
  right: 20%;
  bottom: 50%;
  transform: translate (-50%, -50%);
  transition: .7s ease;

}

.ham-menu span:nth-child(1) {
  top: 25%;
}

.ham-menu span:nth-child(3) {
  top: 75%;
}

.ham-menu.active span:nth-child(1) {
  top: 50%;
  transform: translate (50%, -50%) rotate(90deg);
  
}

.ham-menu.active span:nth-child(2) {
  opacity: 0;
}
.ham-menu.active span:nth-child(3) {
  top: 50%;
  transform: translate(50%, -50%) rotate(90deg);
  
}

</style>



