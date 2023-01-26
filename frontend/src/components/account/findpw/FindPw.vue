<template>
  <v-container style="width:500px; height:300px;">

    <v-card>
      <v-card-title >
        <span class="text-h5">비밀번호 찾기</span>
      </v-card-title>
      <v-card-text> 
  
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="ID"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Email"
                type="Email"
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
        >
          취소
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
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
  //   findPw(){
  //     const id = this.id
  //     const email = this.email

  //     const pack = {
  //       id: id,
  //       email: email
  //     }
  //     this.$store.dispatch('findPw', pack)
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