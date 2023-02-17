<template>
  <div id="AlarmViewListSangdam" >
    <div id="alarmList">
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in SangdamAlarmViewarticles"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        @delete-alarm="getSangdamAlarmArticles"
        @refresh-alarm="getSangdamAlarmArticles"
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
  name: 'AlarmViewListSangdam',
  data() {
    return {
      SangdamAlarmViewarticles: [], 
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
  //   SangdamAlarmViewarticles() {
  //     return this.$store.state.SangdamAlarmViewarticles
  //   },
  // },
  created() {
    this.getSangdamAlarmArticles()
    // this.getBoardAlarmArticles()
    // this.getHelpAlarmArticles()
    // this.getGuitarAlarmArticles()  
  },
  methods: {
    // getSangdamAlarmArticles() {
    //   console.log('상담알람 알람뷰')
    //   this.$store.dispatch('getSangdamAlarmArticles')
    // },
    getSangdamAlarmArticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification?type=1`,
        headers: {
          "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          this.SangdamAlarmViewarticles = res.data.content
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
.alarmList {
  margin: 3rem;
}
</style>