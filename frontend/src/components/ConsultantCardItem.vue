<template>
  <div>
    <div>
      <ConsultantCardItemInfo
      :counsel="counsel"/>
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
          >
          <ConsultantCardItemReserve
          :counsel="counsel"/>
          </v-window-item>

          <v-window-item  
            value="two"
          >
          <ConsultantCardItemReview/>
          </v-window-item>
        </v-window>
      </v-card-text>
    </v-card>     
</template>

<script>
import ConsultantCardItemReserve from '@/components/ConsultantCardItemReserve.vue'
import ConsultantCardItemReview from '@/components/ConsultantCardItemReview.vue'
import ConsultantCardItemInfo from '@/components/ConsultantCardItemInfo.vue'

export default {
    name:'ConsultantCardItem',
    components: {
      ConsultantCardItemReserve,
      ConsultantCardItemReview,
      ConsultantCardItemInfo
    },
    data(){
      const id = this.$route.params.id
      return{
        id:id
      }
    }
    // data(){
    //   const counselString = this.$route.params.dataCounselor;
    //   const counsel = JSON.parse(counselString);
    //   return{
    //     counsel: counsel,
    //     tab:null
    //   }
    // },
}
</script>

<style>

</style>