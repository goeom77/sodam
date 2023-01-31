<template>
  <div>
    <CheckEducation/>
    <CheckCertificate/>
    <CheckWorkExperience/>
    <v-btn color="blue darken-1" text @click="nextTo">확인</v-btn>
    <v-btn color="blue darken-1" text @click="moveBack">뒤로가기</v-btn>
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
      id: this.$store.state.userSignupData.id,
      password: this.$store.state.userSignupData.password,
      name: this.$store.state.userSignupData.name,
      email: this.$store.state.userSignupData.email,
      tel: this.$store.state.userSignupData.tel,
      gender: null,
      enterprise_id: null,
    }
  },
  methods:{
    // 뒤로가기
    moveBack(){
      this.$router.push({ name: 'signupCounselor' })
    },
    nextTo(){
      this.$router.push({ name: ''})
    }
    // 회원가입

  }
}
</script>

<style>

</style>