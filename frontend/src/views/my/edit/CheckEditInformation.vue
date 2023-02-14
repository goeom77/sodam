<template>
  <div class="check" style="padding-top:30px">
    <div class="checkForm" style="margin:auto; padding-top: 30px;">
      <h2>비밀번호 확인</h2>
      <h4>고객님의 소중한 개인정보 보호를 위해 <br>
        비밀번호를 다시 확인합니다.</h4>
      <br>
      <h4>비밀번호가 노출되지않도록 주의해주세요</h4>
      <input id="checkPwInput" type="password" v-model="InputPassword"  placeholder="비밀번호"/>
        <br>
      <v-btn @click="checkPassword">
      확인
    </v-btn>
    </div>
    <router-link style="color:black" to="/MainEdit">고민게시판</router-link>&nbsp;&nbsp;&nbsp;
  </div>
</template>

<script>

export default {
  name:'CheckEditInformation',
  data(){
    return{
      InputPassword:null,
      password: this.$store.state.payload.password,
    }
  },

  methods:{
    checkPassword(){
      if (this.InputPassword === this.password){
        this.$router.push({ name : 'MainEdit'})
      } else {
        alert('비밀번호가 다릅니다')
      }
    }
  }
}
</script>

<style>
#checkPwInput {
    padding: 10px;
    width: 50%;
    font-size: 17px;
    font-family: Raleway;
    border: 1px solid #aaaaaa;
    border-radius: 10px;
    -webkit-appearance: none;
    
}


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