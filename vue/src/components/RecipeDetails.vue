<template>
  <div>
    <p>picture ?</p>
    <h1>{{recipe.name}}</h1>
    <div>{{recipe.description}}</div>
    <div>Ingredients List (v-for, probably)</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      recipe : {}
    }
  }
}
</script>

<style>

</style>