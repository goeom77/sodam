<template>
  <v-container style="width:500px; height:800px;">
    <v-card>
      <v-card-title >
        <span class="text-h5">회원 가입</span>
      </v-card-title>
      <v-card-text> 
          <v-row>
            <v-col cols="12">
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
                type="text"
                v-model="email"
                :rules="user_email_rule" 
                required
              ></v-text-field>
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
      </v-card-text>
      <v-card-actions>
        <!-- 오른쪽 끝으로 이동 -->
        <v-spacer></v-spacer>
        <div v-if="checkDuplicateFlag != 0 && passwordValidFlag && passwordCheckFlag">
          <v-btn color="blue darken-1" text @click="signup">확인</v-btn>
        </div>
        <div v-else>
          <v-btn color="blue darken-1" disabled text>확인</v-btn>
        </div>
        
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
        v=> !! v|| '전화번호는 필수 입력사항입니다.'
        ],
        idDuplicateFlag:true,
        // 중복 확인 여부 
        checkDuplicateFlag:0,
        passwordValidFlag: true,
        passwordCheckFlag: true,
        msg:null,
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
      signup(){
        const id = this.id
        const password = this.password

        const name = this.name
        const email = this.email
        const tel = this.tel
        
        const payload = {
          id: id,
          password: password,
          name: name,
          email: email,
          tel: tel,
        }
        console.log(payload)
        this.$store.dispatch('signupClient', payload)
        this.$router.push({name:'home'})
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