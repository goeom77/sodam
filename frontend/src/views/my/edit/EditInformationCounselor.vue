<template>
  <!-- 상담사용 -->
    <table class="type03">
      <!-- 이름 -->
      <tr>
        <th scope="row">이름</th>
        <td>{{ this.name}} </td>
      </tr>
      <!-- 전화번호 -->
      <tr>
        <th scope="row">전화번호</th>
        <td>
          <input type="text" name="tel" v-model="this.tel" class="form-control" placeholder="Tel">
        </td>
      </tr>
      <!-- 이메일 -->
      <tr>
        <th scope="row">E-mail</th>
        <td>
          <input type="text" name="email" v-model="this.email" class="form-control" placeholder="E-mail">
        </td>
      </tr>

      <!-- 자기소개 -->
      <tr>
        <th scope="row">상담 분야</th>
        <td>
          <select id="form_need2" class="form-control" v-model="consultTypeList" multiple="multiple">
            <option 
              v-for="(item, index) in selectTypeList"
              :key="index"
              :value="item.value"
              >{{ item.name }}</option>
          </select>
        </td>
      </tr>
      <tr>
        <th scope="row">자기 소개</th>
        <td>
          <textarea id="form_message" name="message" v-model="content" class="form-control" 
          rows="4" required="required" data-error="Please, leave us a message."></textarea>
        </td>
      </tr>
      <!-- 프로필 사진  -->
      <tr>
        <th scope="row">프로필 사진</th>
        <td>
          <v-file-input
            accept="image/*"
            v-model="this.profileImage"
            label="File input"
          ></v-file-input>

          <button type="button" class="btn btn-outline-dark" @click="OnImage">수정</button>
        </td>
      </tr>
    </table>

    <!-- 학력 -->
    <div class="ten">
      <h1>학력</h1>
    </div>
    <div v-for="(edu,idx) in education"
    :key="idx">
    {{ edu.degree }} | {{ edu.school }} | {{ edu.major }} | {{ edu.is_graduate }} | {{ edu.education_file }}

    </div>
    <table id="sign_table">
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


      <!-- 자격증 -->
      <div class="ten">
      <h1>자격증</h1>
    </div>
  <table id="sign_table">
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


    <!-- ㄱ여력 -->
    <div class="ten">
      <h1>경력</h1>
    </div>
    <table id="sign_table">
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
    <div style="padding: 20px;">
      <a style="margin:auto;" class="bn62" @click="counselorUpdate">확인</a>
    </div>
</template>

<script>
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name:'EditInformationCounselor',
  data(){
    return{
      name: '',
      email: '',
      tel:0,
      introduce: '',
      profileImage:null,
      career: '',
      education: '',
      certificate:'',
      id: this.$store.state.payload.id,
      consultTypeList:null,
      selectTypeList: [
        {name:'아동 청소년', value:"CHILD_TEENAGER"},
        {name:'재난', value:"CALAMITY"},
        {name:'부부 및 가족상담', value:"COUPLE_FAMILY"},
        {name:'재활 상담', value:"REHABILITATION"},
        {name:'노인 상담', value:"AGED"},
        {name:'중독 상담', value:"ADDICTED"},
        {name:'정신 건강', value:"MENTAL_HEALTH"},
        {name:'교정 상담', value:"CORRECTION"},
        {name:'진로 상담', value:"COURSE"},
        {name:'상담자 교육', value:"EDUCATION"},
        {name:'성폭력 상담', value:"SEXUAL_VIOLENCY"},
        {name:'상담자 슈퍼비전', value:"SUPERVISION"},
        {name:'스포츠 상담', value:"SPORTS"},
        {name:'학교 상담', value:"SCHOOL"},
      ],
      education_row:[{
        degree:null,
        school:null,
        major:null,
        is_graduate:null,
        education_file: null,
      }],
      certificate_row:[{
        certificate_name:null,
        certificate_number:null,
        certificate_agency:null,
        certificate_file:null,
      }],
      career_row:[{
        career_name:null,
        career_content:null,
        career_period:null,
      }]
    }
  },
  created() {
      this.getcounselordetail()
  },
  methods:{
    OnImage(){
      const formdata = new FormData()
      formdata.append('file', this.profileImage[0])
      return axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/my-page/profile-img/${id}`,
        data: formdata,
        headers:{
          'Content-Type': 'multipart/form-data',
        },
      })
      .then(res=>{
        console.log(res)
      })
    },
    getcounselordetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/counselor/${this.$store.state.payload.id}`,
        data:{
          id: this.$store.state.payload.id
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res => {
        this.name= res.data.name,
        this.email= res.data.email,
        this.tel= res.data.tel,
        this.introduce= res.data.introduce,
        this.consultTypeList = res.data.consultTypeList,
        this.career= res.data.career,
        this.certificate = res.data.certificate,
        this.education= res.data.education
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
        .then((res) => {
          console.log(res)
          this.$router.push({
            name: 'MainEdit1',
            // params: { postId: this.$route.params.postId } 
          })
        })
        .catch((err) => {
          console.log('업데이트 안된단다 재훈아')
          console.log(err)
        })
      },


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
  },
  created(){
    this.getcounselordetail()


  }
}
</script>

<style>

.type03 {
  width:95%;
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  border-left: 3px solid #369;
  margin : 20px 20px;
}
.type03 th {
  background-color: white;
  width: 30px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #153d73;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  text-align: center;

}
.type03 td {
  width: 359px;
  padding: 10px;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}
#sign_table{
  width: 90%;
}

.ten h1 {
  font-size:34px; font-weight:500; text-transform:uppercase;
  margin-left: 20px;
}
.ten h1:before {
    background-color: #369;
    border-radius: 0.25rem;
    content: '';
    display: block;
    height: 0.25rem;
    width: 42px;
    margin-top: 10px;
}
#form_need2{
  width: 300px;
}
</style>