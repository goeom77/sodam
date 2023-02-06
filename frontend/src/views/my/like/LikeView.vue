<template>
  {{ this.clientId }}
  <div v-if="this.favList">
    <LikeViewCard/>
  </div>
  <div v-else>
    등록한 상담사가 없습니다
  </div>
</template>

<script>
import axios from 'axios'
import LikeViewCard from './LikeViewCard.vue';

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'Like',
  components:{
    LikeViewCard
  },
  data(){
    return{
      clientId:this.$store.state.payload.id,
      favList: null
    }
  },
  methods:{
    getFavList(){
      axios({
        method:'post',
        url: `${VUE_APP_API_URL}/api/client/${this.clientId}/fav`,
        data:{
          clientId: this.clientId
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        console.log(res)
        this.favList = res
      })
    } 
  },
  created(){
    this.getFavList()
  }
}
</script>

<style>

</style>