<template>
  <nav>
    <div>
      <router-link to="/">홈로고</router-link> &nbsp;&nbsp;&nbsp;
      <router-link to="/KidBoard">고민게시판</router-link>&nbsp;&nbsp;&nbsp;
      <router-link to="/">HELP DESK</router-link>&nbsp;&nbsp;&nbsp;
      <router-link to="/calendar">일정관리</router-link>&nbsp;&nbsp;&nbsp;
      <router-link to="/clientmanage">고객관리</router-link>&nbsp;&nbsp;&nbsp;
    </div>
    
    <div id="navMypage">
      <router-link to="/login">Login</router-link> | 
      <router-link to="/">마이 페이지</router-link>
    </div>
  </nav>
  <router-view /> 
</template>

<script>
document.querySelector('body').setAttribute('style',"margin: 0;")
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #000000;
}

nav {
  /* 반응형 x */
  width: 1200px;
  margin: 0 auto;
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  /* background-color: #F1E7DB; */
  position: relative;
  z-index: 3;
  top: 0;
  left: 0;
  right: 0;
}

nav a {
  font-weight: bold;
  color: #000000;
  text-decoration-line: none;
}

nav a.router-link-exact-active {
  color: #579BB1;
}

figuercaption{
  display:none;
  border:1px solid black;
  width:150px; height:50px;
  text-align:center;
}

</style>
