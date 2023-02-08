<template>
  <ClientManageDetailInfoList
    v-for="(info, idx) in clientInfo"
    :key="idx"
    :info="info"
  />

</template>

<script>
import ClientManageDetailInfoList from './ClientManageDetailInfoList.vue'
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'ClientManageDetailInfo',
  components:{
    ClientManageDetailInfoList
  },
  data(){
    return {
      counselorId:this.$store.state.payload.id,
      clientInfo:[],
    }
  },
  props:{
        // id = clientData.email임
    id:String
  },
  methods:{
    getUserData(){
      axios({
        method:'get',
        url:`${VUE_APP_API_URL}/api/consultApplicant?counselorId=${this.counselorId}`,
        data:{
          counselorId: this.counselorId
        }
      })
      .then(res=>{
        const total = res.data
        const result = total.filter(index => index.email === this.id);
        
        this.clientInfo = result 
        // this.clientInfo = res.data
      })
    },

  },
  created(){
    this.getUserData()
  }
}
</script>

<style>

</style>