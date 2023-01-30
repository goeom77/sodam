<template>
  <div>
    <CheckEducation/>
    <CheckWorkExperience/>
    <CheckCertificate/>

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
  }
}
</script>

<style>

</style>