<template>
  <div>
    <!-- <div> 여긴 상담내역 {{ history }} </div> -->
    <button @click="startMeeting"> 상담하기 </button>
    <!-- <h1>{{ this.history }}</h1> -->
  </div>
</template>

<script>
export default {
  name: 'SangdamView',
  data() {
      return{
        userId: undefined,
        history : null
      }
    },
  components: {
  },
  created() {
    this.userInfo();
    // this.getscheduledetail()
  },
  methods: {
    startMeeting(id) {
      this.$router.push({name: 'VideoPage', params: {id: id}});
    },
    userInfo() {
      // this.common_code = this.$store.state.payload.common_code
      this.userId = this.$store.state.payload.id
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