<template>
  <div id="AlarmViewListBoard" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in BoardAlarmViewarticles.content"
        :key="AlarmViewarticle.postId"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        :limit="AlarmViewListPage"
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
      contentlist: [], 
    }
  },
  components: {
    AlarmViewListItem
  },
  computed: {
    BoardAlarmViewarticles() {
      return this.$store.state.BoardAlarmViewarticles
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