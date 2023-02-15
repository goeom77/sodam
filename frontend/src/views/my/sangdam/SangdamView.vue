<template>
  <div>
    <!-- <div> 여긴 상담내역 {{ history }} </div> -->
    <button @click="gogogogogogo"> 영상 연결 ㄱㄱ</button>
    <!-- <h1>{{ this.history }}</h1> -->
  </div>
</template>

<script>
export default {
  name: 'SangdamView',
  data() {
      return{
        history : null
      }
    },
  components: {
  },
  // created() {
  //   this.getscheduledetail()
  // },
  methods: {
    gogogogogogo() {
      this.$router.push({ name: 'VideoPage' },)
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
    // }
  }
}
</script>

<style>

</style>