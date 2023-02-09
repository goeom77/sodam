<template>
  <div @submit.prevent="onSubmit">
<!-- 학력 -->
    <table>
      <caption @click="checkData">학력</caption>
        <thead>
          <tr>
            <th>학위</th>
            <th>학교</th>
            <th>전공</th>
            <th>졸업 여부</th>
            <th>증명서 사본</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(row1,idx) in this.education_row"
          :key="idx">
            <td>
              <div class="haveBorder">
                <select v-model="row1.degree">
                  <option value="학사">학사</option>
                  <option value="석사">석사</option>
                  <option value="박사">박사</option>
                </select>
              </div>
            </td>
            <td>
              <!-- 학교 -->
              <div class="haveBorder">
                <input type="text" name="eduSchool" v-model="row1.school" placeholder="학교명">
              </div>
            </td>
              <td>
                <!-- 전공 -->
                <div class="haveBorder">
                  <input type="text" name="eduMajor" v-model="row1.major" placeholder="전공명">
                </div>
              </td>
              <td>
                <!-- 졸업여부 -->
                <div class="haveBorder">
                  <select v-model="row1.is_graduate">
                    <option value="재학">재학</option>
                    <option value="졸업">졸업</option>
                    <option value="수료">수료</option>
                  </select>
                </div>
              </td>
              <td>
                <v-file-input
                accept="image/*"
                v-model="row1.education_file"
                label="File input"
              ></v-file-input>
              </td>
              <td>
                <button @click="removeEducationRow(row1)">X</button>
              </td>
          </tr>
        </tbody>
        <tfoot>
          <td colspan="5" class="tablefoot"></td>
        </tfoot>
        <div>
         <button class="button btn-primary" @click="addEducationRow">+추가</button>
      </div>
      </table>

        <!-- 자격증  -->
  <table>
    <caption>자격증</caption>
      <thead>
        <tr>
          <th>자격증 명</th>
          <th>일련 번호</th>
          <th>발급기관</th>
          <th>파일</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="(row2,idx) in certificate_row"
          :key="idx">
          <td>
            <!-- 자격증 이름 -->
            <div class="haveBorder">
              <input type="text" v-model="row2.certificate_name" placeholder="자격증 명">
            </div>
          </td>
          <td>
            <!-- 일련 번호 -->
            <div class="haveBorder">
              <input type="text" name="eduSchool" v-model="row2.certificate_number" placeholder="일련번호 ">
            </div>
          </td>
          <td>
            <!-- 발급기관 -->
            <div class="haveBorder">
              <input type="text" name="eduSchool" v-model="row2.certificate_agency" placeholder="발급기관 ">
            </div>
          </td>


          <td>
            <v-file-input
            accept="image/*"
            v-model="row2.certificate_file"
            label="File input"
          ></v-file-input>
          </td>
          <td>
            <button @click="removeCertificateRow(row2)">X</button>
          </td>
        </tr>
      </tbody>
      <tfoot>
        <td colspan="5" class="tablefoot"></td>
      </tfoot>
      <button class="button btn-primary" @click="addCertificateRow">+추가</button>
    </table>


