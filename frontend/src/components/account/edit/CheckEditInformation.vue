<template>
  <div class="check">
    <div class="checkForm">
      <h2>회원 정보 수정</h2>
      <h4>고객님의 소중한 개인정보 보호를 위해 비밀번호를 다시 확입합니다.</h4>
      <br>
      <h4>비밀번호가 노출되지않도록 주의해주세요</h4>
      <input type="text" v-model="checkPassword"  placeholder="비밀번호"/>
      <br>
      <v-btn @click="checkPw">
      확인
    </v-btn>
    </div>
    <router-link style="color: black;" to="/editinformation">고객관리</router-link>&nbsp;&nbsp;&nbsp;
  </div>
</template>

<script>

export default {
  name:'CheckEditInformation',
  data(){
    return{
      checkPassword:null,
      password: this.$store.state.payload.password
    }
  },

  checkPw:{
    checkPassword(){
      if (this.checkPassword === this.password){
        this.$router.push({ name : EditInformation})
      } else {
        alert('비밀번호가 다릅니다')
      }
    }
  }
}
</script>

<style>
.checkForm{

  width : 500px;
  height: 300px;
  background-color: white;
  border: 1px solid black;
  border-radius: 30px;
}

.check{
  text-align: center;
  justify-items: center;
}
</style>