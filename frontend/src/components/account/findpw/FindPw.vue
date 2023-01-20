<template>
  <div class="findidform">
    <h1>비밀번호 찾기</h1>
    <h2>아이디</h2>
        <input type="text" v-model="id" placeholder="아이디를 입력해주세요"/>
      <h2>이메일</h2>
        <input type="text" v-model="email" placeholder="이메일을 입력해주세요"/>
        <br>
        <button type="button" class="btn btn-light" @click="findPw">비밀번호 찾기</button>
  </div>
</template>

<script>
export default {
  name:'findId',
  data(){
    return{
      ID: null,
      Email: null,
    }
  },
  // methods:{
  //   findPw(){
  //     const id = this.id
  //     const email = this.email

  //     const pack = {
  //       id: id,
  //       email: email
  //     }
  //     this.$store.dispatch('findPw', pack)
  //   }
  // }
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