<template>
  <div v-if="this.reviewData">
    {{ this.reviewData }}
    <br>
    상담 분야 : {{ this.reviewData.type }}
    <br>
    평점 : {{ this.reviewData.stars }}
    <br>
    제목 : {{ this.reviewData.title }}
    <br>
    내용 : {{ this.reviewData.contents }}
    <br>
    작성일시 : {{ this.reviewData.createdAt }}
    <br>
    조회수 : {{ this.reviewData.pastCount }}
    <br>
    
    <v-btn outlined rounded text @click="ReviewUpdate">수정</v-btn>
    <v-btn outlined rounded text @click="ReviewDelete">삭제</v-btn>
  </div>
</template>

<script>
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL
  
export default {
    name:'ConsultantCardItemReviewItemDetail',
    props:{
      id: String
    },
    data(){
      return{
        reviewData:null,
      }
    },
    methods:{
      getReviewInfo(){
        axios({
          method:'get',
          url:`${VUE_APP_API_URL}/api/review/search?id=${this.id}`,
          data: {
            id: this.id,
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(res=>{
          console.log(res.data)
          this.reviewData = res.data
        })
      },
      ReviewUpdate(){
        this.$router.push({ name: 'ConsultantCardItemReviewUpdate', params: {id: this.id}  })
      },
      ReviewDelete(){
        axios({
          method:'delete',
          url:`${VUE_APP_API_URL}/api/review/review?reviewId=${this.id}`,
          data: {
            reviewId: this.id,
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(res=>{
          this.$router.go(-1);
        })
      }
    },
    created(){
      this.getReviewInfo()
    }
}
</script>

<style>

</style>