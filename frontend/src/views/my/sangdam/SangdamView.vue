<template>
  <div>
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
  //   this.consultssssion()
  // },
  methods: {
    gogogogogogo() {
      this.$router.push({ name: 'VideoPage' },)
    },
    // consultssssion() {
    //     axios({
    //       method: 'get',
    //       url: `${VUE_APP_API_URL}/api/client/${this.$store.state.payload.id}/cunsult`,
    //       headers: {
    //         Authorization : `Bearer ${this.$store.state.token.token.access_token}`
    //       }
    //     })
    //     .then(() => {
    //       this.history = res.data.content
    //       console.log('되냐 상담 내역?')
    //     })
    //     .catch((err) => {
    //       console.log('안된다 상담내역')
    //     })
    //   },
  }
}
</script>

<style>

</style>