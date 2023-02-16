<template>
  <div>
    <div class="container">      
      <div class="row" style="padding-top:20px">
        <div class="col-1" style="text-align: center; margin: auto;">
          
        </div>
        <div class="col-8" style="text-align: center; margin: auto;">
          
        </div>
        <div class="col-1" style="text-align: center; margin: auto;">
          <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-chat-left" viewBox="0 0 16 16">
            <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
          </svg>
        </div>
        <div class="col-2" style="text-align: center; margin: auto;">
          <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
            <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
            <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
          </svg>
        </div>
      </div>

      <div v-if="searchfinish===false">
        <QnaHelpViewListItem
        v-for="(Boardarticle, index) in QnaHelpViewListarticles.content"
        :key="Boardarticle.id"
        :Boardarticle="Boardarticle"
        :index="index"
        :limit="QnaHelpViewListPage"
        @delete-Board="getQnaHelpViewListArticles"
        @refresh-Board="getQnaHelpViewListArticles"
        /> 
        </div>
        <div v-if="searchfinish===true">
          <QnaHelpViewListItem
          v-for="(Boardarticle, index) in contentlist.content"
          :key="Boardarticle.id"
          :Boardarticle="Boardarticle"
          :index="index"
          :limit="QnaHelpViewListPage"
          this.searchfinish: false
          @delete-Board="getQnaHelpViewListArticles"
          @refresh-Board="getQnaHelpViewListArticles"
          /> 
        </div>
      </div>
            
      <div style="margin:auto; padding-top:30px;">
        <div class="searchbar">
          <input type="text"
          v-model="schVal"
          placeholder="검색"
          style="border:1px solid black; border-radius:30px; width:50%; height:50px; text-indent:20px"
          @keypress.enter.prevent="QnaHelpViewListarticlessearch"
          >
          <v-btn variant="text" icon="mdi-magnify" 
            @click="QnaHelpViewListarticlessearch"></v-btn>
        </div>
      </div>
      <div v-if="QnaHelpViewListarticles">
        <div style="margin: auto;" >
          <v-pagination
            v-model="this.QnaHelpViewListPage"
            :length="5"
          ></v-pagination>
        </div>
      </div>
    <!-- <v-container>
      <v-card elevation="10" outlined width="100%" class="mx-auto">

        <v-card-text>
          <v-row>
            <v-col  cols="12" md="9" style="margin-left:60px; margin-top:30px; ">
            </v-col>
            <v-col align-self="center">
              <div>

              </div>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
            </v-col>
          </v-row>
        </v-card-text>
      </v-card>
	
	  </v-container> -->
  </div>
</template>


<script>

import axios from 'axios'
import QnaHelpViewListItem from '../helpitem/QnaHelpViewListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'QnaHelpViewList',
  data() {
    return {
      QnaHelpViewListPage: 1,
      QnaHelpViewListarticles: [], 
      schVal: '',
      searchfinish: false,
      contentlist: [], 
    }
  },
  components: {
    QnaHelpViewListItem
  },
  created() {
    this.getQnaHelpViewListArticles()

  },
  methods: {
    getQnaHelpViewListArticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/help-desk/qna/list`,
        // headers: {
        //   "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          console.log('이거 되라 게시판 전체 목록')
          this.QnaHelpViewListarticles = res.data
        })
        .catch((err) => {
          console.log('어림도 없지 게시판 전체 목록')
        })
    },
    QnaHelpViewListarticlessearch() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api//help-desk/qna/list?searchword=${this.schVal}`
      })
        .then((res) => {
          console.log(res)
          this.contentlist = res.data;
          this.searchfinish = true;
          this.schVal = '';
          console.log('됐음 카멜레온')
          // this.KidBoardarticles = res.data.category
        })
        .catch(() => {
          console.log('안됐음 카멜레온')
        })
    },
  }
}
</script>

<style>
#QnaHelpViewList {
  /* width: 1255px; */
  margin: auto;
}

.searchbar{
  text-align: center;
}


</style>







