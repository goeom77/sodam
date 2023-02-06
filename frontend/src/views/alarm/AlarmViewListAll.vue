<template>
  <div id="AlarmViewListAll" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in AlarmViewarticles.content"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        :limit="AlarmViewListPage"
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
      contentlist: [], 
    }
  },
  components: {
    AlarmViewListItem
  },
  computed: {
    AlarmViewarticles() {
      return this.$store.state.AlarmViewarticles
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