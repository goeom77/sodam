<template>
  <div>
    {{ id }}
    <ClientManageDetailInfo
    :id="id"/>
  </div>
</template>

<script>
import ClientManageDetailInfo from '@/components/management/client/ClientManageDetailInfo.vue'

export default {
  name:'ClientManageDetail',
  components:{
    ClientManageDetailInfo
  },
  props:{
    // id = clientData.email임
    id:String
  },
  data(){
    return{

    }
  },
  methods:{
    
  }
}
</script>

<style>

</style>