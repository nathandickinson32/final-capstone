
<template>

    
   <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <div class="container">
    <div id="breakfast">
    <!--add images and turn them into links -->
    <router-link v-bind:to="{name: 'category', params: {id: 2}}"> 

        <img class="big3" src="/oatmeal2-1-of-1.webp" alt="oatmeal"> 
        <div class="btn-float top-center-breakfast">
            <p>Breakfast</p>
        </div>

    </router-link>
    
        </div>

        <div id="lunch">
  <router-link v-bind:to="{name: 'category', params: {id: 3}}"> 
        
        <img  class="big3" src="salad.webp" alt="cheeseburger"> 
        <div class="btn-float top-center-lunch">
            <p>Lunch</p>
        </div>
        
             
  </router-link>

        </div>
  
<div id="dinner">
    <router-link v-bind:to="{name: 'category', params: {id: 4}}"> 
        <img class="big3" src="Chicken-Parmesan-1.jpg" alt="chicken parm"> 
        <div class="btn-float top-center-dinner">
            <p>Dinner</p>
        </div>
    </router-link>

        
</div>
</div>

    

    </template>

    
<style scoped>
.container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: "breakfast lunch dinner";
    margin-top: 10%;
    position: relative;
}

#breakfast {
    /* position: absolute; */
    grid-area: breakfast;
    justify-content: center;
}


#lunch {
    /* position: absolute; */
    grid-area: lunch;
    justify-content: center;
}

#dinner{
    /* position: absolute; */
    grid-area: dinner;
    justify-content: center;
}

.big3 {
    
    float: center;
    border: solid 2px black;
    border-radius: 10px;
    width:  95%;
    height: 45vh;
    object-fit: cover;
    
    
}

.btn-float {
    z-index: 1;
    position: relative;
    top: -10vh;
    margin:0;
    width: 100%;
}

.btn-float > p {
    border: 1px solid black;
    border-radius: 10px;
    background-color: pink;
    padding: 5px;
    font-size: 2rem; /*min(5vw, 3rem);*/
    color:black;
    width: 30px;
    min-width: fit-content;
    margin:0 auto;
}

a {
    text-decoration: none;
}
p {
  font-family: 'LibreBaskerville', serif;
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


