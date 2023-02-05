<template>
  <nav>
    <div>
      <div style="float: left">
        <router-link to="/">
          <img
            id="logo"
            :src="projectlogo"
            alt="noimage"
            style="width: 75px; height: 30px; "
          />
        </router-link> &nbsp;&nbsp;&nbsp;
      </div>
      <div style="float: left; line-height: 35px;">
        <router-link to="/KidBoard">고민게시판</router-link>&nbsp;&nbsp;&nbsp;
        <router-link to="/Announce">HELP DESK</router-link>&nbsp;&nbsp;&nbsp;
        <router-link to="/Calendar">일정관리</router-link>&nbsp;&nbsp;&nbsp;
        <router-link to="/ClientManage">고객관리</router-link>&nbsp;&nbsp;&nbsp;
      </div>
    </div>
    <div v-if="isLogin===true">
      {{ this.$store.state.payload.id }}님 안녕하세요
    </div>
    <div id="navMypage">
      <v-btn @click="logIn" v-if="isLogin===false">Login</v-btn>
      <div v-if="isLogin===true">
        <!-- <h4>{{ this.name }}님</h4> -->
        <v-btn @click="logOut">Logout</v-btn>
        <router-link to="/mypage">마이 페이지</router-link>
        <router-link to="/Checkeditinformation">정보 수정</router-link>
      </div>
    </div>
  </nav>
  <router-view /> 
</template>

<script>
import axios from 'axios'
document.querySelector('body').setAttribute('style',"margin: 0;")
const API_URl = 'http://127.0.0.1:8080'
export default {
  name:'App',
  data(){
    return{
      projectlogo : require('@/assets/projectlogoperpect.png'),
    }
  },

  methods: {
    logOut(){
      axios({
        method: 'get',
        url: `${API_URl}/logout/id`,
        headers: {
          Authorization: `Token ${ this.$store.state.token }`
        }
      })
      .then(
        this.$store.dispatch('logOut')
      )
    },
    logIn(){
      this.$router.push({name:'login'})
    }
  },
  computed:{
    isLogin(){
      return this.$store.getters.isLogin
    }
  }
}
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
  width: 1255px;
  margin: 0 auto;
  padding: 20px;
  display: flex;
  background-color: #F1E7DB;
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

/* nav a.router-link-exact-active {
  color: #579BB1;
} */



</style>
