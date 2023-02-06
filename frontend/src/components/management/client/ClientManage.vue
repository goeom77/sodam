<template>
  <div v-if="isLogin===true">
    {{ this.id }}
    {{ this.sessionInfo }}
    <ClientManageCard
    v-for="(clientData,idx) in this.sessionInfo"
    :key="idx"
    :clientData="clientData"/>

  </div>
</template>

<script>
import ClientManageCard from '@/components/management/client/ClientManageCard.vue'
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'ClientManage',
  components:{
    ClientManageCard
  },
  data(){
    return{
      sessionInfo: null,
    }
  },
  methods:{
    getSessionInfo(){
      axios({
        method:'get',
        // url:`${VUE_APP_API_URL}/api/consultApplicant?counselorId=${this.id}`,
        url:`${VUE_APP_API_URL}/api/myclient/${this.id}`,
        data:{
          counselorId : this.id
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        this.sessionInfo = res.data.content
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