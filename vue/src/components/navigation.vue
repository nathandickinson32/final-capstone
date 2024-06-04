<template>
<div>
<div class="home">
    <h1>MEAL PLANNER OF DOOM</h1>
    <p>Both guests and registered users can access this static display</p>
  </div>
  <nav class="static">

    <a class="home-btn" href="/">Home</a>

    <div class="dropdown">
      <label for="categories">Choose a category:</label>
        <select name="categories" id="categories">
          <option>-Select-</option>
          <option value="">Breakfast</option>
          <option value="">Lunch</option>
          <option value="">Dinner</option>
          <option value="">High Protein</option>
          <option value="">Low-Carb</option>
          <option value="">Vegetarian/Vegan</option>
          <option value="">Asian</option>
          <option value="">Mexican</option>
          <option value="">Italian</option>
          <option value="">Middle-Eastern</option>
        </select>
      </div>
      <div class="off-screen-menu">
        <ul>
          <li>Favorite Meals</li>
          <li>Favorite Recipes</li>
          <li>Grocery List</li>
          <li>Custom Recipes</li>
        </ul>
      </div>
      <nav>
        <div class="ham-menu">
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
created() {
const hamMenu = document.querySelector('.ham-menu');
const offScreenMenu = document.querySelector('.off-screen-menu');
if(hamMenu){
hamMenu.addEventListener('click', () => {
  hamMenu.classList.toggle('active');
  offScreenMenu.classList.toggle('active');
})
}
}
};


</script>


<style>
.home {
  text-align: center;
  border: 2px solid;
  border-radius: 25px;
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
  background-color:pink;
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
  background-color: black;
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
  text-align: center;
  font-size: 3rem;
  transition: .3s ease;
}

.off-screen-menu.active{
  right: 0;
}
nav {
  padding: 1rem;
  display: flex;

}

.ham-menu {
  height: 50px;
  width: 50px;
  margin-left: auto;
  position: relative;
}

.ham-menu span {
  height: 5px;
  width: 100%;
  background-color: pink;
  border-radius: 25px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate (-50%, -50%);
  transition: .3s ease;
}

.ham-menu span:nth-child(1) {
  top: 25%;
}

.ham-menu span:nth-child(3) {
  top: 75%;
}

.ham-menu.active span:nth-child(1) {
  top: 50%;
  transform: translate (-50%, -50%) rotate(45deg);
}

.ham-menu.active span:nth-child(2) {
  opacity: 0;
}
.ham-menu.active span:nth-child(3) {
  top: 50%;
  transform: translate(-50%, 50%) rotate(-45deg);
}

</style>



