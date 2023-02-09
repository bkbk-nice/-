<template>
  <div id="app">
    <!--    <nav>-->
    <!--      <router-link to="/">Home</router-link> |-->
    <!--      <router-link to="/about">About</router-link>|-->
    <!--      <router-link to="/admin">Admin</router-link>-->
    <!--    </nav>-->
<!--    <div id='building'>-->
<!--      <div>-->
<!--        <button @click="goHome">home</button>-->
<!--        <button @click="goAdmin">login</button>-->
<!--       <button @click="goAdminPlus">plus</button>-->

<!--      </div>-->
      <router-view/>

<!--    </div>-->
  </div>
</template>


<style>
  #app{

  }
</style>
<!--<script>-->

<!--let that;-->
<!--export default {-->
<!--  name: 'App',-->
<!--  data() {-->
<!--    return {-->

<!--    }-->

<!--  },-->

<!--  methods: {-->
<!--    goHome() {-->
<!--      this.$router.push({path: '/'})-->
<!--    },-->
<!--    goAdmin() {-->
<!--      this.$router.push({path: '/admin'})-->
<!--    },-->
<!--    goAdminPlus() {-->
<!--      this.$router.push({path: '/adminPlus'})-->
<!--    },-->


<!--  }-->
<!--}-->
<!--</script>-->


<!--<style lang="less">-->
<!--#app {-->
<!--  font-family: Avenir, Helvetica, Arial, sans-serif;-->
<!--  -webkit-font-smoothing: antialiased;-->
<!--  -moz-osx-font-smoothing: grayscale;-->
<!--  text-align: center;-->
<!--  color: #2c3e50;-->
<!--  margin-top: 0px;-->
<!--  body{-->
<!--    margin:0;-->
<!--    padding:0;-->
<!--    border:0-->
<!--  }-->
<!--  #building{-->
<!--    background:url("./assets/back.jpg");-->
<!--    width:100%;-->
<!--    height:100%;-->
<!--    position:fixed;-->
<!--    background-size:100% 100%;-->
<!--  }-->
<!--}-->
<!--</style>-->



