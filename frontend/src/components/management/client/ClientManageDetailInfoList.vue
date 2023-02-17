<template>
  <div>

  <v-card
    class="mx-auto mb-3"
    max-width=90%
  >
  
  <div class="d-flex flex-no-wrap justify-space-between">
    <div>
      <v-card-title>
      session ID : {{ info.sessionId }}
    </v-card-title>

    <v-card-text>
      <p>회차 : {{ info.turn }}</p>
      <p>상담 기한  : {{ info.dueDate }}</p>
    </v-card-text>
    </div>

    <div class="mt-3 mr-3">
      <div v-if="info.state==='WAIT'">
        <v-btn outlined rounded text @click="changeState">신청 확인</v-btn>
      </div>
      <div v-else>
        <v-btn outlined rounded text @click="changeState2">취소</v-btn>
      </div>
    </div>
  </div>

    <v-card-actions v-if="info.state==='APPROVED'">
      <v-btn
        variant="text"
        width="100%"
        @click="show = !show"
      >
      상담 기록 보기
      <v-icon :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"></v-icon>
      </v-btn>


      <v-btn
        :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
        @click="show = !show"
      ></v-btn>
    </v-card-actions>
    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-actions>
          <div v-if="info.state==='APPROVED'">
                    <v-dialog
              v-model="dialog"
              width="50%"
            >
              <template v-slot:activator="{ props }">
                <v-btn
                  color="primary"
                  v-bind="props"
                >
                상담기록 상세보기
                </v-btn>
              </template>

              <v-card>
                <v-card-title>상담기록 암호키 입력</v-card-title>
                <v-text-field label="key*" v-model="key" variant="outlined" required></v-text-field>
                <v-card-actions>
                  <v-btn color="primary" block @click="getConsultDetail">확인</v-btn>
                </v-card-actions>
                <v-card-actions>
                  <v-btn color="primary" block @click="dialog = false">Close Dialog</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>


          <div v-else>
            대기중
          </div>
        </v-card-actions>

        <v-card-text>
          I'm a thing. But, like most politicians
          </v-card-text>
      </div>
    </v-expand-transition>

  </v-card>


    <!-- <v-expansion-panels>
  <v-expansion-panel
    title="지난 기록 보기"
    text="Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi, ratione debitis quis est labore voluptatibus! Eaque cupiditate minima"
  >
  </v-expansion-panel>
</v-expansion-panels> -->
    <!-- --------------------------
    {{ this.consult_id }}
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
    </div> -->


  </div>


</template>




<script>
import axios, { HttpStatusCode } from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
const VUE_APP_STT_API_URL = process.env.VUE_APP_STT_API_URL

export default {
  name:'ClientManageDetailInfo',
  data(){
    return{
      consult_id:this.$store.state.payload.id,
      show: false,
      dialog: false,
      key:'',
      schedule_id: 107
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
            detail: null
          }
        }
      })
      .then(res=>{
        console.log(res)
      })
    },
    
    // moveTo() {
    //   console.log(this.key)
    //   // this.$store.commit('SET_STT_KEY', this.key)
    //   this.getConsultDetail
    // this.$router.push({name:'ConsultDetail', params: { id: '107'}  })
    // },
    getConsultDetail() {
            console.log(this.schedule_id)
            console.log(`${VUE_APP_STT_API_URL}`)
            axios({
                method:'post',
                url: `${VUE_APP_STT_API_URL}/stt/text`,
                data:{
                    schedule_id:this.schedule_id,
                    key:this.key,
                }
            })
            .then(res=>{
                console.log(res)
                this.$store.commit('SET_STT_KEY', this.key)
                this.$store.commit('SET_STT_DATA', res.data)
                this.$router.push({name:'ConsultDetail', params: { id: this.schedule_id}  })
            })
            .catch(error => {
                if (error.response) {
                    console.log(error.response);
                    console.log(error.response.status);
                    if(error.response.status == HttpStatusCode.Unauthorized) this.$swal("키 오류");
                    
                }
                
            });
        }
  },

}
</script>

<style>

</style>