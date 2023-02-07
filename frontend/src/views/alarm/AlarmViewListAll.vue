<template>
  <div id="AlarmViewListAll" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in AlarmViewarticles.content"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        :limit="AlarmViewListPage"
        @delete-alarm="getAlarmArticles"
        @refresh-alarm="getAlarmArticles"
      /> 
    </div>

    <div v-if="AlarmViewarticles" class="text-center">
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
  name: 'AlarmViewListAll',
  data() {
    return {
      AlarmViewListPage: 1,
      AlarmViewarticles: [], 
    }
  },
  components: {
    AlarmViewListItem
  },
  // computed: {
  //   AlarmViewarticles() {
  //     return this.$store.state.AlarmViewarticles
  //   },
  // },
  created() {
    this.getAlarmArticles()
    // this.getSangdamAlarmArticles()
    // this.getBoardAlarmArticles()
    // this.getHelpAlarmArticles()
    // this.getGuitarAlarmArticles()  
  },
  methods: {
    // getAlarmArticles() {
    //   console.log('전체알람 알람뷰')
    //   this.$store.dispatch('getAlarmArticles')
    // },
    getAlarmArticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification`,
        headers: {
          "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          console.log('이거 되라 제발')
          this.AlarmViewarticles = res.data
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