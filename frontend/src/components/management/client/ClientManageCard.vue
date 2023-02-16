<template>
  <v-card
    class="mx-auto mb-3"
    max-width=90%
  >

  <v-card-title>
    {{ clientData.name }}
    </v-card-title>

    <v-card-subtitle>
      <p><v-icon icon="mdi-phone"></v-icon> {{ clientData.tel }}</p>
      <p><v-icon icon="mdi-email"></v-icon>{{ clientData.email }}</p>
    </v-card-subtitle>
  <v-card-text>
    </v-card-text>
    
    <div class="d-flex justify-space-between ms-3">
    <v-card-actions>
      <v-btn
        text
        color="deep-purple accent-4"
        @click="moveTo"
      >
      자세히보기
      
      </v-btn>
    </v-card-actions>
    <div>
      <!-- v-if 상담 기간인 경우 -->
      <!-- 상담사랑 고객의 키값을 비교 -->
      <v-btn
        text
        color="red accent-4"
      >
      <p @click="moveTo2">상담하기 </p>

      </v-btn>
    </div>
  </div>
  </v-card>
</template>

<script>
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

import axios from "axios";
// const APPLICATION_SERVER_URL = process.env.APPLICATION_SERVER_URL
// const OPENVIDU_SERVER_SECRET = "SODAM";

export default {
  name:'ClientManageCard',
  props:{
    clientData:Object,
    sessionInfo:Object
  },
  data(){
    return{
      consultant_id: this.$store.state.payload.id,
    }
  },
  created() {
    this.getCounselorData()
    // 상담사 네임을 기입
  },
  methods:{
    // 컨설턴트 정보 가져오기
    getCounselorData(){
      axios({
        method:'get',
        url:`${VUE_APP_API_URL}/api/counselor/${this.consultant_id}`,
        data:{
          id: this.consultant_id
        },        
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        this.consultant_name = res.data.name
      })
    },
    moveTo() {
      this.$router.push({ name: 'ClientManageDetail', params: {id: this.clientData.email }  })
    },
    // 세션 id 내려주기 (지금 구현 안되어 있음)
    moveTo2(){
      this.$router.push({ name: 'VideoPage' })
    }
  },
}
</script>

<style>

</style>