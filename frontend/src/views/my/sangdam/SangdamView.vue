<template>
  <div>
    <SangdamList
      v-for="(data, index) in datas"
      :key="index"
      :dateTime="data.dateTime"
      :sessionId="data.sessionId"
    />
  </div>
</template>

<script>
import SangdamList from '@/views/my/sangdam/SangdamList.vue'
import axios from "axios";
const VUE_APP_API_URL = process.env.VUE_APP_API_URL;

export default {
  name: 'SangdamView',
  data() {
      return{
        userId: undefined,
        history : null,
        datas: [],
        openviduId: undefined,
      }
    },
  components: {
    SangdamList
  },
  created() {
    this.userInfo();
    this.getSchedules();
  },
  methods: {
    userInfo() {
      // this.common_code = this.$store.state.payload.common_code
      this.userId = this.$store.state.payload.id
    },
    getSchedules() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/schedule/schedules?userId=${this.userId}`,
        // headers: {
        //   "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`},
      })
      .then((res) => {
        // console.log(res.data.content)
        this.datas = res.data.content
      })
      .catch((err) => {
          console.log(err)
      })
    },
  }
}
</script>

<style>

</style>