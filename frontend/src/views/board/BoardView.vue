<template>
  <div>
    
    <div style="width:1168px; margin: auto;">
      <v-toolbar
        color="#FCFAEF"
        dark
        tabs
        style="text-align: start;"
      >
  
        <v-toolbar-title style="margin-left: 50px; font-weight: 1000;">고민 게시판</v-toolbar-title>
  
        <v-spacer></v-spacer>

        <div>
          <router-link 
            to="/BoardCreateView" 
            id="BoardCreateButton"
            class="BoardCreateButton" 
            style="margin-right: 55px;">글쓰기</router-link> 
        </div>
  
        <template v-slot:extension>
          <v-tabs
            v-model="tab"
            bg-color="transparent"
            color="grey"
            grow
          
          >
            <!-- <v-tabs-slider color="yellow"></v-tabs-slider> -->
  
            <v-tab value="one">전체</v-tab>
            <v-tab value="two">아동</v-tab>
            <v-tab value="three">청소년</v-tab>
            <v-tab value="four">성인</v-tab>
            <v-tab value="five">부부</v-tab>
            <v-tab value="six">노년</v-tab>
            <v-tab value="seven">기타</v-tab>
            <v-tab value="eight">내 글 보기</v-tab>
      
          </v-tabs>
        </template>
      </v-toolbar>

  </div>
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
    data () {
      return {
        tab: null,
        }
    }
  }
</script>
<style>
.BoardCreateButton {
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