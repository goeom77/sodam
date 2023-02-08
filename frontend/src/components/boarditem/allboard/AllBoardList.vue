
<template>
  <div id="AllBoardList">
    <div>
      <v-card class="AllBoardListCard">
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
            내 글 보기
          </v-tab>
          <div>
            <router-link 
            to="/KidBoardCreate" 
            id="KidBoardCreateButton"
            class="KidBoardCreateButton" >글쓰기</router-link> 
          </div>
        </v-tabs>
        
        <v-window v-model="tab">
          <v-window-item value="one">
            <AllBoardListAll/>
          </v-window-item>
          
          <v-window-item value="two">
            <MyBoardList/>
          </v-window-item>
        </v-window>
      </v-card>
    </div>
    <!-- <div>
      <router-link 
      to="/KidBoardCreate" 
      id="KidBoardCreateButton"
      class="KidBoardCreateButton" >글쓰기</router-link> 
    </div> -->
  </div>
</template>
  
<script>
import AllBoardListAll from '../boarditem/AllBoardListAll.vue'
import MyBoardList from '../boarditem/MyBoardList.vue'


  export default {
    name: 'AllBoardList',
    components: {
      AllBoardListAll,
      MyBoardList,
    },
    data(){
      return{
        tab:null
      }
    },

  }
  </script>
  
  
  <style>
  #AllBoardList {
    /* 반응형 x */
    /* width: 800px; */
    margin: 0 auto;
  }
  
  a {
    text-decoration: none;
    color: white;
  }
  
  a:visited {
    background-color: #579BB1;
  }
  

  .AllBoardListCard{
    width:90%; 
    height:1000px; 
    /* justify-content: center; */
    /* display: grid; */
    /* grid-template-columns: 1fr 1fr 1fr 1fr 1fr; */
    background-color:white(0, 0, 0, 0.5);
    padding-left: 60px;
    padding-right:60px;
    position: absolute;
    float: left;
    /* height:1000px; */
  }

  .KidBoardCreateButton {
  float:left;
  height: 30px;
  line-height: 25px;
  font-weight: 700;
  border: 1px solid white;
  /* border-bottom: 0px; */
  background-color: #579BB1;
  padding: 3px;
  padding-left: 10px;
  padding-right: 10px;
  border-radius: 5px;
}
  </style>