<template>
  <div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
		<aside id="fh5co-aside" role="complementary" class="border js-fullheight">

			<h1 id="fh5co-logo">
        <router-link to="/">
          <img
            id="logo"
            :src="projectlogo"
            alt="noimage"
            style="width: 75px; height: 30px; "
          />
        </router-link> &nbsp;&nbsp;&nbsp;</h1>


			<nav id="fh5co-main-menu" role="navigation">
				<ul>
          <li><router-link to="/BoardView">고민게시판</router-link>&nbsp;&nbsp;&nbsp;</li>
          <li><router-link to="/HelpView">HELP DESK</router-link>&nbsp;&nbsp;&nbsp;</li>
          <li><router-link to="/Calendar">일정관리</router-link>&nbsp;&nbsp;&nbsp;</li>
          <li><router-link to="/ClientManage">고객관리</router-link>&nbsp;&nbsp;&nbsp;</li>
				</ul>
			</nav>
		</aside>
  </div>




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
        <router-link to="/BoardView">고민게시판</router-link>&nbsp;&nbsp;&nbsp;
        <router-link to="/HelpView">HELP DESK</router-link>&nbsp;&nbsp;&nbsp;
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
const LOCAL_URL = process.env.LOCAL_URL

export default {
  name:'App',
  data(){
    return{
      projectlogo : require('../src/assets/images/projectlogoperpect.png'),
      newNotiCount : this.$store.state.newNotiCount,
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

      // 알림 권한 설정
      let granted = false;
      if (Notification.permission === 'granted') {
          granted = true;
      } else if (Notification.permission !== 'denied') {
          Notification.requestPermission()
                      .then((permission) => granted = permission === 'granted')          
      }

      eventSource.onopen = e => {
        console.log(e);
      }

      eventSource.addEventListener('noti', event => {
        // 새로운 알림 수 + 1
        this.$store.dispatch('countNoti');
        
        const data = JSON.parse(event.data);
        
        // 브라우저 알림
        const showNotification = () => {
            const notification = new Notification("소담", {
                body: data.title,
                icon: require('../src/assets/images/faviconalarm.png')
            });
            
            notification.addEventListener('click', () => {
                window.open("http://localhost:8180/AlarmView", '_self');
            });
        }

        // 알림 보여주기
        if (granted) {
            showNotification();
        }
      });

      eventSource.onerror = event => {
        console.log(event.data);
      }
      
    }
  },
  computed:{
    isLogin(){
      return this.$store.getters.isLogin
    },
    checkNotiCount() {
      return this.$store.getters.getNotiCount
    }
  },
  watch: {
    checkNotiCount(count) {
      this.newNotiCount = count;
    }
  },
  mounted() {
    console.log("mounted");
    console.log(VUE_APP_API_URL, LOCAL_URL)
    if(this.$store.getters.isLogin) {
      this.initNotiListener();
    }
  },
  beforeUpdate() {
    console.log("beforeUpdate")
    if(this.$store.getters.isLogin) {
      this.initNotiListener();
      this.$store.dispatch('unreadNotiCount');
    }
  }
}
</script>

<style>
.fh5co-page{
  font-family: "Roboto", Arial, sans-serif;
  font-weight: 300;
  font-size: 20px;
  line-height: 1.6;
  color: rgba(0, 0, 0, 0.5);
}
@media screen and (max-width: 992px) {
  .fh5co-page {
    font-size: 16px;
  }
}

li {
  color: #da1212;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
li:hover, a:active, a:focus {
  color: #da1212;
  outline: none;
}
::-webkit-selection {
  color: #fff;
  background: #da1212;
}

::-moz-selection {
  color: #fff;
  background: #da1212;
}

::selection {
  color: #fff;
  background: #da1212;
}


#fh5co-aside {
  padding-top: 40px;
  padding-bottom: 40px;
  width: 15%;
  position: fixed;
  bottom: 0;
  top: 0;
  left: 0;
  overflow-y: scroll;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
#fh5co-aside.border {
  border-right: 1px solid #e6e6e6;
}
@media screen and (max-width: 1200px) {
  #fh5co-aside {
    width: 30%;
  }
}
@media screen and (max-width: 768px) {
  #fh5co-aside {
    width: 270px;
    -moz-transform: translateX(-270px);
    -webkit-transform: translateX(-270px);
    -ms-transform: translateX(-270px);
    -o-transform: translateX(-270px);
    transform: translateX(-270px);
  }
}
#fh5co-aside #fh5co-logo {
  text-align: center;
}
#fh5co-aside #fh5co-main-menu ul {
  text-align: center;
  margin: 0;
  padding: 0;
}
@media screen and (max-width: 768px) {
  #fh5co-aside #fh5co-main-menu ul {
    margin: 0 0 2em 0;
  }
}
#fh5co-aside #fh5co-main-menu ul li {
  margin: 0 0 10px 0;
  padding: 0;
  list-style: none;
}
#fh5co-aside #fh5co-main-menu ul li a {
  color: rgba(0, 0, 0, 0.5);
  text-decoration: none;
  letter-spacing: .1em;
  text-transform: uppercase;
  font-size: 15px;
  font-weight: 300;
  position: relative;
  -webkit-transition: 0.3s;
  -o-transition: 0.3s;
  transition: 0.3s;
  padding: 10px 10px;
  letter-spacing: .2em;
  font-family: "Montserrat", Arial, sans-serif;
}
#fh5co-aside #fh5co-main-menu ul li a:after {
  content: "";
  position: absolute;
  height: 2px;
  bottom: 7px;
  left: 10px;
  right: 10px;
  background-color: #da1212;
  visibility: hidden;
  -webkit-transform: scaleX(0);
  -moz-transform: scaleX(0);
  -ms-transform: scaleX(0);
  -o-transform: scaleX(0);
  transform: scaleX(0);
  -webkit-transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  -moz-transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  -ms-transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  -o-transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}
