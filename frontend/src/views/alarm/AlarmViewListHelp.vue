<template>
  <div id="AlarmViewListHelp" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in HelpAlarmViewarticles.content"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        @delete-alarm="getHelpAlarmArticles"
        @refresh-alarm="getHelpAlarmArticles"
      /> 
    </div>

    <div v-if="HelpAlarmViewarticles" class="text-center">
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
  name: 'AlarmViewListHelp',
  data() {
    return {
      HelpAlarmViewarticles: [],
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
  //   HelpAlarmViewarticles() {
  //     return this.$store.state.HelpAlarmViewarticles
  //   },
  // },
  created() {
    this.getHelpAlarmArticles()
    // this.getBoardAlarmArticles()
    // this.getHelpAlarmArticles()
    // this.getGuitarAlarmArticles()  
  },
  methods: {
    // getHelpAlarmArticles() {
    //   console.log('헬프알람 알람뷰')
    //   this.$store.dispatch('getHelpAlarmArticles')
    // },
    getHelpAlarmArticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification?type=3`,
        headers: {
          "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          this.HelpAlarmViewarticles = res.data.content
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