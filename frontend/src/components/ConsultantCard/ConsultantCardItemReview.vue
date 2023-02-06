<template>
  <div>
    <!-- {{ counselorData }} -->
    <ConsultantCardItemReviewItem
    v-for="(review, idx) in reviews"
    :key="idx"
    :idx="idx"
    :review="review"
    :limit="page"/>
    <div class="text-center">
      <v-pagination
        v-model="page"
        :length="6"
      ></v-pagination>
    </div>
    <v-btn outlined rounded text @click="moveTo">
      후기작성
    </v-btn>
  </div>
</template>

<script>
import axios from 'axios'
import ConsultantCardItemReviewItem from '@/components/ConsultantCard/ConsultantCardItemReviewItem.vue'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'ConsultantCardItemReview',
  components:{
    ConsultantCardItemReviewItem
  },
  props:{
    counselorData:Object
  },
  data(){
    return{
      reviews:null,
      clientId:null,
      counselorId:null,
      type:null,
      page:1
    }
  },
  methods:{
    moveTo(){
      this.$router.push({ name: 'consultantcarditemreviewcreate', params: {id: this.counselorData.id}  })
    },
    getReview(){
      axios({
        method:'post',
        url: `${VUE_APP_API_URL}/api/review/my`,
        data:{
          counselorId:this.counselorData.id,
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        console.log(res)
        this.reviews = res.data.content
      })
    }
  },
  created(){
    this.getReview()
  }
}
</script>

<style>

</style>