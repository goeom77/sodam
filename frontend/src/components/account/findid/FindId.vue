<template>
  <div class="findidform">
    <h1>아이디 찾기</h1>
    <h2 class="form__title">이름</h2>
        <input type="text" v-model="ID" placeholder="이름을 입력해주세요"/>
        <h2 class="form__title">이메일</h2>
        <input type="text" v-model="Email" placeholder="이메일을 입력해주세요"/>
        <br>
        <button type="button" class="btn btn-light" @click="findId">아이디 찾기</button>
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
  //   findId(){
  //     const ID = this.ID
  //     const Email = this.Email

  //     const pack = {
  //       ID: ID,
  //       Email: Email
  //     }
  //     this.$store.dispatch('findId', pack)
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