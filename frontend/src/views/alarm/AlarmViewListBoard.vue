<template>
  <div id="AlarmViewListBoard" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in BoardAlarmViewarticles.content"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        :limit="AlarmViewListPage"
        @delete-alarm="getBoardAlarmArticles"
        @refresh-alarm="getBoardAlarmArticles"
      /> 
    </div>

    <div v-if="BoardAlarmViewarticles" class="text-center">
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
  name: 'AlarmViewListBoard',
  data() {
    return {
      AlarmViewListPage: 1,
      BoardAlarmViewarticles: [], 
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
          console.log('이거 되라 제발')
          this.BoardAlarmViewarticles = res.data
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