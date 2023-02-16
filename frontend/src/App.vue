<template>
  <div
    class="mr-5"
    style="position: fixed; z-index: 10; top: 20px; right:5px;"
  >
    <v-btn
      v-if="isLogin=== false"
      @click="dialogclick"
      class="ma-2"
      color="purple"
      icon="mdi-account-outline"
    ></v-btn>
    <v-btn
      v-else
      @click="dialogclick"
      class="ma-2"
      color="indigo"
      icon="mdi-account-outline"
    ></v-btn>
  </div>
  <v-card
    id="myCard"
    v-if="dialog===true"
    class="mx-auto bg-light"
    width="300"
    style="position: fixed; z-index: 9; top: 60px; right:50px;"
    @blur="dialogclick"
  >
    <v-card-item>
      <div style="text-align: start;">
        <!-- 로그인 상태 시작 -->
        <div v-if="isLogin===true">
          <div class="text-h6 mb-1 pt-2 pl-5">
            {{ this.$store.state.payload.id }}님
            <span id="navMypage">
              <!-- 알람 -->
              <v-btn class="text-none" variant="plain">
                <v-badge :content="newNotiCount" color="error" @click="alarm">
                  <v-icon variant="text">mdi-bell-outline</v-icon>
                </v-badge>
              </v-btn>
            </span>
            </div>
          <hr>
          <!-- 로그아웃 -->
          <div class="ml-5">
            <svg xmlns="http://www.w3.org/2000/svg"  width="30" height="30" fill="currentColor" class="bi bi-door-open-fill" viewBox="0 0 16 16" @click="logOut" type="button">
              <path d="M1.5 15a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2.5A1.5 1.5 0 0 0 11.5 1H11V.5a.5.5 0 0 0-.57-.495l-7 1A.5.5 0 0 0 3 1.5V15H1.5zM11 2h.5a.5.5 0 0 1 .5.5V15h-1V2zm-2.5 8c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z"/>
            </svg>
            <button @click="logOut" class="ml-5"><p class="mt-1">로그아웃</p></button>
          </div>
          <hr>
          <!-- 찜목록 -->
          <router-link to="/LikeView" style="text-decoration: none ;" class="ml-5" type="button" @click="dialogoff()">
            <v-icon variant="text" size="x-large">mdi-account-star-outline</v-icon>
            <button><p class="mt-1 ml-5">찜 목록</p></button>
          </router-link>
          <hr>
          <!-- 마이페이지 -->
          <router-link to="/mypage" style="text-decoration: none ;"  class="ml-5" type="button" @click="dialogoff()">
            <v-icon variant="text" size="x-large">mdi-account-cog-outline</v-icon>
            <button><p class="mt-1 ml-5">마이페이지</p></button>
          </router-link>
          <hr>
           <!-- 개인정보 수정 -->
          <router-link to="/CheckEditInformation" style="text-decoration: none ;"  class="ml-5" type="button" @click="dialogoff()">
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="black" class="bi bi-person" viewBox="0 0 16 16">
              <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
            </svg>
            <button><p class="mt-1  ml-5">개인정보 수정</p></button>
          </router-link>
        </div>
        <!-- 로그인 상태 끝 -->
        <!-- 로그 아웃상태 -->
        <div v-if="isLogin===false" class="mt-3">
          <router-link to="/login" style="text-decoration: none ;"  class="ml-5" type="button" @click="dialogoff()">
            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-door-open" viewBox="0 0 16 16" @click="logIn">
              <path d="M8.5 10c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z"/>
              <path d="M10.828.122A.5.5 0 0 1 11 .5V1h.5A1.5 1.5 0 0 1 13 2.5V15h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V1.5a.5.5 0 0 1 .43-.495l7-1a.5.5 0 0 1 .398.117zM11.5 2H11v13h1V2.5a.5.5 0 0 0-.5-.5zM4 1.934V15h6V1.077l-6 .857z"/>
            </svg>
            <p class="mt-1 ml-5">로그인</p>
          </router-link>
        </div>
        <div class="text-caption"></div>
      </div>
    </v-card-item>

    <v-card-actions>
      <v-btn variant="outlined" style="width:100%" @click="dialogclick">
        확인
      </v-btn>
    </v-card-actions>
  </v-card>
  <div id="fh5co-page">
    <a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
    <div style="text-align:center">
      <aside id="fh5co-aside" role="complementary" class="border js-fullheight d-flex justify-center align-center mb-6 bg-surface-variant" >
        <nav id="fh5co-main-menu" role="navigation">
          <router-link to="/">
            <img
              id="logo"
              class="w-50 h-auto mr-4"
              :src="projectlogo"
              alt="noimage"
            />
          </router-link>
          <div style="height:220px"></div>
          <ul>
            <li><router-link to="/BoardView">고민게시판</router-link>&nbsp;&nbsp;&nbsp;</li>
            <li><router-link to="/Calendar">일정관리</router-link>&nbsp;&nbsp;&nbsp;</li>
            <li><router-link to="/ClientManage">고객관리</router-link>&nbsp;&nbsp;&nbsp;</li>
            <li><router-link to="/HelpView">HELP DESK</router-link>&nbsp;&nbsp;&nbsp;</li>
          </ul>
          <div style="height:220px"></div>
        </nav>
      </aside>
    </div>
    <router-view /> 
  </div>
  
  <!-- <LoadingView :loading="loadingStatus"></LoadingView> -->
  <!-- <LoadingView :loading="this.$store.state.loadingStatus"></LoadingView> -->
</template>

<script>
import axios from 'axios'
// import axios from '@/store/instance.js'
import { EventSourcePolyfill } from "event-source-polyfill";
// import LoadingView from '../src/views/common/LoadingView'

document.querySelector('body').setAttribute('style',"margin: 0;")
const VUE_APP_API_URL = process.env.VUE_APP_API_URL
const LOCAL_URL = process.env.LOCAL_URL

export default {
  name:'App',
  data(){
    return{
      dialog : false,
      projectlogo : require('../src/assets/images/projectlogoperpect.png'),
      newNotiCount : this.$store.state.newNotiCount,
    }
  },
  component: {
    // LoadingView
  },


  methods: {
    dialogoff() {
      this.dialog = false
    },
    dialogclick() {
      this.dialog = !this.dialog
    },
    // closeclick() {
    //   const card = document.getElementById('myCard')
    //   window.onclick(function(e){
    //     if (e.target == myCard){
    //       this.dialog = false
    //       console.log(card)
    //       console.log(window)
    //     }
    //   })
    // },
    logOut(){
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/auth/logout`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        this.dialogoff();
        this.$store.dispatch('logOut')
        this.$router.push({name:'home'})
      })
      .then(
      )
    },
    logIn(){
      dialogoff();
      this.$router.push({name:'login'});
    },
    alarm(){
      dialogoff();
      this.$router.push({name:'AlarmView'});
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
              // url: `${VUE_APP_API_URL}/api/auth/kakao`
                window.open(`${VUE_APP_API_URL}/AlarmView`, '_self');
            });
        }

        // 알림 보여주기
        if (granted) {
            showNotification();
        }
      });

      eventSource.onerror = event => {
        console.log(event);
      }
      
    }
  },
  computed:{
    store() {
      return store
    },
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
  beforeUpdate() {
    if(this.$store.getters.isLogin) {
      this.initNotiListener();
      this.$store.dispatch('unreadNotiCount');
    }
  },
  mounted() {
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
  overflow:hidden;
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
