<template>
    <div class="backButtonFormat"> 
        <div class="backButton">
            <button class='back-button' v-on:click="this.$router.back()">Back</button>
        </div>
    </div>
   
  
</template>

<script>
export default {

}
</script>

<style scoped>
.backButton {
    background-image: url(../images/back.png);
}

.backButtonFormat{
    width: 50px;
  height: 50px;
  border: none;
  margin: 0;
  background-repeat: no-repeat;
  background-size: 40px 40px;
}

.back-button {
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
  font-family: 'Montserrat', serif;
}
</style>