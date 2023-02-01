<template>
  <div>
    <CheckEducation/>
    <CheckCertificate/>
    <CheckWorkExperience/>

    {{ userSignupData }} 
    {{ this.enterprise_id }}
    <!-- 파일 저장하는거 추가해야함 -->
    <v-btn color="blue darken-1" text @click="signUpCounselor">회원가입</v-btn>
    <v-btn color="blue darken-1" text @click="moveBack">뒤로가기</v-btn>
    <v-btn color="blue darken-1" text @click="signUpCounselortest">회원가입</v-btn>
  </div>
</template>

<script>
import CheckWorkExperience from '@/components/account/signup/counselor/CheckWorkExperience.vue'
import CheckCertificate from '@/components/account/signup/counselor/CheckCertificate.vue'
import CheckEducation from '@/components/account/signup/counselor/CheckEducation.vue'

export default {
  name:'SignUpCounselor2',
  components:{
    CheckCertificate,
    CheckWorkExperience,
    CheckEducation,
  },
  data(){
    return {
      dialog: false,
      userSignupData: this.$store.state.userSignupData,
      id: this.$store.state.userSignupData.id,
      password: this.$store.state.userSignupData.password,
      name: this.$store.state.userSignupData.name,
      email: this.$store.state.userSignupData.email,
      tel: this.$store.state.userSignupData.tel,
      gender: this.$store.state.userSignupData.gender,
      enterprise_id: 1,
    }
  },
  methods:{
    // 뒤로가기
    moveBack(){
      
      this.$router.push({ name: 'signupcounselor' })
    },
    signUpCounselor(){
      const id = this.id
      const password = this.password
      const name = this.name
      const email = this.email
      const tel = this.tel
      const gender = this.gender
      const enterprise_id = this.enterprise_id

      const payload ={
        id: id,
        password: password,
        name: name,
        email: email,
        tel: tel,
        gender: gender,
        enterprise_id: enterprise_id
      }

      this.$store.dispatch('signupCounselor', payload)
      this.$router.push({name:'login'})
    },
    signUpCounselortest(){
      const id = this.id
      const password = this.password
      const name = this.name
      const email = this.email
      const tel = this.tel
      const gender = this.gender
      const enterprise_id = this.enterprise_id

      const payload ={
        id: id,
        password: password,
        name: name,
        email: email,
        tel: tel,
        gender: gender,
        enterprise_id: enterprise_id
      }
      alert('정말 회원가입할래요?');
      this.$store.dispatch('signupCounselor', payload)
      this.$router.push({name:'login'})
    },
    

    // 회원가입
  }
}
</script>

<style>

</style>