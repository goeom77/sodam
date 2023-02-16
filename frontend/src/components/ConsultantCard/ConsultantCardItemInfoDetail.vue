<template>
    <v-container>
      <v-card variant="tonal">
        <div v-show="counselor.career[0] != null">
          <div class="infoList">
            <h4 class="infoListTitle">경력</h4>
            <div v-for="career in counselor.career" :key="career">
              <div>{{ this.careerFormat(career) }}</div>
            </div>
          </div>
        </div>
        <div v-show="counselor.certificates[0] != null">
          <div class="infoList">
            <h4 class="infoListTitle">자격증</h4>
            <div v-for="certificate in counselor.certificates" :key="certificate">
              <div>{{ this.certificateFormat(certificate) }}</div>
            </div>
          </div>
        </div>
        <div v-show="counselor.educations[0] != null">
          <div class="infoList">
            <h4 class="infoListTitle">학력</h4>
            <div v-for="education in counselor.educations" :key="education">
              <div>{{ this.educationFormat(education) }}</div>
            </div>
          </div>
        </div>
      </v-card>
    </v-container>
</template>
  
<script>
  export default {
    name:'ConsultantCardItemInfo',
    data(){
      return{
        common_code: this.$store.state.payload.common_code,
        clientId:this.$store.state.payload.id,
        counselor: this.$store.state.userSignupData
      }
    },
    methods:{
      getReview(){
        axios({
            method:'post',
            url: `${VUE_APP_API_URL}/api/review/my/counselor`,
            data:{
            counselorId: this.counselorId
            },
        })
        .then(res=>{
            console.log(res)
            this.reviews = res.data.content
        })
        .catch(res=>{
            console.log(res)
            console.log('안직힘   ')
        })
      },
      careerFormat(career) {
        return `${career.name} ${career.content} ${career.period}`
      },
      certificateFormat(certificate) {
        return `${certificate.name} ${certificate.agency}`
      },
      educationFormat(education) {
        return `${education.school} ${education.major} ${education.degree} ${education.is_graduate}`
      }
    },
    computed: {
      
    }
  }
  </script>
  
<style>
.infoList {
  margin: 2rem;
}
.infoListTitle {
  margin-bottom: 0.8rem;
}
</style>