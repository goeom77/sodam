<template>
<v-container>

  <div class="main">
    <input type="radio" id="tab-1" name="show" checked/>
    <input type="radio" id="tab-2" name="show" />
    <input type="radio" id="tab-3" name="show" />
    <div class="tab">
      <label for="tab-1">회원 정보 수정</label>
      <label for="tab-2">비밀번호 변경</label>
      <label for="tab-3">회원 탈퇴</label>
    </div>


    <div class="content">
      <!-- 회원 정보 수정 -->
      <div class="content-dis">
        <!-- 아이디 : {{ data.id }}
        비밀번호 : {{ data.password }} -->
        <v-btn @click="changePassword">변경</v-btn>
        <!-- 이름 : {{ data.name }}
        이메일 : {{ data.email }}
        전화번호 : {{ data.number }} -->
      </div>
      
      <div class="content-dis">
        <h5>현재 비밀번호</h5>
      <input type="text" v-model="password" placeholder="기존 비밀번호"/>
      <br>
      <h5>새 비밀번호</h5>
      <input type="text" v-model="password2" placeholder="새 비밀번호"/>
      <br>
      <h5>비밀번호 확인</h5>
      <input type="text" v-model="password2" placeholder="비밀번호 확인"/>
      <br>
      <!-- <v-btn @click="changePassword">변경</v-btn> -->
      </div>


      <div class="content-dis">
        <h2>탈퇴 후 공개 게시글은 삭제되지 않습니다.</h2>
        <br>
        <h5>소담의 게시판에 등록한 모든 게시물은 탈퇴 후에도 유지됩니다.</h5>
        <br>
        <h5>해당 게시물의 삭제를 원하는 경우 탈퇴 전에 삭제해주세요</h5>
          <!-- <v-btn @click="changePassword">변경</v-btn>
          <v-btn @click="changePassword">변경</v-btn> -->
      </div>
    </div>
  </div>
</v-container>

</template>

<script>

export default {
  name:'EditInformation',
  methods:{
    changePassword(){
      pass
    }
  }
}
</script>

<style>
.main input[type=radio] {
	display: none;
  
}
#tab-1:checked ~ .tab label:nth-child(1),
#tab-2:checked ~ .tab label:nth-child(2),
#tab-3:checked ~ .tab label:nth-child(3),
#tab-4:checked ~ .tab label:nth-child(4) {
	background-color: rgba(0, 0, 0, 0.2);
  box-shadow: none;
}
.content > div {
	display: none;
}
#tab-1:checked ~ .content div:nth-child(1),
#tab-2:checked ~ .content div:nth-child(2),
#tab-3:checked ~ .content div:nth-child(3),
#tab-4:checked ~ .content div:nth-child(4)  {
	display: block;
  
}
.main {
  margin: 0 auto;
  background-color: rgba(0, 0, 0, 0.2);
  max-width: 100%;
}
.tab {
  overflow: hidden;
}
.tab label {
    font-size: 18px;
    cursor: pointer;
    float: left;
    width: 25%;
    text-align: center;
    padding: 15px 0;
    text-transform: uppercase;
    font-weight: bold;
    letter-spacing: 2px;
    user-select: none;
    -webkit-user-select: none;
}
.content {
  background-color: white ;
  min-height: 250px;
}
.content > div{
  padding: 30px;
  line-height: 1.5;
  font-size: 17px;
}

.content-dis{
  border: 1px solid black;
}
</style>