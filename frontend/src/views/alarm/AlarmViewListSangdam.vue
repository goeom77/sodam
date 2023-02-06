<template>
  <div id="AlarmViewListSangdam" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in SangdamAlarmViewarticles.content"
        :key="AlarmViewarticle.postId"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        :limit="AlarmViewListPage"
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
      contentlist: [], 
    }
  },
  components: {
    AlarmViewListItem
  },
  computed: {
    SangdamAlarmViewarticles() {
      return this.$store.state.SangdamAlarmViewarticles
    },
  },
  methods: {

  }
}
</script>

<style>
#AlarmViewListAll {
  width: 1255px;
}



</style>