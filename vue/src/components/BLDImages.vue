
<template>
    <div>
    <!--add images and turn them into links -->
    <a href="/breakfast"> 
        <img src=
        "omelet.jpg"
             alt=""> 
    </a>
 
  <router-link v-bind:to="{name: 'category', params: {id: 2}}"> 
        <img src=
        "cheeseburger_stock.jpg"
             alt=""> 
  </router-link>

    <a href=""> 
        <img src="Chicken-Parmesan-1.jpg"
             alt=""> 
    </a>
     </div>

    </template>

    
<style>
img {
    float: left;
    width:  33%;
    height: 45vh;
    object-fit: cover;
    margin-top: 10px;
    border: solid 1px black;
    
    
}</style>
