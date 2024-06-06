
<template>
    <div>
    <!--add images and turn them into links -->
    <router-link v-bind:to="{name: 'category', params: {id: 1}}"> 

        <img id="big3" src="/oatmeal2-1-of-1.webp" alt="oatmeal"> 
        <div class="top-center-breakfast">Breakfast</div>

    </router-link>
    
        </div>

        <div>
  <router-link v-bind:to="{name: 'category', params: {id: 2}}"> 
        
        <img class = "burger" id="big3" src="cheeseburger_stock.jpg" alt="cheeseburger"> 
        <div class="top-center-lunch">Lunch</div>

             
  </router-link>

</div>
  

<div>
    <router-link v-bind:to="{name: 'category', params: {id: 3}}"> 
        <img id="big3" src="Chicken-Parmesan-1.jpg" alt="chicken parm"> 
        <div class="top-center-dinner">Dinner</div>
    </router-link>

        
</div>
    

    </template>

    
<style scoped>
#big3 {
    float: left;
    width:  33%;
    height: 45vh;
    object-fit: cover;
    margin-top: 10%;
    border: solid 1px black;
    border-radius: 10px;
}

.burger{
    
    border-left: none;
    border-right: none;

}

.top-center-breakfast{
    z-index: 1;
  position: absolute;
  border: 1px solid black;
  border-radius: 10px;
  background-color: pink;
  padding: 5px;
  top: 45%;
  left: 17%;
  transform: translate(-50%, -50%);
  font-size: 3rem;
  color:black;
}

.top-center-lunch{
    z-index: 1;
  position: absolute;
  border: 1px solid black;
  border-radius: 10px;
  background-color: pink;
  padding: 5px;
  top: 45%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 3rem;
  color:black;
}
.top-center-dinner{
    z-index: 1;
  position: absolute;
  border: 1px solid black;
  border-radius: 10px;
  background-color: pink;
  padding: 5px;
  top: 45%;
  left: 83%;
  transform: translate(-50%, -50%);
  font-size: 3rem;
  color:black;
}




</style>


