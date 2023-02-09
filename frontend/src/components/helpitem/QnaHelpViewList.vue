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
                  @keypress.enter.prevent="QnaHelpViewListarticlessearch"
                ></v-text-field>
              </div>
            </v-col>
            <v-col align-self="center">
              <div>
                <Button
                  @click="QnaHelpViewListarticlessearch"
                  style="background-color: #579BB1; border-radius: 10px; width: 70%; height: 30px;  float: left; padding-bottom: 0%;"
                >SEARCH</Button>

              </div>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-data-table>
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
              </v-data-table>
            </v-col>
          </v-row>
        </v-card-text>
        <div v-if="QnaHelpViewListarticles">
          <div style="margin: auto;" >
            <v-pagination
              v-model="this.QnaHelpViewListPage"
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
        headers: {
          "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
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



</style>







