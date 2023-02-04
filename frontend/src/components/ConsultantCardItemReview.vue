<template>
  <div>
    <!-- {{ counselorData }} -->

    {{ this.review }}
    <button @click="moveTo">후기 작성</button>

  </div>
</template>

<script>
import axios from 'axios'

const API_URL = 'http://127.0.0.1:8080'
export default {
  name:'ConsultantCardItemReview',
  props:{
    counselorData:Object
  },
  data(){
    return{
      review:null,
      clientId:null,
      counselorId:null,
      type:null,
    }
  },
  methods:{
    moveTo(){
      this.$router.push({ name: 'consultantcarditemreviewcreate', params: {id: this.counselorData.id}  })
    },
    getReview(){
      axios({
        method:'post',
        url: `${API_URL}/api/review/my`,
        data:{
          counselorId:this.counselorData.id,
          review:null,
        }
      })
      .then(res=>{
        console.log(res)
        this.review = res.content
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