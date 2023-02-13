<template>
  <div>
    <v-container>
      <v-card elevation="10" outlined width="100%" class="mx-auto">

        <v-card-text>
          <v-row>
            <v-col  cols="12" md="9" style="margin-left:60px; margin-top:30px; ">
              <div>
                <v-text-field
                  style="background-color:white;"
                  v-model="schVal"
                  label="검색어"
                  single-line
                  @keypress.enter.prevent="CoupleBoardViewListarticlessearch"
                ></v-text-field>
              </div>
            </v-col>
            <v-col align-self="center">
              <div>
                <button
                  @click="CoupleBoardViewListarticlessearch"
                  style="background-color: #579BB1; border-radius: 10px; width: 70%; height: 30px;  float: left; padding-bottom: 0%;"
                >SEARCH</button>

              </div>
            </v-col>
          </v-row>
          <v-row>
            <v-col>

                <div v-if="searchfinish===false">
                  <BoardViewListItem
                    v-for="(Boardarticle, index) in CoupleBoardViewListarticles.content"
                    :key="Boardarticle.postId"
                    :Boardarticle="Boardarticle"
                    :index="index"
                    :limit="CoupleBoardViewListPage"
                    @delete-Board="getCoupleBoardViewListArticles"
                    @refresh-Board="getCoupleBoardViewListArticles"
                /> 
                </div>
                <div v-if="searchfinish===true">
                  <BoardViewListItem
                    v-for="(Boardarticle, index) in contentlist.content"
                    :key="Boardarticle.postId"
                    :Boardarticle="Boardarticle"
                    :index="index"
                    :limit="CoupleBoardViewListPage"
                    this.searchfinish: false
                    @delete-Board="getCoupleBoardViewListArticles"
                    @refresh-Board="getCoupleBoardViewListArticles"
                  /> 
                </div>
            </v-col>
          </v-row>
        </v-card-text>
        <div v-if="CoupleBoardViewListarticles">
          <div style="margin: auto;" >
            <v-pagination
              v-model="this.CoupleBoardViewListPage"
              :length="5"
            ></v-pagination>
          </div>
        </div>
      </v-card>
	
	  </v-container>
  </div>
</template>


<script>

import axios from 'axios'
import BoardViewListItem from '../boarditem/BoardViewListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'CoupleBoardViewList',
  data() {
    return {
      CoupleBoardViewListPage: 1,
      CoupleBoardViewListarticles: [], 
      schVal: '',
      searchfinish: false,
      contentlist: [], 
    }
  },
  components: {
    BoardViewListItem
  },
  created() {
    this.getCoupleBoardViewListArticles()

  },
  methods: {
    getCoupleBoardViewListArticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/list/couple`,
        // headers: {
        //   "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          console.log('이거 되라 게시판 전체 목록')
          this.CoupleBoardViewListarticles = res.data
        })
        .catch((err) => {
          console.log('어림도 없지 게시판 전체 목록')
        })
    },
    CoupleBoardViewListarticlessearch() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/list/couple?searchword=${this.schVal}`
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
#CoupleBoardViewList {
  /* width: 1255px; */
  margin: auto;
}



</style>







