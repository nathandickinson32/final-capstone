
<template>
    <div>
    <!--add images and turn them into links -->
    <a href="/breakfast"> 


        <img src="/oatmeal2-1-of-1.webp" alt="oatmeal"> 
        <div class="top-center-text">Breakfast</div>

    </a>
    
        </div>

        <div>
  <router-link v-bind:to="{name: 'category', params: {id: 2}}"> 
        
        <img class = "burger" src="cheeseburger_stock.jpg" alt="cheeseburger"> 
        <div class="top-center-lunch">Lunch</div>

             
  </router-link>

</div>
  

    <div>
    <a href=""> 
        <img src="Chicken-Parmesan-1.jpg" alt="chicken parm"> 
        <div class="top-center-dinner">Dinner</div>

    </a>
</div>
    

    </template>

    
<style>
img {
    float: left;
    width:  33%;
    height: 45vh;
    object-fit: cover;
    margin-top: 10%;
    border: solid 1px black;
}

.burger{
    
    border-left: none;
    border-right: none;

}

.top-center-text{
  position: absolute;
  top: 45%;
  left: 17%;
  transform: translate(-50%, -50%);
  font-size: 3rem;
  color:hotpink;
}

.top-center-lunch{
  position: absolute;
  top: 45%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 3rem;
  color:hotpink;
}
.top-center-dinner{
  position: absolute;
  top: 45%;
  left: 83%;
  transform: translate(-50%, -50%);
  font-size: 3rem;
  color:hotpink;
}</style>


