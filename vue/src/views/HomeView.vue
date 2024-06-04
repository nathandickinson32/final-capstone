<template>
  <div>
    <Navigation />
  </div>
  <div>
    <!--add images and turn them into links -->
    <a href=""> 
        <img src=
        "omelet.jpg"
             alt=""> 
    </a>
 
    <a href=""> 
        <img src=
        "cheeseburger_stock.jpg"
             alt=""> 
    </a>

    <a href=""> 
        <img src="Chicken-Parmesan-1.jpg"
             alt=""> 
    </a>
  </div>
  
</template>

<script>
import Navigation from '../components/navigation.vue';

export default {
    components: { Navigation }
};
</script>

<style>
img{
    float: left;
    width:  65vh;
    height: 65vh;
    object-fit: cover;
    
    
}</style>






