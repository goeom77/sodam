<template>
  <div id="AlarmViewList">
        <v-card class="Card">
          <v-tabs
            v-model="tab"
            bg-color="transparent"
            color="grey"
            grow
          >
            <v-tab value="one">
              전체
            </v-tab>
            <v-tab value="two">
              상담
            </v-tab>
            <v-tab value="three">
              고민 게시판
            </v-tab>
            <v-tab value="four">
              HELP DESK
            </v-tab>
          </v-tabs>
          
          <v-window v-model="tab">
            <v-window-item value="one">
              <AlarmViewListAll/>
            </v-window-item>
            
            <v-window-item value="two">
              <AlarmViewListSangdam/>
            </v-window-item>
            
            <v-window-item value="three">
              <AlarmViewListBoard/>
            </v-window-item>
            
            <v-window-item value="four">
              <AlarmViewListHelp/>
            </v-window-item>
          </v-window>
        </v-card>
  </div>
</template>
  
<script>
import AlarmViewListAll from '../alarm/AlarmViewListAll.vue'
import AlarmViewListSangdam from '../alarm/AlarmViewListSangdam.vue'
import AlarmViewListBoard from '../alarm/AlarmViewListBoard.vue'
import AlarmViewListHelp from '../alarm/AlarmViewListHelp.vue'

  export default {
    name: 'AlarmViewList',
    components: {
      AlarmViewListAll,
      AlarmViewListSangdam,
      AlarmViewListBoard,
      AlarmViewListHelp,
    },
    data(){
      return{
        tab:null
      }
    },

  }
  </script>
  
  
  <style>
  #AlarmViewList {
    /* 반응형 x */
    width: 1255px;
    margin: 0 auto;
  }
  
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
    background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('@/assets/hand.png');
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
  #AlarmViewListtitle {
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
    width:100%; 
    height:1000px; 
    /* justify-content: center; */
    /* display: grid; */
    /* grid-template-columns: 1fr 1fr 1fr 1fr 1fr; */
    background-color:white(0, 0, 0, 0.5);
    padding-left: 60px;
    padding-right:60px;
    position: absolute;

    /* height:1000px; */
  }
  </style>