<template>
  <div id="AlarmViewListHelp" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in HelpAlarmViewarticles.content"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        :limit="AlarmViewListPage"
        @delete-alarm="getHelpAlarmArticles"
        @refresh-alarm="getHelpAlarmArticles"
      /> 
    </div>

    <div v-if="HelpAlarmViewarticles" class="text-center">
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
  name: 'AlarmViewListHelp',
  data() {
    return {
      AlarmViewListPage: 1,
      HelpAlarmViewarticles: [], 
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
          console.log('이거 되라 제발')
          this.HelpAlarmViewarticles = res.data
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