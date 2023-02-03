<template>
  <div>
    {{ this.id }}
    {{ this.sessionInfo }}
    <client-manage-detail
    :sessionInfo="sessionInfo"/>

  </div>
</template>

<script>
import ClientManageDetail from '@/components/management/ClientManageDetail.vue'
import axios from 'axios'

const API_URL = 'http://127.0.0.1:8080'
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
        url:`${API_URL}/api/consult-session`,
        data:{
          id: this.id
        }
      })
      .then(res=>{
        this.sessionInfo = res.data
      })
    },
  },
  created() {
    this.getSessionInfo()
  }
    
}
</script>

<style>

</style>