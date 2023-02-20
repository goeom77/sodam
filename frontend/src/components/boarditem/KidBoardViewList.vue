<template>
  <div class="container BoardList">
    <div class="row">
      <div class="col-1" style="text-align: center; margin:auto;">
        
      </div>

      <div class="col-7" style="margin:auto;">
        
      </div>
      <div class="col-1" style="text-align: center; margin:auto;">
        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-chat-left" viewBox="0 0 16 16">
          <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
        </svg>
      </div>
      <div class="col-1" style="text-align: center; margin:auto;">
        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
          <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
          <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
        </svg>
      </div>
      <div class="col-2" style="text-align: center; margin:auto;">
        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
          <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
          <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
        </svg>
      </div>

    </div>
    <v-row>
      <v-col>
        <div v-if="searchfinish===false">
          <BoardViewListItem
            v-for="(Boardarticle, index) in KidBoardViewListarticles.content"
            :key="index"
            :Boardarticle="Boardarticle"
            :index="index"
            :limit="KidBoardViewListPage"
            @delete-Board="getKidBoardViewListArticles"
            @refresh-Board="getKidBoardViewListArticles"
        /> 
        </div>
        <div v-if="searchfinish===true">
          <BoardViewListItem
            v-for="(Boardarticle, index) in contentlist.content"
            :key="Boardarticle.postId"
            :Boardarticle="Boardarticle"
            :index="index"
            :limit="KidBoardViewListPage"
            this.searchfinish: false
            @delete-Board="getKidBoardViewListArticles"
            @refresh-Board="getKidBoardViewListArticles"
          /> 
        </div>
      </v-col>
    </v-row>
    
    <div style="text-align: right; margin:10px">
      <v-btn append-icon="mdi-pencil" @click="moveCreate">
        게시글 작성
      </v-btn>
    </div>

    <!-- 검색 -->
    <div style="margin:auto; padding-top:30px;">
      <div class="searchbar">
        <input type="text"
        v-model="schVal"
        placeholder="검색"
        style="border:1px solid black; border-radius:30px; width:50%; height:50px; text-indent:20px"
        @keypress.enter.prevent="KidBoardViewListarticlessearch"
        >
        <v-btn variant="text" icon="mdi-magnify" 
          @click="KidBoardViewListarticlessearch"></v-btn>
      </div>
    </div>
    <!-- 페이지네이션 -->
    <div v-if="KidBoardViewListarticles">
      <div style="margin: auto;" >
        <v-pagination
          v-model="this.KidBoardViewListPage"
          :length="5"
        ></v-pagination>
      </div>
    </div>
  </div>
</template>


<script>

import axios from 'axios'
import BoardViewListItem from '../boarditem/BoardViewListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'KidBoardViewList',
  data() {
    return {
      KidBoardViewListPage: 1,
      KidBoardViewListarticles: [], 
      schVal: '',
      searchfinish: false,
      contentlist: [], 
    }
  },
  components: {
    BoardViewListItem
  },
  created() {
    this.getKidBoardViewListArticles()

  },
  methods: {
    getKidBoardViewListArticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/list/child`,
        // headers: {
        //   "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          this.KidBoardViewListarticles = res.data
        })

    },
    KidBoardViewListarticlessearch() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/list/child?searchword=${this.schVal}`
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
    moveCreate(){
      this.$router.push({name:'BoardCreateView'})
    }
  }
}
</script>

<style>
#KidBoardViewList {
  /* width: 1255px; */
  margin: auto;
}



</style>







