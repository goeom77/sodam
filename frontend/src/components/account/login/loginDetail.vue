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
        <button class="btn">아이디 찾기</button>
        <button class="btn">비밀번호 찾기</button>
        <br>
        <button class="btn">로그인</button>
        <br>
        <button class="btn">카카오 로그인</button>
        <div style="display:block">
          <h4>계정이 없으신가요?</h4>
          <button class="btn">회원 가입</button>

        </div>

      </form>

    </div>
  </div>

  </div>
</template>

<script>
export default {
    name:'loginDetail',
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
      }
    }
    
}
</script>

<style>
.input {
  background-color: #fff;
  border: none;
  padding: 0.9rem 0.9rem;
  margin: 0.5rem 0;
  width: 90%;
}
.loginform{
  width : 500px;
  height: 300px;
  background-color: white;
  border: 1px solid black;
}
</style>