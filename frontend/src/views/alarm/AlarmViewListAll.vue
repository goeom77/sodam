<template>
  <div id="AlarmViewListAll" >
    <div id="alarmList">
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in AlarmViewarticles"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        @delete-alarm="getAlarmArticles"
        @refresh-alarm="getAlarmArticles"
      /> 
    </div>

    <div v-if="AlarmViewarticles" class="text-center">
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
  name: 'AlarmViewListAll',
  data() {
    return {
      AlarmViewarticles: [],
      // pagination
      page: 1,
      totalPages: 1,
      offset: 1,
      pageSize: 1,
    }
  },
  components: {
    AlarmViewListItem
  },
  mounted() {
    this.getAlarmArticles()
  },
  methods: {
    getAlarmArticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification`,
        headers: {
          "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          this.AlarmViewarticles = res.data.content
          this.totalPages = res.data.totalPages
          this.offset = res.data.pageable.offset
          this.page = res.data.pageable.pageNumber
          this.pageSize = res.data.pageable.pageSize
        })
        .catch((err) => {
          console.log(err);
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