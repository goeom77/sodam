<template>
  <div>
    <ConsultantCardItemReviewItem
    v-for="(review, idx) in reviews"
    :key="idx"
    :review="review"/>
    <v-btn outlined rounded text @click="moveTo" v-show="this.$store.state.payload.common_code == '1'">
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
      counselorId:"",
      type:null,
      page:1
    }
  },
  methods:{
    moveTo(){
      this.$router.push({ name: 'consultantcarditemreviewcreate', params: {id: this.$store.state.userSignupData.id }  })
    },
    getReview(){
      axios({
        method:'post',
        url: `${VUE_APP_API_URL}/api/review/my/counselor`,
        data:{
          counselorId: this.counselorId
        },
      })
      .then(res=>{
        console.log(res)
        this.reviews = res.data.content
      })
      .catch(res=>{
        console.log(res)
        console.log('안직힘   ')
      })
    },
  },
  beforeMount(){
    this.counselorId = this.$store.state.userSignupData.id;
    this.getReview()
  }
}
</script>

<style>

</style>