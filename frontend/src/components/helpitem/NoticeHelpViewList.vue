<template>
  <div>
      <v-card elevation="10" outlined width="100%" class="mx-auto">
        <div class="container helpContent" style="border-bottom: 1px solid #ccc;">
          <div class="row">
            <div class="col-1" style="text-align: center; margin:auto;">
              
            </div>
            <div class="col-8" style="text-align: center; margin:auto;" >
              
            </div>
            <div class="col-1" style="text-align: center; margin:auto;" >
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
        </div>
        <NoticeHelpViewListItem
          v-for="(Noticearticle, index) in NoticeHelpViewListarticles.content"
          :key="Noticearticle.id"
          :Noticearticle="Noticearticle"
          :index="index"
          :limit="NoticeHelpViewListPage"
          @delete-Notice="getNoticeHelpViewListArticles"
          @refresh-Notice="getNoticeHelpViewListArticles"/>
        
            <!-- 페이지네이션 -->
        <div v-if="NoticeHelpViewListarticles">
          <div style="margin: auto;" >
            <v-pagination
              v-model="this.NoticeHelpViewListPage"
              :length="5"
            ></v-pagination>
          </div>
        </div>
      </v-card>
  </div>
</template>


<script>

import axios from 'axios'
import NoticeHelpViewListItem from '../helpitem/NoticeHelpViewListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'NoticeHelpViewList',
  data() {
    return {
      NoticeHelpViewListPage: 1,
      NoticeHelpViewListarticles: [], 
    }
  },
  components: {
    NoticeHelpViewListItem
  },
  created() {
    this.getNoticeHelpViewListArticles()

  },
  methods: {
    getNoticeHelpViewListArticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/help-desk/notice/list`,
        // headers: {
        //   "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          console.log('이거 되라 게시판 전체 목록')
          this.NoticeHelpViewListarticles = res.data
        })
        .catch((err) => {
          console.log('어림도 없지 게시판 전체 목록')
        })
    },

  }
}
</script>

<style>
.noticehelp{
  text-align: center;
  justify-content: center;
}
#NoticeHelpViewList {
  /* width: 1255px; */
  margin: auto;
}


.help-menu{
  font-size: 50px;
  font-weight: 1000;
  border-bottom: 1px solid #ccc;
}
.helpContent{
  padding:20px;
}
</style>







