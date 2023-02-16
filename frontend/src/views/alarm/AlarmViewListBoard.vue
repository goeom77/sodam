<template>
  <div id="AlarmViewListBoard" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in BoardAlarmViewarticles"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        @delete-alarm="getBoardAlarmArticles"
        @refresh-alarm="getBoardAlarmArticles"
      /> 
    </div>

    <div class="text-center pt-2">
      <v-pagination
        v-model="page"
        :length="totalPages"
      ></v-pagination>
    </div>
  </div>

</template>

<script>

import axios from 'axios'
import AlarmViewListItem from '../alarm/AlarmViewListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'AlarmViewListBoard',
  data() {
    return {
      BoardAlarmViewarticles: [],
      page: 1,
      totalPages: 1,
      offset: 1,
      pageSize: 1,
    }
  },
  components: {
    AlarmViewListItem
  },
  // computed: {
  //   BoardAlarmViewarticles() {
  //     return this.$store.state.BoardAlarmViewarticles
  //   },
  // },
  created() {
    this.getBoardAlarmArticles()
    // this.getHelpAlarmArticles()
    // this.getGuitarAlarmArticles()  
  },
  methods: {
    // getBoardAlarmArticles() {
    //   console.log('게시판알람 알람뷰')
    //   this.$store.dispatch('getBoardAlarmArticles')
    // },
    getBoardAlarmArticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification?type=2`,
        headers: {
          "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          this.BoardAlarmViewarticles = res.data.content
          this.totalPages = res.data.totalPages
          this.offset = res.data.pageable.offset
          this.page = res.data.pageable.pageNumber
          this.pageSize = res.data.pageable.pageSize
        })
        .catch((err) => {
          console.log(err)
        })
    },
  }
}
</script>

<style>



</style>