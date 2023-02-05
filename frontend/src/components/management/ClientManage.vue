<template>
  <div v-if="isLogin===true">
    {{ this.id }}
    {{ this.sessionInfo }}
    <client-manage-detail
    v-for="(clientData,idx) in this.sessionInfo"
    :key="idx"
    :clientData="clientData"/>

  </div>
</template>

<script>
import ClientManageDetail from '@/components/management/ClientManageDetail.vue'
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'ClientManage',
  components:{
    ClientManageDetail
  },
  data(){
    return{
      sessionInfo: null,
      id: this.$store.state.payload.id
    }
  },
  methods:{
    getSessionInfo(){
      axios({
        method:'get',
        url:`${VUE_APP_API_URL}/api/consultApplicant?counselorId=${this.id}`,
        data:{
          counselorId : this.id
        }
      })
      .then(res=>{
        this.sessionInfo = res.data
        console.log(res)
      })
    },
  },
  computed:{
    isLogin(){
      return this.$store.getters.isLogin
    }
  },
  created() {
    this.getSessionInfo()
  }
    
}
</script>

<style>

</style>