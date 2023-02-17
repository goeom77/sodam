<template>
  <div id="fh5co-main">
    <div class="fh5co-narrow-content">
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
    
    <div v-if="this.reviewData.counselorId === this.$store.state.payload.id">
      <v-btn outlined rounded text @click="ReviewUpdate">수정</v-btn>
      <v-btn outlined rounded text @click="ReviewDelete">삭제</v-btn>
    </div>
  </div>
</div>
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
          this.$router.push({ name: 'consultantcarditem', params: {id: this.$store.state.userSignupData.id }  })
        })
      }
    },
    created(){
      console.log("created")
      this.getReviewInfo()
    }
}
</script>

<style>
#fh5co-main {
  width: 85%;
  float: right;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
@media screen and (max-width: 1200px) {
  #fh5co-main {
    width: 70%;
  }
}
@media screen and (max-width: 768px) {
  #fh5co-main {
    width: 100%;
  }
}
#fh5co-main .fh5co-narrow-content {
  position: relative;
  width: 80%;
  margin: 0 auto;
  padding: 4em 0;
}
@media screen and (max-width: 768px) {
  #fh5co-main .fh5co-narrow-content {
    width: 100%;
    padding: 3.5em 1em;
  }
} 
</style>