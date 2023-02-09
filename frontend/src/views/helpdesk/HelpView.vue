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
            to="/NoticeCreateView" 
            id="NoticeCreateButton"
            class="HelpCreateButton" 
            style="margin-right: 55px;">글쓰기</router-link> 
        </div>
        <div>
          <router-link 
            to="/QnaCreateView" 
            id="QnaCreateButton"
            class="HelpCreateButton" 
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
  
            <v-tab value="one">공지사항</v-tab>
            <v-tab value="two">1:1 문의</v-tab>
      
          </v-tabs>
        </template>
      </v-toolbar>

    </div>
    <v-window v-model="tab">
      <v-window-item value="one">
        <NoticeHelpViewList/>
      </v-window-item>

      <v-window-item value="two">
        <QnaHelpViewList/>
      </v-window-item>

    </v-window>
  </div>
</template>

<script>

import NoticeHelpViewList from '../../components/helpitem/NoticeHelpViewList.vue'
import QnaHelpViewList from '../../components/helpitem/QnaHelpViewList.vue'


  export default {
    name: 'BoardView',
    components: {
      NoticeHelpViewList,
      QnaHelpViewList
    },
    data () {
      return {
        tab: null,
        }
    }
  }
</script>
<style>
.HelpCreateButton {
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