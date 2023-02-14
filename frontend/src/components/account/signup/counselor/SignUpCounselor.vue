<template>
  <v-container style="width:500px; height:800px;">
    <v-card>
      <v-card-title >
        <span class="text-h5">회원 가입</span>
      </v-card-title>
      <v-card-text>
        <v-form ref="form">
          <v-row>
            <v-col cols="12">
              <div>
                <input type="radio" name="gender" v-model="gender" value="MEN" checked>남성
                <input type="radio" name="gender" v-model="gender" value="WOMEN">여성
              </div>
              <v-text-field
              label="Id"
              type="text"
              v-model="id"
              :rules="user_id_rule" 
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
                :rules="user_pw_rule"
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
              :rules="user_pw_rule2"
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
                :rules="user_nm_rule" 
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="E-mail"
                type="email"
                v-model="email"
                :rules="user_email_rule" 
                required
              ></v-text-field>
              <v-btn @click="CheckEmail" v-if="this.checkEmail===0">이메일 확인</v-btn>
              <div v-else-if="this.checkEmail === 1">
                <v-text-field
                  label="인증 번호" type="number" v-model="this.confirm_code"
                  required
                ></v-text-field>
                <v-btn @click="CheckEmailConfirm" v-bind:disabled="emailCheckMsg != null">인증</v-btn>
                {{ this.emailCheckMsg }}
              </div>
              <div v-else-if="this.checkEmail === 2">이미 가입된 사용자입니다.</div>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Tel"
                type="number"
                v-model="tel"
                :rules="user_tel_rule" 
                required
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <!-- 오른쪽 끝으로 이동 -->
        <v-spacer></v-spacer>
        <!-- <div v-if="checkDuplicateFlag != 0 && passwordValidFlag && passwordCheckFlag"> -->
          <v-btn color="blue darken-1" text @click="nextTo" v-bind:disabled="!checkDuplicateFlag || !passwordCheckFlag || emailCheckMsg == null">확인</v-btn>
        <!-- </div>
        <div v-else> -->
          <v-btn color="blue darken-1" disabled text>확인</v-btn>
        <!-- </div> -->
        
        <v-btn color="blue darken-1" text @click="moveBack">취소</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
const VUE_APP_API_URL = process.env.VUE_APP_API_URL
import axios from 'axios'

export default {
    name:'SignUpClient',
    data(){
      return{
        id:null,
        user_id_rule: [
        v => !!v || '아이디는 필수 입력사항입니다.',
        v => /^[a-zA-Z0-9]*$/.test(v) || '아이디는 영문+숫자만 입력 가능합니다.',
        v => !( v && v.length >= 15) || '아이디는 15자 이상 입력할 수 없습니다.'
      ],
        password:null,
        user_pw_rule: [
        v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
        v => !(v && v.length >= 30) || '패스워드는 30자 이상 입력할 수 없습니다.',
        v => (/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(v)) || '대,소문자와 숫자를 조합해주십시요'
      ],
        password2:null,
        user_pw_rule2: [
        v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
        v => !(v && v.length >= 30) || '패스워드는 30자 이상 입력할 수 없습니다.',
        v => v === this.password || '패스워드가 일치하지 않습니다.'
      ],
        name:null,
        user_nm_rule: [
        v => !!v || '이름은 필수 입력사항입니다.',
        v => !(v && v.length >= 30) || '이름은 30자 이상 입력할 수 없습니다.',
        v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다.'
      ],
        email:null,
        user_email_rule:[
          v=> !!v || 'e-mail은 필수 입력사항입니다.'
        ],
        tel:null,
        user_tel_rule:[
        v=> !! v || '전화번호는 필수 입력사항입니다.'
        ],
        idDuplicateFlag:true,
        // 중복 확인 여부 
        checkDuplicateFlag:false,
        passwordValidFlag: false,
        passwordCheckFlag: false,
        msg:null,
        gender:'MEN',
        checkEmail:0,
        confirm_code:null,
        // 이메일 인증 확인
        emailCheckMsg: null
        
      }
    },

    methods:{
      // 이메일 확인 
      CheckEmail(){
        axios({
          method: 'post',
          url: `${VUE_APP_API_URL}/api/auth/send-code`,
          data:{
            email:this.email
          }
        })
        .then(res => {
          if(res.data === 'OK') {
            this.checkEmail = 1
          } else if(res.data === 'CONFLICT') { // 이미 존재하는 사용자
            this.checkEmail = 2
          } else {  // 이메일 에러
            this.checkEmail = 1 // -------- 이메일 복구 후 삭제
            alert('이메일 확인 중 문제가 발생했습니다. 잠시후 시도해 주세요.')
          }
        })
      },
      // 이메일 인증
      CheckEmailConfirm(){
        axios({
          method:'get',
          url:`${VUE_APP_API_URL}/api/auth/confirm-mail?code=${this.confirm_code}`,
          data:{
            code: this.confirm_code
          }
        })
        .then((res)=>{
          this.emailCheckMsg = '이메일이 인증되었습니다.'
        })
        .catch((error) => {
          this.emailCheckMsg = '인증코드가 일치하지 않습니다.'
        })
      },
      checkDuplicate(){
      this.checkDuplicateFlag = !this.checkDuplicateFlag

      },
      // 아이디 중복 검사
      duplicateId(){
        axios({
          method: 'get',
          url:`${VUE_APP_API_URL}/api/auth/check-duplicate-id/${this.id}`
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
        if(this.$refs.form.validate()) {
          console.log("ok")
        }
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
        this.$store.dispatch('saveCounselor', payload)
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