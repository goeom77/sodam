<template>
  <div>
    <h1>Detail</h1>
    <p>글 번호 : {{ KidBoardarticle?.id }}</p>
    <p>제목 : {{ KidBoardarticle?.title }}</p>
    <p>내용 : {{ KidBoardarticle?.content }}</p>
    <p>작성시간 : {{ KidBoardarticle?.created_at }}</p>
    <p>수정시간 : {{ KidBoardarticle?.updated_at }}</p>
  </div>

</template>

<script>
// @ is an alias to /srcz
import axios from 'axios'

const API_URL = 'http://127.0.0.1:8000'

export default {
  name: 'KidBoardDetail',
  data() {
    return {
      KidBoardarticle: null,
    }
  },
  created() {
    this.getKidBoardarticleDetail()
  },
  methods: {
    getKidBoardarticleDetail() {
      axios({
        method: 'get',
        url: `${API_URL}/backend/${this.$route.params.id}`
      })
        .then((res) => {
          console.log(res)
          this.KidBoardarticle = res.data
        })
        .catch((err) => {
          KidBoardarticle.log(err)
        })
    }
  }
}

</script>


<style>

</style>