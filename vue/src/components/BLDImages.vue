
<template>

    
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <div class="container">
    <div id="breakfast">
    <!--add images and turn them into links -->
    <router-link v-bind:to="{name: 'category', params: {id: 1}}"> 

        <img id="big3" src="/oatmeal2-1-of-1.webp" alt="oatmeal"> 
        <div class="top-center-breakfast">Breakfast</div>

    </router-link>
    
        </div>

        <div id="lunch">
  <router-link v-bind:to="{name: 'category', params: {id: 2}}"> 
        
        <img  id="big3" src="cheeseburger_stock.jpg" alt="cheeseburger"> 
        <div class="top-center-lunch">Lunch</div>
        
             
  </router-link>

        </div>
  

<div id="dinner">
    <router-link v-bind:to="{name: 'category', params: {id: 3}}"> 
        <img id="big3" src="Chicken-Parmesan-1.jpg" alt="chicken parm"> 
        <div class="top-center-dinner">Dinner</div>
    </router-link>

        
</div>
</div>
    

    </template>

    
<style scoped>
.container {
    display: grid;
    /*grid-template-columns: 1fr 1fr 1fr;    */
    margin-top: 10%;
    position: relative;
}
#breakfast {
    position: relative;
    grid-area: breakfast;
    justify-content: center;
}

#lunch {
    position: relative;
    grid-area: lunch;
    justify-content: center;
}

#dinner{
    position: relative;
    grid-area: dinner;
    justify-content: center;
}

#big3 {
    
    float: center;
    border: solid 2px black;
    border-radius: 10px;
    width:  40vh;
    height: 45vh;
    object-fit: cover;
    
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
  transform: translate(0, -50%);
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
  transform: translate(-130%, -50%);
  font-size: 3rem;
  color:black;
}



/* Extra small devices (phones, 600px and down) */
@media only screen and (max-width: 600px) {
  .container {
    image-rendering: auto;
    grid-template-columns: 1fr;
    grid-template-areas:
    "breakfast"
    "lunch"
    "dinner";
  }
}

/* Small devices (portrait tablets and large phones, 600px and up) */
@media only screen and (min-width: 600px) {
  .media {
    width: 100vh;
  }
}

/* Medium devices (landscape tablets, 768px and up) */
@media only screen and (min-width: 768px) {
  .media {
    width: 100vh;
  }
}

/* Large devices (laptops/desktops, 992px and up) */
@media only screen and (min-width: 992px) {
  .media{
    width: 100vh;
  }
}

/* Extra large devices (large laptops and desktops, 1200px and up) */
@media only screen and (min-width: 1200px) {
  .media {
    width: 100vh;
  }
}


</style>


