<template>
  <div>
    <button class="background2" @click="loginWithKakao"></button>
  </div>
  
<!-- 

  <a id="loginBtn" type="button" onclick="function login() {
    const redirectUri = 'http://localhost:8080/auth/kakao/callback';
        const appKey = '9e0f9a70f672fba12b71ea1b5ec10e80'
            window.location.href = 'https://kauth.kakao.com/oauth/authorize?client_id=' + appKey +
    '&redirect_uri=' + redirectUri + '&response_type=code';}
login()"><img src="kakao_login_btn.png" alt="button"></a>
<a onclick="alert('alert test')">alert?</a> -->
</template>

<script>
export default {
    name: "LoginKakao",
    // methods: {
    //     loginWithKakao() {
    //       console.log('가나다라')
    //       const params = {
    //           redirectUri: "http://localhost:80",
    //       };
    //       window.Kakao.Auth.authorize(params);
    //     },
    // },
};
</script>

<style>
.background2 {
  width:300px;
  height:100px;
  background-image: url(../../../assets/kakao_login_btn.png);
}
</style>