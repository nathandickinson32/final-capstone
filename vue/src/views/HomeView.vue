<template>
  <div class="home">
    <h1>MEAL PLANNER OF DOOM</h1>
    <p>Both guests and registered users can access HomeView</p>
  </div>
  <nav class="static">

    <button class="home-link">
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>
    </button>
  </nav>
</template>

<script>
export default {
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
  text-align: center;
  border: 2px solid;
  border-radius: 25px;
  width: 80vw;
  height: 30px;
  margin-left: auto;
  margin-right: auto;
}

.home-link {
  display: flex;
  text-align: left;
  padding-left: 10px;
  height: 30px;
  text-decoration: none;
  border: 1px solid;
  margin-top: auto;
  margin-bottom: auto
}

</style>

