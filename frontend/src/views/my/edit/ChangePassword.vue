<template>
  <v-container>
    <div class="change-pass">
      <h5>현재 비밀번호</h5>
      <v-text-field
        label="기존 비밀번호"
        type="password"
        v-model="password1"
        @blur="passwordValid"
        :rules="user_pw_rule1"
        required
      ></v-text-field>
      <br>
      <h5>새 비밀번호</h5>
      <v-text-field
        label="새 비밀번호"
        type="password"
        v-model="password2"
        @blur="passwordValid"
        :rules="user_pw_rule2"
        required
      ></v-text-field>
      <br>
      <h5>비밀번호 확인</h5>
      <v-text-field
        label="비밀번호 확인"
        type="password"
        v-model="password3"
        @blur="passwordValid"
        :rules="user_pw_rule3"
        required
      ></v-text-field>
      <br>
    <v-btn @click="[checkPassword(),changePassword()]">변경</v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  name:'ChangePassword',
  data(){
    return{
      password1:null,
      user_pw_rule1: [
        v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
        v => !(v && v.length >= 30) || '패스워드는 30자 이상 입력할 수 없습니다.',
        v => v === this.password || '패스워드가 일치하지 않습니다.',
        v => (/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(v)) || '대,소문자와 숫자를 조합해주십시요'
      ],
        
      password2:null,
      user_pw_rule2: [
        v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
        v => !(v && v.length >= 30) || '패스워드는 30자 이상 입력할 수 없습니다.',
        v => (/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(v)) || '대,소문자와 숫자를 조합해주십시요',

      ],
      user_pw_rule3: [
        v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
        v => !(v && v.length >= 30) || '패스워드는 30자 이상 입력할 수 없습니다.',
        v => (/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(v)) || '대,소문자와 숫자를 조합해주십시요',
        v => v === this.password2 || '패스워드가 일치하지 않습니다.'
      ],
      password3:null,

      password: this.$store.state.payload.password
    }
  },
  methods:{
    checkPassword(){
      // 
      if (this.password != this.password1){
        alert('기존 정보와 다릅니다.')
      } else if (this.password2 != this.password3){
        alert('새 비밀번호를 확인해주세요')
      } else {
        alert('비밀번호를 바꾸시겠습니까')
      }
    },

    changePassword(){
    }
  }
}
</script>

<style>

</style>