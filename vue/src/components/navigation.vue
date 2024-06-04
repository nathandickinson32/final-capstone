<template>
<div>
<div class="home">
    <h1>MEAL PLANNER OF DOOM</h1>
    <p>Both guests and registered users can access this static display</p>
  </div>
  <nav class="static">

    <a class="home-btn" href="/">Home</a>
  </nav>
</div>
</template>

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

</style>

