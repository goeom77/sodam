<template>
  <v-card color="basil">

    <v-tabs
      v-model="tab"
      bg-color="transparent"
      color="basil"
      grow
    >
      <v-tab
        v-for="item in items"
        :key="item"
        :value="item"
      >
        {{ item }}
      </v-tab>
    </v-tabs>

    <v-window v-model="tab">
      <v-window-item
        v-for="item in items"
        :key="item"
        :value="item"
      >
        <v-card
          color="basil"
          flat
        >
          <v-card-text>{{ text }}</v-card-text>
        </v-card>
      </v-window-item>
    </v-window>
  </v-card>
</template>
  
  <script>
  import LoginKakao from '@/components/account/login/LoginKakao.vue'
  export default {
      name:'LoginDetail',
      components:{
        LoginKakao
      },
      data(){
        return {
          iD: null,
          password: null,
          common_code:2,
        }
      },
      methods:{
        logIn(){
          const iD = this.iD
          const password = this.password
          const common_code = this.common_code
          
          const payload = {
            iD: iD,
            password: password,
            common_code: common_code
          }
          console.log(payload)
          this.$store.dispatch('login', payload)
        },
        ChangeToClient(){
          this.common_code=2
        },
        ChangeToCounselor(){
          this.common_code=1
        }
  
        // moveFindId(){
        //   console.log('파인드 아이디 입구')
        //   this.$router.push({name:'find_Id'})
        // },
        // moveFindPw(){
        //   this.$router.push({name:'find_Pw'})
        // },
        // moveKakao(){
        //   this.$router.push({name:'loginKakao'})
        // }
  
      }
      
  }
  </script>
  
  <style>
  .input {
    background-color: #fff;
    border: none;
    border-radius: 30px;
    padding: 0.9rem 0.9rem;
    margin: 0.5rem 0;
    width: 90%;
  }
  .loginform{
    margin-top: 100px;
    width : 300px;
    height: 300px;
    /* border: 1px solid black; */
    border-radius: 30px;
    background-color: #FCFAEF;
  }
  .find{
    text-decoration-line: none;
    color: black;
  }
  
  </style>