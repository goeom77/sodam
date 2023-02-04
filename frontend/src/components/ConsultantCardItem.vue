<template>
  <div>
    <div>
      <ConsultantCardItemInfo
      :counselorData="this.counselorData"/>
    </div>
  </div>
    
    <v-card>
      <v-tabs v-model="tab">
        <v-tab value="one">
          상담 예약
        </v-tab>
        <v-tab value="two">
          상담 후기 
        </v-tab>
      </v-tabs>
      <v-card-text>
        <v-window v-model="tab">
          <v-window-item
          value="one"
          style="height:1500px"
          >
          <ConsultantCardItemReserve
          :counselorData="this.counselorData"/>
          </v-window-item>

          <v-window-item  
            value="two"
            style="height:1500px"
          >
          <ConsultantCardItemReview
          :counselorData="this.counselorData"/>
          </v-window-item>
        </v-window>
      </v-card-text>
    </v-card>     
</template>

<script>
import ConsultantCardItemReserve from '@/components/ConsultantCardItemReserve.vue'
import ConsultantCardItemReview from '@/components/ConsultantCardItemReview.vue'
import ConsultantCardItemInfo from '@/components/ConsultantCardItemInfo.vue'
import axios from 'axios'

const API_URL = 'http://127.0.0.1:8080'
export default {
    name:'ConsultantCardItem',
    components: {
      ConsultantCardItemReserve,
      ConsultantCardItemReview,
      ConsultantCardItemInfo
    },
    props:{
      id:String
    },
    data(){
      return{
        tab:null,
        counselorId:null,
        counselorData:[],
      }
    },
    methods:{ 
      changePropstoData(){
        this.counselorId = this.id
      },  
      getCounselorData(){
        axios({
          method:'get',
          url:`${API_URL}/api/counselor/${this.id}`,
          data:{
            id:this.counselorId
          }
        })
        .then(res=>{
          this.counselorData = res.data
        })
      }
    },
    created() {
      this.changePropstoData()
      this.getCounselorData()
  }
}
</script>

<style>

</style>