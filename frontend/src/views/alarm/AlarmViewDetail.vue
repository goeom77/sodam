<template>
  <div>
    <div>
      <h1>AlarmViewDetail</h1>
      <p>글 번호 : {{ AlarmViewarticle?.postId }}</p>
      <p>대상 : {{ AlarmViewarticle?.category }}</p>
      <p>제목 : {{ AlarmViewarticle?.title }}</p>
      <p>내용 : {{ AlarmViewarticle?.content }}</p>
      <p>작성시간 : {{ AlarmViewarticle?.createdAt }}</p>
      <p>작성자 : {{ AlarmViewarticle?.clientId }}</p>
      <button @click="AlarmViewarticleDelete">삭제</button>
    </div>
    <div>
      <hr>


    </div>
  </div>

</template>

<script>
// @ is an alias to /srcz
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'AlarmViewDetail',
  components: {
  },
  data() {
    return {
      AlarmViewarticle: null,
    }
  },
  methods: {
    getAlarmViewarticleDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification/${this.$route.params.postId}`
      })
        .then((res) => {
          console.log(this.$route.params.postId)
          this.AlarmViewarticle = res.data
          console.log(this.AlarmViewarticle)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    AlarmViewarticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`,
        headers: { 
            Authorization: `Token ${this.$store.state.token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ name: this.KidBoardarticle?.category });
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    },
  },
  created() {
    this.getAlarmViewarticleDetail()
  },
}

</script>


<style>

</style>