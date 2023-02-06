<template>
  <div id="AlarmViewListGuitar" >
    <div>
      <AlarmViewListItem
        v-for="(AlarmViewarticle, index) in GuitarAlarmViewarticles.content"
        :key="AlarmViewarticle.id"
        :AlarmViewarticle="AlarmViewarticle"
        :index="index"
        :limit="AlarmViewListPage"
      /> 
    </div>

    <div v-if="GuitarAlarmViewarticles" class="text-center">
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
  name: 'AlarmViewListGuitar',
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
    GuitarAlarmViewarticles() {
      return this.$store.state.GuitarAlarmViewarticles
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