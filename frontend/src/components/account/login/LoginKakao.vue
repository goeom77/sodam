<template>
  <div>
    <a id="custom-login-btn" @click="kakaoLogin()">
      <img
      src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
      width="222"
      alt="카카오 로그인 버튼"
      />
    </a>
    <div @click="kakaoLogout()">로그아웃</div>
  </div>
  <!-- <div>
    <button class="background2" @click="loginWithKakao"></button>
  </div> -->
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
const API_URL = 'http://127.0.0.1:8080'

export default {
  methods: {
    kakaoLogin() {
      window.Kakao.Auth.login({
        scope: "profile_nickname",
        success: this.getKakaoAccount,
      });
    },
    getKakaoAccount() {
      window.Kakao.API.request({
        url: `${API_URL}/api/auth/kakao`,
        success: (res) => {
          const kakao_account = res.kakao_account;
          const ninkname = kakao_account.profile.ninkname;
          const email = kakao_account.email;
          console.log("ninkname", ninkname);
          console.log("email", email);

          //로그인처리구현

          alert("로그인 성공!");
        },
        fail: (error) => {
          console.log(error);
        },
      });
    },
    kakaoLogout() {
      window.Kakao.Auth.logout((res) => {
        console.log(res);
      });
    },
  },
};
// export default {
//     name: "LoginKakao",
//     methods: {
//         loginWithKakao() {
//           console.log('가나다라')
//           const params = {
//               redirectUri: "http://localhost:80",
//           };
//           window.Kakao.Auth.authorize(params);
//         },
//     },
// };
</script>

<style>
.background2 {
  width:300px;
  height:100px;
  background-image: url(../../../assets/kakao_login_btn.png);
}
</style>