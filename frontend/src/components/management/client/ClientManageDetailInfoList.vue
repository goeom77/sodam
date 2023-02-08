<template>
  <div>
    --------------------------
    {{ this.consult_id }}rr
    {{ this.info.sessionId }}
    {{  }}
    <p>sessionId : {{ info.sessionId }}</p>
    <p>이름 : {{ info.name }}</p>
    <p>type : {{ info.consultType }}</p>
    <p>전화번호 : {{ info.tel }}</p>
    <p>이메일 : {{ info.email }}</p>
    <p>상태 : {{ info.state }}</p>
    <p>완료 여부 : {{ info.isConsult }}</p>
    <p>회차 : {{ info.turn }}</p>
    <p>상담 기한  : {{ info.dueDate }}</p>

    <div v-if="info.state==='WAIT'">
      <v-btn outlined rounded text @click="changeState">신청 확인</v-btn>
    </div>
    <div v-else>
      <v-btn outlined rounded text @click="changeState2">취소</v-btn>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'ClientManageDetailInfo',
  data(){
    return{
      consult_id:this.$store.state.payload.id
    }
  },
  props:{
    info:Object
  },
  methods:{
    changeState(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/consult-session/${this.consult_id}`,
        data:{
          consult_id:this.consult_id,
          request:{
            sessionId: this.info.sessionId,
            state: 'APPROVED',
          }
        }
      })
      .then(res=>{
        console.log(res)
      })
      .catch(res=>{
        console.log('왜 안되지  ')
        console.log(res)
      })
    },
    changeState2(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/consult-session/${this.consult_id}`,
        data:{
          consult_id:this.consult_id,
          request:{
            sessionId: this.info.sessionId,
            state: 'WAIT',
          }
        }
      })
      .then(res=>{
        console.log(res)
      })
    }
  },
}
</script>

<style>

</style>