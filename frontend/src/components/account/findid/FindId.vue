<template>
  <v-container style="width:500px; height:300px;">

    <v-card>
      <v-card-title >
        <span class="text-h5">아이디 찾기</span>
      </v-card-title>
      <v-card-text> 
  
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="이름을 입력해주세요"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="이메일을 입력해주세요"
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
  //   findId(){
  //     const ID = this.ID
  //     const Email = this.Email

  //     const pack = {
  //       ID: ID,
  //       Email: Email
  //     }
  //     this.$store.dispatch('findId', pack)
  //   }
  // }
}
</script>

<style>


</style>