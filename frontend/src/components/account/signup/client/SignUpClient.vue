<template>
  <div class="findidform">
    <h1>회원 가입</h1>
      <h2>아이디</h2>
      <input type="text" v-model="id" placeholder="아이디를 입력해주세요"/>
      <button type="button" class="btn btn-light" @click="findId">중복확인</button>
      <h2>비밀번호</h2>
      <input type="text" v-model="password" placeholder="비밀번호를 입력해주세요"/>
      <br>
      <h2>비밀번호 확인</h2>
      <input type="text" v-model="password2" placeholder="비밀번호를 입력해주세요"/>
      <br>
      <h2>성함</h2>
      <input type="text" v-model="name" placeholder="이름을 입력해주세요"/>
      <br>
      <h2>이메일</h2>
      <input type="text" v-model="email" placeholder="이메일을 입력해주세요"/>
      <br>
      <h2>전화번호</h2>
      <input type="text" v-model="number" placeholder="연락처를 입력해주세요"/>
      <br>
      <button type="button" class="btn btn-light" @click="signup">회원가입</button>
  </div>
</template>

<script>
// const API_URL = 'http://127.0.0.1:8080'

export default {
    name:'SignUpClient',
    data(){
      return{
        id:null,
        password:null,
        password2:null,
        name:null,
        email:null,
        number:null,
      }
    },

    methods:{
      // 중복확인
      signup(){
        const id = this.id
        const password = this.password
        const password2 = this.password2
        const name = this.name
        const email = this.email
        const number = this.number
        
        const payload = {
          id: id,
          password: password,
          password2: password2,
          name: name,
          email: email,
          number: number,
        }

        this.$store.dispatch('signup', payload)

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