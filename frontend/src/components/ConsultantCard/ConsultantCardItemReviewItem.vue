<template>
  <div  v-if="(limit-1)*10 <= idx && idx < limit * 10">
    {{ review }}
    <v-card
    class="mx-auto"
    max-width="400"
  >
      <v-card-title>
        <div>
          {{review.title}}
        </div>
        <v-card-subtitle class="pt-4">
          {{ review.clientId }}
        </v-card-subtitle>
      </v-card-title>
      <v-card-text>
        <div>{{review.contents}}</div>
      </v-card-text>
      <v-btn outlined rounded text @click="goDetail">자세히 보기</v-btn>
      <v-btn outlined rounded text @click="reviewUpdate">수정</v-btn>
      <v-btn outlined rounded text @click="ReviewDelete">삭제</v-btn>
      
    </v-card>
  </div>
</template>

<script>
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'ConsultantCardItemReviewItem',
  props:{
    review:Object,
    idx:Number,
    limit:Number
  },
  methods:{
    goDetail(){
      this.$router.push({name:'ConsultantCardItemReviewItemDetail', params:{id: this.review.id}})
    },
    reviewUpdate(){
      const clientId = review.clientId
      const contents = string
      const counselorId = string
      const id = 0
      const pastCount = 0
      const stars = 0
      const title = "string"
      const type = "string"

      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/review/${reviewId}`
      })
    },
    ReviewDelete(){

    }
  }
}
</script>

<style>

</style>