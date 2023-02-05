<template>
  <div>
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
        url:`${API_URL}/api/myclient/${this.id}`,
        data:{
          consultantId : this.id
        }
      })
      .then(res=>{
        this.sessionInfo = res.data
        console.log(res)
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