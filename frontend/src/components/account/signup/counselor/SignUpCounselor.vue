<template>
  <v-container style="width:500px; height:800px;">
    <v-card>
      <v-card-title >
        <span class="text-h5">회원 가입</span>
      </v-card-title>
      <v-card-text> 
          <v-row>
            <v-col cols="12">
              <input type="radio" name="gender" v-model="gender" value="M">남성
              <input type="radio" name="gender" v-model="gender" value="F">여성

              <v-text-field
              label="Id"
              type="text"
              v-model="id"
              required
              ></v-text-field>
              <v-btn @click="duplicateId(),checkDuplicate()">중복 확인</v-btn>
              {{ this.msg }}
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Password"
                type="password"
                v-model="password"
                @blur="passwordValid"
                required
              ></v-text-field>
            </v-col>
            <div v-if="!passwordValidFlag">
              유효하지않은 비밀번호입니다.                
            </div>
            <v-col cols="12">
              <v-text-field
              label="RE Password"
              type="password"
              v-model="password2"
              @blur="passwordCheckValid"
              required
              ></v-text-field>
            </v-col>
            <div v-if="!passwordCheckFlag">
              비밀번호가 동일하지 않습니다.           
            </div>
            <v-col cols="12">
              <v-text-field
              label="Name"
                type="text"
                v-model="name"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="E-mail"
                type="text"
                v-model="email"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Tel"
                type="text"
                v-model="tel"
                required
              ></v-text-field>
            </v-col>
          </v-row>
      </v-card-text>
      <v-card-actions>
        <!-- 오른쪽 끝으로 이동 -->
        <v-spacer></v-spacer>
        <!-- <div v-if="checkDuplicateFlag != 0 && passwordValidFlag && passwordCheckFlag"> -->
          <v-btn color="blue darken-1" text @click="nextTo">확인</v-btn>
        <!-- </div> -->
        <!-- <div v-else> -->
          <v-btn color="blue darken-1" disabled text>확인</v-btn>
        <!-- </div> -->
        
        <v-btn color="blue darken-1" text @click="moveBack">취소</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
const API_URL = 'http://127.0.0.1:8080'
import axios from 'axios'
export default {
    name:'SignUpClient',
    data(){
      return{
        id:null,
        password:null,
        password2:null,
        name:null,
        email:null,
        tel:null,
        idDuplicateFlag:true,
        // 중복 확인 여부 
        checkDuplicateFlag:0,
        passwordValidFlag: true,
        passwordCheckFlag: true,
        msg:null,
        gender:'M',
      }
    },

    methods:{
      checkDuplicate(){
      this.checkDuplicateFlag = this.checkDuplicateFlag+1
      console.log(this.checkDuplicateFlag)
      },
      // 아이디 중복 검사
      duplicateId(){
        axios({
          method: 'get',
          url:`${API_URL}/api/auth/check-duplicate-id/${this.id}`
        })
        .then(res =>{
          if (res.data === 'OK'){
            this.msg = `${this.id}는 사용할 수 있는 아이디입니다.`
            this.idDuplicateFlag = true
          }else{
            this.msg = `${this.id}는 사용할 수 없는 아이디입니다.`
            this.idDuplicateFlag = false
          }
        })
      },

      //비밀번호 유효성 검사 
      passwordValid(){
        if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(this.password)) {
          this.passwordValidFlag = true
        }else{
          this.passwordValidFlag = false
        }
      },
      passwordCheckValid(){
        if (this.password === this.password2){
          this.passwordCheckFlag = true
        }else{
          this.passwordCheckFlag = false
        }
      },
      // 취소버튼 뒤로 가기
      moveBack(){
        this.$router.push({ name: 'login' })
      },
      // 회원가입
      nextTo(){
        const id = this.id
        const password = this.password
        const password2 = this.password2
        const name = this.name
        const email = this.email
        const tel = this.tel
        const gender = this.gender

        const payload = {
          id: id,
          password: password,
          password2: password2,
          name: name,
          email: email,
          tel: tel,
          gender: gender,
        }
        console.log(payload.gender)
        this.$store.dispatch('signupCounselor', payload)
        this.$router.push({name:'signupcounselor2'})
      }
    }
}
</script>

<style>
.findidform{
  width : 500px;
  height: 700px;
  background-color: white;
  border: 1px solid black;
  border-radius: 30px;
}
</style>