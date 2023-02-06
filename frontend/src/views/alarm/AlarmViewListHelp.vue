<template>
  <div id="AlarmViewListHelp" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in HelpAlarmViewarticles.content"
        :key="AlarmViewarticle.postId"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        :limit="AlarmViewListPage"
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
      contentlist: [], 
    }
  },
  components: {
    AlarmViewListItem
  },
  computed: {
    HelpAlarmViewarticles() {
      return this.$store.state.HelpAlarmViewarticles
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