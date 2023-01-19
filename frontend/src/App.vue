<template>
  <nav>
    <div id="navCategory">
      <router-link to="/">홈로고</router-link> &nbsp;&nbsp;&nbsp;
      <router-link to="/">고민게시판</router-link>&nbsp;&nbsp;&nbsp;
      <router-link to="/">HELP DESK</router-link>
    </div>
    <div id="navMypage">
      <router-link to="/login">Login</router-link>
      <router-link to="/">마이 페이지</router-link>
    </div>
  </nav>
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #000000;
}

nav {
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #F1E7DB;
  position: fixed;
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

</style>
