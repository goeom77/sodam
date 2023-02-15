<template>
  <v-container style="width:500px; height:300px; margin-top: 5%;">

    <v-card>
      <v-card-title >
        <span class="text-h5">비밀번호 찾기</span>
      </v-card-title>
      <v-card-text> 
  
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="ID"
                v-model="id"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Email"
                type="Email"
                v-model="email"
                required
              ></v-text-field>
            </v-col>
          </v-row>
  
      </v-card-text>
      <v-card-actions>
        <!-- 오른쪽 끝으로 이동 -->
        <v-spacer></v-spacer>
        <v-btn
          color="blue darken-1"
          @click="[findPw(),dialog=true]"
          text
        >
          확인
        </v-btn>

        <v-dialog
          v-model="dialog"
        >
          <v-card>
            <v-card-text>
              이메일에 임시비밀번호가 발급되었습니다.
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" block @click="[moveBack(),dialog = false]">로그인 창으로</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-btn
          color="blue darken-1"
          text
          @click="moveBack"
        >
          취소
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
const VUE_APP_API_URL = process.env.VUE_APP_API_URL
import axios from 'axios'

export default {
  name:'findId',
  data(){
    return{
      dialog:false,
      id: null,
      email: null,
    }
  },
  methods:{
    // 비밀번호 찾기
    findPw(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/auth/find-pw`,
        data:{
          id:this.id,
          email:this.email
        }
      })
      .then(res=>{
        console.log(res)
      })
    },
    moveBack(){
        this.$router.push({ name: 'login' })
      },
  //   findPw(){
  //     const id = this.id
  //     const email = this.email

  //     const pack = {
  //       id: id,
  //       email: email
  //     }
  //     this.$store.dispatch('findPw', pack)
  //   }
  }
}
</script>

<style>
.findidform{
  width : 500px;
  height: 300px;
  background-color: white;
  border: 1px solid black;
}
</style>