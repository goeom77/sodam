<template>
  <v-container style="width:500px; height:300px;">

    <v-card>
      <v-card-title >
        <span class="text-h5">아이디 찾기</span>
      </v-card-title>
      <v-card-text> 
        <v-row>
          <v-col cols="12">
            <v-text-field
              label="이름을 입력해주세요"
              v-model="name"
              required
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              label="이메일을 입력해주세요"
              v-model="Email"
              type="Email"
              required
            ></v-text-field>
          </v-col>
        </v-row>

      </v-card-text>
      <v-card-actions>
        <!-- 오른쪽 끝으로 이동 -->
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text
          @click="[findID(),dialog = true]">
          확인
        </v-btn>

        <v-dialog
          v-model="dialog"
        >
          <v-card>
            <v-card-text>
              {{ this.name }}님의 아이디는 {{  this.showData }}입니다.
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" block @click="[moveBack(),dialog = false]">로그인 창으로</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-btn color="blue darken-1" text
          @click="moveBack">
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
      name: null,
      Email: null,
      showData:null
    }
  },
  methods:{
    moveBack(){
      this.$router.push({ name: 'login' })
    },
    findID(){
      axios({
        method:'post',
        url: `${VUE_APP_API_URL}/api/auth/find-id`,
        data:{
          name:this.name,
          email:this.Email,
        }
      })
      .then(res=>{
        this.showData = res.data.id
      })
    }
  //   findId(){
  //     const ID = this.ID
  //     const Email = this.Email

  //     const pack = {
  //       ID: ID,
  //       Email: Email
  //     }
  //     this.$store.dispatch('findId', pack)
  //   }
  }
}
</script>

<style>


</style>