<template>
  <div>
      <v-card elevation="10" outlined width="100%" class="mx-auto">
        <v-card-text class="noticehelp">
          <v-row class="NoticeTable">
            <v-col>
              <v-table>
                <thead>
                  <tr>
                    <th class="table-header">
                      Name
                    </th>
                    <th class="table-header">
                      Calories
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="item in desserts"
                    :key="item.name"
                  >
                    <td>{{ item.name }}</td>
                    <td>{{ item.calories }}</td>
                  </tr>
                </tbody>
              </v-table>
                <NoticeHelpViewListItem
                  v-for="(Boardarticle, index) in NoticeHelpViewListarticles.content"
                  :key="Boardarticle.id"
                  :Boardarticle="Boardarticle"
                  :index="index"
                  :limit="NoticeHelpViewListPage"
                  @delete-Board="getNoticeHelpViewListArticles"
                  @refresh-Board="getNoticeHelpViewListArticles"/>
              
            </v-col>
          </v-row>
        </v-card-text>
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

.NoticeTable{
  width: 80%;
}
.table-header{
  width: auto;
  background-color: white;

}


</style>







