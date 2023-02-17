<template>
  <div>
    <!-- <div> 여긴 상담내역 {{ history }} </div> -->
    <button @click="gogogogogogo"> 영상 연결 ㄱㄱ</button>
    <!-- <h1>{{ this.history }}</h1> -->
  </div>
</template>

<script>
import axios from "axios";
import {param} from "jquery";

const VUE_APP_API_URL = process.env.VUE_APP_API_URL;
export default {
  name: 'SangdamView',
  data() {
    return {
      history: null,
      userId: null,
      sessionId:null,
      videos:null
    }
  },
  components: {},
  created() {
    this.userInfo()
  },
  methods: {
    gogogogogogo() {
      this.$router.push({name: 'VideoPage', param: {id: this.videos.sessionId}})
    },
    getSchedules() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/schedule/schedules?userId=${this.userId}`,
      })
          .then(res => {
            console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~여기 시작한다"+JSON.stringify(res.data.content))
            this.videos = res.data.content
            console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~여기 끝난다")
          })
    },
    // getscheduledetail() {
    //   axios({
    //     method: 'get',
    //     url: `${VUE_APP_API_URL}/api/schedule/detail`,
    //     headers: {
    //       Authorization : `Bearer ${this.$store.state.token.token.access_token}`
    //     }
    //   })
    //   .then(() => {
    //     this.history = res.data.
    //     console.log('갖고왔다 상담 내역?')
    //   })
    //   .catch((err) => {
    //     console.log('안된다 상담내역')
    //   })
    // },
    userInfo() {
      console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~userInfo")
      // this.common_code = this.$store.state.payload.common_code
      this.userId = this.$store.state.payload.id
      console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~userInfo " + this.userId)
      this.getSchedules()
    },
  }
}
</script>

<style>

</style>