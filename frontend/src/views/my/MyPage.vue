<template>
<div id="fh5co-main">
  <div class="fh5co-narrow-content">

    <div id="Notice">
      <div id="MyBoard">
          <div id="MyBoardtitle">
            <h1>마이페이지</h1>
          </div>  
        </div>
  
          <v-card class="Card">
            <v-tabs
              v-model="tab"
              bg-color="transparent"
              color="grey"
              grow
            >
              <v-tab value="one">
                개인정보 수정
              </v-tab>
              <v-tab value="two">
                내 글 보기
              </v-tab>
              <v-tab value="three">
                찜 목록
              </v-tab>
              <v-tab value="four">
                상담 진행
              </v-tab>
            </v-tabs>
            
            <v-window v-model="tab">
              <v-window-item value="one">
                <CheckEditInformation/>
              </v-window-item>
              
              <v-window-item value="two">
                <MyPostView/>
              </v-window-item>
              
              <v-window-item value="three">
                <LikeView/>
              </v-window-item>
  
              <v-window-item value="four">
                <SangdamView/>
              </v-window-item>
            </v-window>
          </v-card>
        <div>
      </div>
        <!-- <KidBoardList/> -->
    </div>
  </div>
</div>
</template>
  
<script>
import CheckEditInformation from '@/views/my/edit/CheckEditInformation.vue'
import LikeView from '@/views/my/like/LikeView.vue'
import MyPostView from '@/views/my/mypost/MyPostView.vue'
import SangdamView from '@/views/my/sangdam/SangdamView.vue'

SangdamView

  export default {
    name: 'MyPage',
    components: {
      CheckEditInformation,
      LikeView,
      MyPostView,
      SangdamView,
    },
    data(){
      return{
        tab:null
      }
    }
  }
  </script>
  
  
  <style>

  
  a {
    text-decoration: none;
    color: white;
  }
  
  a:visited {
    background-color: #579BB1;
  }
  
  
  #MyBoard {
    /* margin-top: 61px; */
    width: 100%;
    background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('@/assets/images/hand.png');
    background-color: aliceblue;
    background-repeat: no-repeat;
    background-size: 100% 100%;
    /* background-size: cover; */
    text-align: center;
    font-size: large;
    font-weight: 100;
    padding-top: 20px;
    height: 250px;
    position: relative;
  }
  #myCategory {
    width:100%; 
    height:60px; 
    line-height: 65px;
    /* justify-content: center; */
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
    background-color:rgba(96, 96, 96, 0.5);
    padding-left: 60px;
    padding-right:60px;
    position: absolute;
    bottom: 0px;
  } 
  #MyBoardtitle {
    position: absolute;
    left: 50%; 
    bottom: 50%; 
    transform: translate(-50%);
  }
  #myCategory a.router-link-exact-active {
    background-color: #579BB1;
  }
  .CategoryClass {
    float:left;
    height:60px; 
    font-weight: 700;
    border: 1px solid white;
    border-bottom: 0px;
  }

  .Card{
    /* width:100%; 
    height:500px;  */
    /* justify-content: center; */
    /* display: grid; */
    /* grid-template-columns: 1fr 1fr 1fr 1fr 1fr; */
    /* background-color:white(0, 0, 0, 0.5);
    padding-left: 60px;
    padding-right:60px;
    position: absolute; */

    /* height:1000px; */
  }
  </style>