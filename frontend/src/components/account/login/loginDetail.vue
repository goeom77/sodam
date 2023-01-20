<template>
  <div>
    <!-- 로그인 창  -->
    <div class="loginform">
      <div class="container__form container--signup">
      <form action="#" class="form" id="form1">
        <h2 class="form__title">Sign Up</h2>
        <input type="text" v-model="ID"  placeholder="ID"/>
        <br>
        <input type="password" v-model="password" placeholder="Password"/>
        <br>
        <router-link class="find" :to="{name:'findId'}">아이디찾기</router-link>
        <router-link class="find" :to="{name:'findPw'}">비밀번호 찾기</router-link>
        <br>
        <button class="btn">로그인</button>
        <br>
        <LoginKakao/>
        <router-link class="find" :to="{name:'loginKakao'}">카카오 로그인</router-link>
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
import LoginKakao from './LoginKakao.vue'
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
    method:{
      logIn(){
        const ID = this.ID
        const password = this.password

        const payload = {
          ID: ID,
          password: password
        }
        this.$store.dispatch('login', payload)
      },
      moveFindId(){
        console.log('파인드 아이디 입구')
        this.$router.push({name:'find_Id'})
      },
      moveFindPw(){
        this.$router.push({name:'find_Pw'})
      },
      moveKakao(){
        this.$router.push({name:'loginKakao'})
      }

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
  width : 500px;
  height: 300px;
  background-color: white;
  border: 1px solid black;
  border-radius: 30px;
}
.find{
  text-decoration-line: none;
}
</style>