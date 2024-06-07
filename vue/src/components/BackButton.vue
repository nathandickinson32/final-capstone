<template>
    <div class="backButtonFormat"> 
        <div class="backButton">
            <button v-on:click="this.$router.back()">Back</button>
        </div>
    </div>
   
  
</template>

<script>
export default {

}
</script>

<style>
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
</style>