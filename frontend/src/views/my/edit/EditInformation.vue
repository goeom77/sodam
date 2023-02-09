<template>
  <v-container>
    <div class="content">
      <form @submit.prevent="counselorUpdate">
        <!-- <form> -->
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6;">
            <label for="name">이름</label>
            <input type="text" id="name" v-model.trim="name">
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6;">
            <label for="tel">전화번호</label>
            <input type="number" id="tel" v-model.trim="tel">
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6;">
            <label for="email">이메일</label>
            <input type="text" id="email" v-model.trim="email">
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6;">
            <label for="education">자격증</label>
            <input type="text" id="education" v-model.trim="education">
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6;">
            <label for="career">경력</label>
            <input type="text" id="career" v-model.trim="career">
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6;">
            <label for="introduce">자기소개</label>
            <textarea id="introduce" v-model="introduce"></textarea>
          </div>

          <input type="submit" id="submitno" value="수정">
        </form>
    </div>
  </v-container>
</template>

<script>

 import axios from 'axios'
 const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name:'EditInformation',
  data(){

    return{
      tab:null,
      tel:0,
      career: '',
      introduce: '',
      education: '',
      name: '',
      email: '',
      id: this.$store.state.payload.id

    }
  },
  created() {
      this.getcounselordetail()
  },
  methods:{
     getcounselordetail() {
        axios({
          method: 'get',
          url: `${VUE_APP_API_URL}/api/my-page/counselor/${this.$store.state.payload.id}`,
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(() => {
          this.tel = res.data.tel
          this.career = res.data.career
          this.introduce = res.data.introduce
          this.education = res.data.education
          this.name= res.data.name
          this.email= res.data.email
          console.log('받아왔다 재훈아')
        })
        .catch(() => {
          console.log('실패했다 재훈아')
        })
      },
      counselorUpdate() {
        const title = this.title
        const tel = this.tel 
        const career = this.career
        const introduce =  this.introduce
        const education =  this.education
        const name = this.name
        const email = this.email

      axios({
        method: 'put',
        url: `${VUE_APP_API_URL}/api/counselor/${this.$store.state.payload.id}`,
        data: {
          title : title,
          tel : tel ,
          career : career,
          introduce :  introduce,
          education :  education,
          name : name,
          email : email,
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('업데이트 된단다 재훈아')
        this.$router.push({ 
          name: 'MainEdit', 
          // params: { postId: this.$route.params.postId } 
        })
      })
      .catch((err) => {
        console.log('업데이트 안된단다 재훈아')
        console.log(err)
      })
    }


  }
}
</script>

<style>

</style>