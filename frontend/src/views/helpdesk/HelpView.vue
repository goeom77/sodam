<template>
  <div id="fh5co-main">
    <div class="fh5co-narrow-content-Help">
        <v-toolbar
          color="white"
          dark
          tabs
          style="text-align: start;">
          <v-toolbar-title style="margin-left: 50px; font-weight: 1000;">HELP DESK</v-toolbar-title>
          <v-spacer></v-spacer>
          <div v-if="this.$store.state.payload.id==='admin'">
            <router-link 
              to="/NoticeCreateView" 
              id="NoticeCreateView"
              class="HelpCreateButton" 
              style="margin-right: 55px;">글쓰기</router-link> 
          </div>
          <div v-if="isLogin===true">
            <router-link    
              to="/QnaCreateView" 
              id="QnaCreateView"
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

#fh5co-main {
  width: 85%;
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
#fh5co-main .fh5co-narrow-content-Help {
  position: relative;
  width: 80%;
  margin: 0 auto;
  padding-top: 5em;
}
@media screen and (max-width: 768px) {
  #fh5co-main .fh5co-narrow-content-Help {
    width: 100%;
    padding: 0;
  }
}
</style>