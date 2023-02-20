<template>
  
    <div id="fh5co-main">
      <div class="fh5co-narrow-content">
        <v-toolbar
        class="BoardTool"
          color="#FCFAEF"
          dark
          tabs
          style="text-align: start;">
          <!-- <v-toolbar-title style="margin-left: 50px; font-weight: 1000;">고민 게시판</v-toolbar-title> -->
          <!-- <div v-if="isLogin===true">
            <router-link 
              to="/BoardCreateView" 
              id="BoardCreateButton"
              class="BoardCreateButton" 
              style="margin-right: 55px;">글쓰기</router-link> 
          </div> -->
    
          <template v-slot:extension>
            <v-tabs
              v-model="tab"
              bg-color="transparent"
              color="grey"
              grow
            >
              <!-- <v-tabs-slider color="yellow"></v-tabs-slider> -->
              <v-tab value="one" style="font-size:20px;">전체</v-tab>
              <v-tab value="two" style="font-size:20px;">아동</v-tab>
              <v-tab value="three" style="font-size:20px;">청소년</v-tab>
              <v-tab value="four" style="font-size:20px;">성인</v-tab>
              <v-tab value="five" style="font-size:20px;">부부</v-tab>
              <v-tab value="six" style="font-size:20px;">노년</v-tab>
              <v-tab value="seven" style="font-size:20px;">기타</v-tab>
              <v-tab value="eight" v-if="isLogin===true" style="font-size:20px;">내 글 보기</v-tab>
        
            </v-tabs>
          </template>
        </v-toolbar>
  
    <v-window v-model="tab">
      <v-window-item value="one">
        <AllBoardViewList/>
      </v-window-item>
  
      <v-window-item value="two">
        <KidBoardViewList/>
      </v-window-item>
  
      <v-window-item value="three">
        <TeenBoardViewList/>
      </v-window-item>
      
      <v-window-item value="four">
        <AdultBoardViewList/>
      </v-window-item>
  
      <v-window-item value="five">
        <CoupleBoardViewList/>
      </v-window-item>
      
      <v-window-item value="six">
        <OldBoardViewList/>
      </v-window-item>
  
      <v-window-item value="seven">
        <GuitarBoardViewList/>
      </v-window-item>
  
      <v-window-item value="eight">
        <MyBoardViewList/>
      </v-window-item>
    </v-window>
      </div>
      
  </div>
</template>

<script>

import AllBoardViewList from '../../components/boarditem/AllBoardViewList.vue'
import KidBoardViewList from '../../components/boarditem/KidBoardViewList.vue'
import TeenBoardViewList from '../../components/boarditem/TeenBoardViewList.vue'
import AdultBoardViewList from '../../components/boarditem/AdultBoardViewList.vue'
import CoupleBoardViewList from '../../components/boarditem/CoupleBoardViewList.vue'
import OldBoardViewList from '../../components/boarditem/OldBoardViewList.vue'
import GuitarBoardViewList from '../../components/boarditem/GuitarBoardViewList.vue'
import MyBoardViewList from '../../components/boarditem/MyBoardViewList.vue'

  export default {
    name: 'BoardView',
    components: {
      AllBoardViewList,
      KidBoardViewList,
      TeenBoardViewList,
      AdultBoardViewList,
      CoupleBoardViewList,
      OldBoardViewList,
      GuitarBoardViewList,
      MyBoardViewList,
    },
    data() {
      return {
        tab: null,
        }
    },
    computed:{
      isLogin(){
        return this.$store.getters.isLogin
    }
  },
  }
</script>
<style>

#fh5co-main {
  width: auto;
  float: right;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
@media screen and (max-width: 1200px) {
  #fh5co-main {
    width: 70%;
  }
}
@media screen and (max-width: 768px) {
  #fh5co-main {
    width: 100%;
  }
}
#fh5co-main .fh5co-narrow-content {
  position: relative;
  width: 100%;
  margin: 0 auto;
  padding: 4em 0;
}
@media screen and (max-width: 768px) {
  #fh5co-main .fh5co-narrow-content {
    width: 100%;
    padding: 3.5em 1em;
  }
}
.BoardTool{
  background-image: url(../../assets/images/counseling.png) !important;
  /* background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url(../../assets/images/counseling.png); */
  background-color: black !important;
  background-size: cover;
  
  margin: 0;
  background-repeat: no-repeat;
  background-position: center;
  height: 45vh;
}
</style>