#fh5co-aside #fh5co-main-menu ul li a:hover {
  text-decoration: none;
  color: black;
}
#fh5co-aside #fh5co-main-menu ul li a:hover:after {
  visibility: visible;
  -webkit-transform: scaleX(1);
  -moz-transform: scaleX(1);
  -ms-transform: scaleX(1);
  -o-transform: scaleX(1);
  transform: scaleX(1);
}
#fh5co-aside #fh5co-main-menu ul li.fh5co-active a {
  color: black;
}
#fh5co-aside #fh5co-main-menu ul li.fh5co-active a:after {
  visibility: visible;
  -webkit-transform: scaleX(1);
  -moz-transform: scaleX(1);
  -ms-transform: scaleX(1);
  -o-transform: scaleX(1);
  transform: scaleX(1);
}

.fh5co-nav-toggle {
  cursor: pointer;
  text-decoration: none;
}
.fh5co-nav-toggle.active i::before, .fh5co-nav-toggle.active i::after {
  background: #000;
}
.fh5co-nav-toggle.dark.active i::before, .fh5co-nav-toggle.dark.active i::after {
  background: #000;
}
.fh5co-nav-toggle:hover, .fh5co-nav-toggle:focus, .fh5co-nav-toggle:active {
  outline: none;
  border-bottom: none !important;
}
.fh5co-nav-toggle i {
  position: relative;
  display: -moz-inline-stack;
  display: inline-block;
  zoom: 1;
  *display: inline;
  width: 30px;
  height: 2px;
  color: #000;
  font: bold 14px/.4 Helvetica;
  text-transform: uppercase;
  text-indent: -55px;
  background: #000;
  transition: all .2s ease-out;
}
.fh5co-nav-toggle i::before, .fh5co-nav-toggle i::after {
  content: '';
  width: 30px;
  height: 2px;
  background: #000;
  position: absolute;
  left: 0;
  -webkit-transition: 0.2s;
  -o-transition: 0.2s;
  transition: 0.2s;
}
.fh5co-nav-toggle.dark i {
  position: relative;
  color: #000;
  background: #000;
  transition: all .2s ease-out;
}
.fh5co-nav-toggle.dark i::before, .fh5co-nav-toggle.dark i::after {
  background: #000;
  -webkit-transition: 0.2s;
  -o-transition: 0.2s;
  transition: 0.2s;
}

.fh5co-nav-toggle i::before {
  top: -7px;
}

.fh5co-nav-toggle i::after {
  bottom: -7px;
}

.fh5co-nav-toggle:hover i::before {
  top: -10px;
}

.fh5co-nav-toggle:hover i::after {
  bottom: -10px;
}

.fh5co-nav-toggle.active i {
  background: transparent;
}

.fh5co-nav-toggle.active i::before {
  top: 0;
  -webkit-transform: rotateZ(45deg);
  -moz-transform: rotateZ(45deg);
  -ms-transform: rotateZ(45deg);
  -o-transform: rotateZ(45deg);
  transform: rotateZ(45deg);
}

.fh5co-nav-toggle.active i::after {
  bottom: 0;
  -webkit-transform: rotateZ(-45deg);
  -moz-transform: rotateZ(-45deg);
  -ms-transform: rotateZ(-45deg);
  -o-transform: rotateZ(-45deg);
  transform: rotateZ(-45deg);
}

.fh5co-nav-toggle {
  position: fixed;
  left: 0;
  top: 0px;
  z-index: 9999;
  cursor: pointer;
  opacity: 1;
  visibility: hidden;
  padding: 20px;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
@media screen and (max-width: 768px) {
  .fh5co-nav-toggle {
    opacity: 1;
    visibility: visible;
  }
}

@media screen and (max-width: 480px) {
  .col-xxs-12 {
    float: none;
    width: 100%;
  }
}

</style>
