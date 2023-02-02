<template>
  <v-tabs>
    <v-tab @click="ChangeToClient">
      고객
    </v-tab>
    <v-tab @click="ChangeToCounselor">
      상담사
    </v-tab>
  </v-tabs>

  <v-tabs-items v-model="tab">
    <v-tab-item>
      <div v-if="this.common_code==='2'">
        고객 로그인 창 
        <div class="loginform">
          <div class="container__form container--signup" style="margin: 0 auto; width:455px;">
          <form action="#" class="form" id="form1">
            <h2 class="form__title">Login</h2>
            <br>
            <input class="inputTage" type="text" v-model="id"  placeholder="ID"/>
            <br>
            <input class="inputTage" type="password" v-model="password" placeholder="Password"/>
            <br>
            <router-link class="find" :to="{name:'findId'}">아이디찾기</router-link>
            <router-link class="find" :to="{name:'findPw'}">비밀번호 찾기</router-link>
            <br>
            <v-btn @click="logIn">로그인</v-btn>
            <br>
            <br>
            <LoginKakao/>
            <div style="display:block">
              <h4>계정이 없으신가요?</h4>
              <router-link class="find" :to="{name:'signupclient'}">회원가입</router-link>
              </div>
            </form>
          </div>
        </div>
      </div>

      <div v-if="this.common_code==='1'">
        상담사 로그인 창 
        <div class="loginform">
          <div class="container__form container--signup" style="margin: 0 auto; width:455px;">
          <form action="#" class="form" id="form1">
            <h2 class="form__title">Login</h2>
            <input type="text" v-model="id"  placeholder="ID"/>
            <br>
            <input type="password" v-model="password" placeholder="Password"/>
            <br>
            <router-link class="find" :to="{name:'findId'}">아이디찾기</router-link>
            <router-link class="find" :to="{name:'findPw'}">비밀번호 찾기</router-link>
            <br>
            <v-btn @click="logIn">로그인</v-btn>
            <br>
            <br>
            <LoginKakao/>
              <div style="display:block">
                <h4>계정이 없으신가요?</h4>
                <router-link class="find" :to="{name:'signupcounselor'}">회원가입</router-link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </v-tab-item>
  </v-tabs-items>
</template>

<script>
import LoginKakao from '@/components/account/login/LoginKakao.vue'
export default {
    name:'LoginDetail',
    components:{
      LoginKakao
    },
    data(){
      return {
        id: null,
        password: null,
        common_code:'2',
      }
    },
    methods:{
      logIn(){
        const id = this.id
        const password = this.password
        const common_code = this.common_code
        
        const payload = {
          id: id,
          password: password,
          common_code: common_code
        }
        console.log(payload)
        this.$store.dispatch('login', payload)
      },
      ChangeToClient(){
        this.common_code='2'
      },
      ChangeToCounselor(){
        this.common_code='1'
      }

      // moveFindId(){
      //   console.log('파인드 아이디 입구')
      //   this.$router.push({name:'find_Id'})
      // },
      // moveFindPw(){
      //   this.$router.push({name:'find_Pw'})
      // },
      // moveKakao(){
      //   this.$router.push({name:'loginKakao'})
      // }

    }
    
}
</script>

<style>
.input {
  background-color: #fff;
  border: none;
  border-radius: 30px;
  padding: 0.9rem 0.9rem;
  margin: 0.5rem 0;
  width: 90%;
}
.loginform{
  margin-top: 100px;
  width : 300px;
  height: 300px;
  /* border: 1px solid black; */
  border-radius: 30px;
  background-color: #FCFAEF;
}
.find{
  text-decoration-line: none;
  color: black;
}

</style>