<!-- 경력 -->
    <div>
    <h4>
      <label for="inputTextCareerSummary">상담 경력</label>
    </h4>
    <!-- 상담경력 input박스 -->
    <table>
    <caption>상담 경력</caption>
      <thead>
        <tr>
          <th>근무지</th>
          <th>담당 업무</th>
          <th>근무기간</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="(row3,idx) in career_row"
          :key="idx">
          <td>
            <!-- 근무지 이름 -->
            <div class="haveBorder">
              <input type="text" v-model="row3.career_name" placeholder="근무지">
            </div>
          </td>
          <td>
            <!-- 담당업무 -->
            <div class="haveBorder">
              <input type="text" v-model="row3.career_content" placeholder="담당업무">
            </div>
          </td>
          <td>
            <!-- 근무기간 -->
            <div class="haveBorder">
              <input type="text" name="eduSchool" v-model="row3.career_period" placeholder="근무기간 ">
            </div>
          </td>
          <td>
            <button @click="removeCareerRow(row3)">X</button>
          </td>
        </tr>
      </tbody>
      <tfoot>
        <td colspan="5" class="tablefoot"></td>
      </tfoot>
      <button class="button btn-primary" @click="addCareerRow">+추가</button>
    </table>
    <!-- <div>
      <input type="text" id="inputTextCarrerSummary" v-model="this.career_content" placeholder="예) 3년 이상, 6개월 이상 ">
      <p>입력하지않는 경우, 프로필에 공란으로 표시됩니다.</p>
      <v-file-input
        accept="image/*"
        v-model="this.career_file"
        label="File input"
      ></v-file-input>
    </div> -->
  </div>

    <!-- 파일 저장하는거 추가해야함 -->
    <v-btn color="blue darken-1" text @click="signUpCounselor">회원가입</v-btn>
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
      dialog: false,
      id: this.$store.state.userSignupData.id,
      password: this.$store.state.userSignupData.password,
      name: this.$store.state.userSignupData.name,
      email: this.$store.state.userSignupData.email,
      tel: this.$store.state.userSignupData.tel,
      gender: this.$store.state.userSignupData.gender,
      enterprise_id: 1,

      // 자격증
      certificate_row:[{
        certificate_name:null,
        certificate_number:null,
        certificate_agency:null,
        certificate_file:null,
      }],
      // 학력
      education_row:[{
        degree:null,
        school:null,
        major:null,
        is_graduate:null,
        education_file: null,
      }],
      // 경력
      career_row:[{
        career_name:null,
        career_content:null,
        career_period:null,
      }]
    }
  },
  methods:{
    getinfodetail() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/admin/${this.$route.params.id}`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ name: 'HelpView' });
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    },







    // 데이터 확인
    checkData(){
      console.log(this.certificate_row)
      console.log(this.education_row)
      console.log(this.career_row)
    },
    // 뒤로가기
    moveBack(){
      this.$router.push({ name: 'signupcounselor' })
    },
  
    // 행 삭제
    removeEducationRow(row1){
      this.education_row.splice(row1, 1);
      // this.education_row.remove(row);
    },
    addEducationRow(){
      this.education_row.push({
        degree:'',
        school:'',
        major:'',
        is_graduate:'',
        education_file:'',
      })
    },
    removeCertificateRow(row2){
      this.certificate_row.splice(row2,1);
    },
    addCertificateRow(){
      this.certificate_row.push({
        certificate_name:'',
        certificate_number:'',
        certificate_agency:'',
        certificate_file:'',
      })
    },
    removeCareerRow(row2){
      this.career_row.splice(row2,1);
    },
    addCareerRow(){
      this.career_row.push({
        career_name:'',
        career_content:'',
        career_period:'',
      })
    },

    // 여기부터 정보 가져오기 


    
    counselorUpdate() {
      const id = this.id
      const password = this.password
      const name = this.name
      const email = this.email
      const tel = this.tel
      const gender = this.gender
      const enterprise_id = this.enterprise_id
      const certificate = this.certificate_row
      const education = this.education_row
      const career = this.career_row

      const payload ={
        id: id,
        password: password,
        name: name,
        email: email,
        tel: tel,
        gender: gender,
        enterprise_id: enterprise_id,
        certificate: certificate,
        education: education,
        career: career,
      }
      console.log(career)
      if(career.length == 0 || (career != null && this.career_row[0].career_name != null ))
        career == null
      
      let isDataExistAllRow = true;
      education.forEach(el => {
        if(!el.degree || !el.education_file||!el.is_graduate || !el.major || !el.school) isDataExistAllRow = false;
      });
      if(isDataExistAllRow)
        certificate.forEach(el => {
          if(el.certificate_file == null) isDataExistAllRow = false;
        })

      if(!isDataExistAllRow){
        this.$swal("학력, 자격증 모든 빈칸을 채워주세요");
      }
      else{
      this.$store.dispatch('counselorUpdate', payload)
      this.$router.push({name:'login'})
      }


    }

    
  }
}
</script>

<style>

</style>