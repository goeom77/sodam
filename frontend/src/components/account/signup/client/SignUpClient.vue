<template>
  <v-container style="width:500px; height:800px;">
    <v-card>
      <v-card-title >
        <span class="text-h5">회원 가입</span>
      </v-card-title>
      <v-card-text> 
          <v-row>
            
            <v-col cols="12">
              <v-text-field
              label="Id"
              type="text"
              v-model="id"
              required
              ></v-text-field>
              <button type="button" class="btn btn-light" @click="findId">중복확인</button>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Password"
                type="text"
                v-model="password"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="RE   Password"
                type="text"
                v-model="password2"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12">
              <v-text-field
                label="Name"
                type="text"
                v-model="name"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12">
              <v-text-field
                label="E-mail"
                type="text"
                v-model="email"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Tel"
                type="text"
                v-model="number"
                required
              ></v-text-field>
            </v-col>
          </v-row>
  
      </v-card-text>
      <v-card-actions>
        <!-- 오른쪽 끝으로 이동 -->
        <v-spacer></v-spacer>
        <v-btn
          color="blue darken-1"
          text
        >
          확인
        </v-btn>
        <v-btn
          color="blue darken-1"
          text
          @click="moveBack"
        >
          취소
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
// const API_URL = 'http://127.0.0.1:8080'

export default {
    name:'SignUpClient',
    data(){
      return{
        id:null,
        password:null,
        password2:null,
        name:null,
        email:null,
        number:null,
      }
    },

    methods:{
      moveBack(){
        this.$router.push({ name : login})
      },
      // 중복확인
      signup(){
        const id = this.id
        const password = this.password
        const password2 = this.password2
        const name = this.name
        const email = this.email
        const number = this.number
        
        const payload = {
          id: id,
          password: password,
          password2: password2,
          name: name,
          email: email,
          number: number,
        }
        console.log(payload)
        this.$store.dispatch('signup', payload)

      }
    }
}
</script>

<style>
.findidform{
  width : 500px;
  height: 700px;
  background-color: white;
  border: 1px solid black;
  border-radius: 30px;
}
</style>