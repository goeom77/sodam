<template>
  <v-container>
    <div class="content-dis">
      <h2>탈퇴 후 공개 게시글은 삭제되지 않습니다.</h2>
      <br>
      <h5>소담의 게시판에 등록한 모든 게시물은 탈퇴 후에도 유지됩니다.</h5>
      <br>
      <h5>해당 게시물의 삭제를 원하는 경우 탈퇴 전에 삭제해주세요</h5>
        <v-btn @click="OutMember">탈퇴</v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  name:'DeleteAccount',
  data(){
    return{
      id : this.$store.state.payload.id
    }
  },
  methods:{
    OutMember(){
      axios({
        method:'delete',
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        },
        data:{
          id:this.id
        }
      })
      .then(res=>{
        console.log(res)
      })
    }
  }
}
</script>

<style>

</style>