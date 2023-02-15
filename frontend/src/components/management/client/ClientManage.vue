<template>

  <div id="fh5co-main">
    <div class="fh5co-narrow-content">
      <div v-if="isLogin===true">
        <!-- {{ this.sessionInfo }} -->
        <ClientManageCard
        v-for="(clientData,idx) in this.sessionInfo"
        :key="idx"
        :clientData="clientData"
        :sessionInfo="this.sessionInfo"
        />
      </div>
    </div>
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
      id: this.$store.state.payload.id,
      clientInfo:null,
    }
  },
  methods:{
    getSessionInfo(){
      axios({
        method:'get',
        url:`${VUE_APP_API_URL}/api/myclient/${this.id}`,
        // url:`${VUE_APP_API_URL}/api/consult-session?counselorId=${this.id}`,
        data:{
          counselorId : this.id
          
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