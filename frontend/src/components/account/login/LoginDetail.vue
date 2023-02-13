<template>
  <br>
  <v-window v-model="tab">
    <v-window-item value="one">
      <div class="d-flex justify-content-center ml-4">
        <v-btn
          class="ma-2"
          color="purple"
          icon="mdi-account-heart-outline"
        ></v-btn>
        <h2 style="margin:auto; padding-right:60px;"> Login</h2>
      </div>
    </v-window-item>
    <v-window-item value="two">
      <div class="d-flex justify-content-center ml-4">
        <v-btn
          class="ma-2"
          color="red-lighten-2"
          icon="mdi-doctor"
        ></v-btn>
        <h2 style="margin:auto; padding-right:60px;">Login</h2>
      </div>
    </v-window-item>
  </v-window>
  <br>
  <v-tabs style="width:100%;" v-model="tab">
    <v-tab @click="ChangeToClient" style="width:50%;" value="one">
      <div style="height:5%"></div>
      <h5>고객</h5>
    </v-tab>
    <v-tab @click="ChangeToCounselor" style="width:50%" value="two">
      <div style="height:5%"></div>
      <h5>상담사</h5>
    </v-tab>
  </v-tabs>
  <div style="height:20px"></div>
  <!-- <v-window v-model="tab">
    <v-window-item value="one">
      <v-btn
        class="ma-2"
        color="purple"
        icon="mdi-account-heart-outline"
      ></v-btn>
    </v-window-item>
    <v-window-item value="two">
      <v-btn
        class="ma-2"
        color="red-lighten-2"
        icon="mdi-doctor"
      ></v-btn>
    </v-window-item>
  </v-window> -->
  <div>
    <div style="margin: 0 auto; width:455px;">
      <v-card
        class="mx-auto"
        max-width="380"
        variant="outlined"
      >
        <v-card-item>
          <div>
            <div>
              <v-layout justify-center>
                <v-card-text>
                  <v-form>
                    <v-text-field
                      prepend-icon="mdi-account"
                      name="login"
                      label="Login"
                      type="text"
                    ></v-text-field>
                    <v-text-field
                      prepend-icon="mdi-lock-outline"
                      id="password"
                      name="password"
                      label="Password"
                      type="password"
                    ></v-text-field>
                    <v-card-actions style="width:100%">
                      <v-spacer></v-spacer>
                      <v-btn style="width:100%" variant="outlined" @click="logIn">로그인</v-btn>
                    </v-card-actions>
                    <hr>
                    <v-card-actions>
                      <v-btn style="width:100%; border:0px;" @click="logIn"><LoginKakao  variant="outlined"/></v-btn>
                    </v-card-actions>
                    <br>
                    <div style="width:100%">
                      <router-link style="margin:auto" class="find" :to="{name:'findId'}">아이디찾기  |</router-link>
                      <router-link style="margin:auto" class="find" :to="{name:'findPw'}">  비밀번호 찾기  |</router-link>
                      <router-link style="margin:auto" class="find" :to="{name:'signupclient'}">  회원가입</router-link>
                    </div>
                  </v-form>
                </v-card-text>
              </v-layout>
            </div>
          </div>
        </v-card-item>
      </v-card>
    </div>
  </div>

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
        id: null,
        password: null,
        common_code:'2',
        tab: null,
      }
    },
    methods:{
      logIn(){
        const id = this.id
        const password = this.password
        const common_code = this.common_code
        
        const payload = {
          id: id,
          password: password,
          common_code: common_code
        }
        console.log(payload)
        this.$store.dispatch('login', payload)
      },
      ChangeToClient(){
        this.common_code='2'
      },
      ChangeToCounselor(){
        this.common_code='1'
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
.find{
  text-decoration-line: none;
  color: black;
}
.find:hover{
  text-decoration-line:underline;
  color: black;
}
</style>