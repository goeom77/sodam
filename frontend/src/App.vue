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
        <v-btn class="text-none" stacked style="background-color: white;">
          <v-badge floating :content="newNotiCount" color="error" @click="alarm">
            <v-icon>mdi-bell-outline</v-icon>
          </v-badge>
        </v-btn>
        <v-btn @click="logOut">Logout</v-btn>
        <router-link to="/mypage">마이 페이지</router-link>
      </div>
    </div>
  </nav>
  <router-view /> 
</template>

<script>
import axios from 'axios'
import { EventSourcePolyfill } from "event-source-polyfill";

document.querySelector('body').setAttribute('style',"margin: 0;")
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name:'App',
  data(){
    return{
      projectlogo : require('../src/assets/images/projectlogoperpect.png'),
      newNotiCount : this.$store.state.newNotiCount
    }
  },
  component: {
    // Spinner
  },
  methods: {
    logOut(){
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/logout/id`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(
        this.$store.dispatch('logOut')
      )
    },
    logIn(){
      this.$router.push({name:'login'})
    },
    alarm(){
      this.$router.push({name:'AlarmView'})
    },
    initNotiListener() {
      const eventSource = new EventSourcePolyfill(`${VUE_APP_API_URL}/api/subscription`, {
        headers: {
          "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`
        },
        withCredentials : true
      });

      eventSource.onerror = event => {
        console.log(event);
      }

      eventSource.addEventListener('noti', event => {
        // 새로운 알림 수 + 1
        this.$store.dispatch('countNoti');
        
        const data = JSON.parse(event.data);

        (async () => {
          // 브라우저 알림
          const showNotification = () => {
              
              const notification = new Notification(data.title, {
                  body: data.content
              });
              
              setTimeout(() => {
                  notification.close();
              }, 10 * 1000);
              
              notification.addEventListener('click', () => {
                  window.open(data.url, '_blank');
              });
          }

          // 브라우저 알림 허용 권한
          let granted = false;

          if (Notification.permission === 'granted') {
              granted = true;
          } else if (Notification.permission !== 'denied') {
              let permission = await Notification.requestPermission();
              granted = permission === 'granted';
          }

          // 알림 보여주기
          if (granted) {
              showNotification();
          }
        })();
      })
    }
  },
  computed:{
    isLogin(){
      return this.$store.getters.isLogin
    }
  },
  mounted() {
    if(this.isLogin) {
      this.initNotiListener();
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
  background-color: white;
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
