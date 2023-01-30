<template>
  <div >
    <!-- 로그인 창  -->
    <div class="loginform">
      <div class="container__form container--signup" style="margin: 0 auto; width:455px;">
      <form action="#" class="form" id="form1">
        <h2 class="form__title">SignUp</h2>

        <input type="text" v-model="ID"  placeholder="ID"/>
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
          <router-link class="find" :to="{name:'signupclient'}">회원가입</router-link>
        </div>
      </form>
    </div>
  </div>
  </div>
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
        ID: null,
        password: null,
      }
    },
    methods:{
      logIn(){
        const ID = this.ID
        const password = this.password
        const payload = {
          ID: ID,
          password: password
        }
        console.log(payload)
        this.$store.dispatch('login', payload)
      },
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