<template>
  <div id="AlarmViewListSangdam" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in SangdamAlarmViewarticles.content"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        :limit="AlarmViewListPage"
        @delete-alarm="getSangdamAlarmArticles"
        @refresh-alarm="getSangdamAlarmArticles"
      /> 
    </div>

    <div v-if="SangdamAlarmViewarticles" class="text-center">
      <v-pagination
        v-model="this.AlarmViewListPage"
        :length="5"
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
      AlarmViewListPage: 1,
      SangdamAlarmViewarticles: [], 
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
          console.log('이거 되라 제발')
          this.SangdamAlarmViewarticles = res.data
        })
        .catch((err) => {
          console.log('어림도 없지')
        })
    },
  }
}
</script>

<style>
#AlarmViewListAll {
  width: 1255px;
}



</style>