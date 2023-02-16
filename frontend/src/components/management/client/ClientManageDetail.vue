<template>
    <v-container style="width:calc(60%); height:800px; margin-top: 5%;">
    <ClientManageDetailInfo
    :id="id"

    />
    </v-container>
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