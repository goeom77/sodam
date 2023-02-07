<template>
  <div>
    {{ id }}
    {{ reviewData }}
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
    methods:{
      getReviewInfo(){
        axios({
          method:'get',
          url:`${VUE_APP_API_URL}/api/review/search?id=${this.id}`,
          data: {
            id: this.id,
            reviewData:null,
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(res=>{
          this.reviewData = res.data
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