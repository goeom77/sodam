<template>
<div id="fh5co-main">
  <div class="fh5co-narrow-content">
    <div>
      <div>
        <ConsultantCardItemInfo
        :counselorData="this.counselorData"/>
      </div>
    </div>
  <!-- 상담 신청서 start -->
  <v-row justify="center" v-show="this.$store.state.payload.common_code == '2'">
    <v-dialog
      v-model="dialog"
      persistent
    >
      <template v-slot:activator="{ props }">
        <v-btn
          v-bind="props"
        >
        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
          <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
          <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
        </svg>
        </v-btn>
      </template>
      <div class="container">
          <v-card style=" padding-bottom: 50px;">
              <div class=" text-center mt-5 ">
                <h1 >상담 신청서</h1>      
              </div>
              <div class="row ">
                <div class="col-lg-7 mx-auto">
                  <div class="card mt-2 mx-auto p-4 bg-light">
                    <div class="card-body bg-light">
                      <div class = "container">
                        <form id="contact-form" role="form">
                          <div class="controls">
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label for="form_need" @click="checkDate(dueDate)">상담 유형 *</label>
                                  <select id="form_need" class="form-control" v-model="consultType" required="required">
                                    <option 
                                      v-for="(item, index) in selectTypeList"
                                      :key="index"
                                      :value="item.value"
                                      >{{ item.name }}</option
                                    >
                                  </select>
                                  <label for="form_name">성함 *</label>
                                  <input id="form_name" type="text" name="name" v-model="name" class="form-control" placeholder="성함" required="required" data-error="이름은 필수사항입니다">
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label for="form_lastname">성별 *</label>
                                    <select id="form_need" v-model="gender" class="form-control">
                                      <option 
                                        v-for="(item, index) in genderList"
                                        :key="index"
                                        :value="item.value"
                                        >{{ item.name }}</option
                                      >
                                    </select>
                                    <label for="form_name">나이 *</label>
                                    <input id="form_name" type="number" name="name" v-model="age" class="form-control" placeholder="나이" required="required" data-error="이름은 필수사항입니다">
                                </div>
                              </div>
                            </div>
                          <div class="row">
                            <div class="col-md-6">
                              <div class="form-group">
                                <label for="form_email">Email *</label>
                                <input id="form_email" type="email" name="email"  v-model="email" class="form-control" placeholder="E-mail" required="required" data-error="Valid email is required.">
                              </div>
                            </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label for="form_name">TEL</label>
                                  <input id="form_name" type="number" name="name" class="form-control" v-model="tel" placeholder="TEL" required="required" data-error="Firstname is required.">
                              </div>
                            </div>
                          </div>
                          <div>
                            <label for="form_name">상담 기한</label>
                            <datepicker
                              class="form-control"
                              placeholder="YYYY-MM-DD" required="required"
                              v-model="dueDate"
                              lang="ko"
                              :lowerLimit="new Date()"
                              :clearable="false"
                            />
                          </div>
                        <div class="row">
                          <div class="col-md-12">
                            <div class="form-group">
                              <label for="form_message">고민 내용 *</label>
                                <textarea id="form_message" name="message" v-model="content" class="form-control" placeholder="원하시는 상담" rows="4" required="required" data-error="Please, leave us a message."></textarea>
                            </div>
                          </div>
                          <div class="col-md-12 d-flex justify-content-center" style="margin:auto;">
                            <v-btn
                              color="blue-darken-1"
                              variant="text"
                              @click="[reserveConsult(),dialog = false]"

                            >
                              제출
                            </v-btn>
                            <v-btn
                              color="blue-darken-1"
                              variant="text"
                              @click="dialog = false"
                            >
                              취소
                            </v-btn>
                          </div>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            <!-- /.8 -->
            </div>
        <!-- /.row-->
          </div>
        </v-card>
      </div>
    </v-dialog>
  </v-row>
  <!-- 상담 신청서 end -->
  <br>
    <div>
      <ConsultantCardItemReview :counselorData="this.counselorData" />
    </div>
  </div>
  
</div>
</template>




<script>
import ConsultantCardItemReserve from '@/components/ConsultantCard/ConsultantCardItemReserve.vue'
import ConsultantCardItemReview from '@/components/ConsultantCard/ConsultantCardItemReview.vue'
import ConsultantCardItemInfo from '@/components/ConsultantCard/ConsultantCardItemInfo.vue'
import axios from 'axios'
import Datepicker from 'vue3-datepicker'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
    name:'ConsultantCardItem',
    components: {
      ConsultantCardItemReserve,
      ConsultantCardItemReview,
      ConsultantCardItemInfo,
      Datepicker
    },
    props:{
      id:String
    },
    data(){
      return{
        dialog: false,
        tab:null,
        counselorId:null,
        counselorData:[],
        age:null,
      clientId: this.$store.state.payload.id,
      consultType:null,
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
      content:null,
      counselorId: this.id,
      dueDate:null,
      email:null,
      gender:null,
      genderList:[
        {name:'남', value:"MEN"},
        {name:'여', value:"WOMEN"},
      ],
      name:null,
      state:'WAIT',
      tel:null,
      }
    },
    methods:{ 
      changePropstoData(){
        this.counselorId = this.id
      },  
      getCounselorData(){
        axios({
          method:'get',
          url:`${VUE_APP_API_URL}/api/counselor/${this.id}`,
          data:{
            id:this.counselorId
          },

        })
        .then(res=>{
          const counselorData = JSON.parse(JSON.stringify(res.data))
          this.counselorData = counselorData
          this.$store.dispatch('saveCounselor', counselorData)
        })
      },
      dateFormat(dueDate){
      let month = dueDate.getMonth() + 1;
      let day = dueDate.getDate();
      let hour = dueDate.getHours();
      let minute = dueDate.getMinutes();
      let second = dueDate.getSeconds();

      month = month >= 10 ? month : '0' + month;
      day = day >= 10 ? day : '0' + day;
      hour = hour >= 10 ? hour : '0' + hour;
      minute = minute >= 10 ? minute : '0' + minute;
      second = second >= 10 ? second : '0' + second;

      return dueDate.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
    },
    checkDate(dueDate){
      // console.log(this.$store.state.token.[[Target]])
      // const clientId=clientId
      console.log(this.age,this.clientId,this.consultType,this.content,this.counselorData.id)
      console.log(this.dateFormat(dueDate),this.email,this.gender,this.name,this.state,this.tel)
    },
    reserveConsult(){
      axios({
        method:'POST',
        url: `${VUE_APP_API_URL}/api/consultApplicant`,
        data:{
          age: this.age,
          clientId: this.clientId,
          consultType: this.consultType,
          content: this.content,
          counselorId: this.counselorData.id,
          dueDate: this.dueDate,
          email: this.email,
          gender: this.gender,
          name: this.name,
          state: this.state,
          tel: this.tel
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
          console.log(res)
        //   this.$router.push({name:'consultantcarditem'})
        })
      },
    },
    created() {
      this.changePropstoData()
      this.getCounselorData()
  }
}
</script>

<style>

</style>