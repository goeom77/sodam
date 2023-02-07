<template>
  <div>
    <!-- 예약 -->
    <div>

      <h1 @click="checkDate(dueDate)">상담 유형 </h1>
      <select id="Typeselect" v-model="consultType">
        <option 
          v-for="(item, index) in selectTypeList"
          :key="index"
          :value="item.value"
          >{{ item.name }}</option
        >
      </select>

      <h1>성함</h1>
      <input type="text" name="name" v-model="name">
      
      <h1>연락처</h1>
      <input type="number" name="name" v-model="tel">
      
      <h1>이메일</h1>
      <input type="email" name="name" v-model="email">
      
      <h1>나이</h1>
      <input type="number" name="name" min="0" max="150" v-model="age">

      <h1>성별</h1>
      <select id="genderselect" v-model="gender" >
        <option 
          v-for="(item, index) in genderList"
          :key="index"
          :value="item.value"
          >{{ item.name }}</option
        >
      </select>
      <h1>고민 내용</h1>
      <textarea name="" id="" cols="30" rows="10" v-model="content"></textarea>
      <h1>상담 기한</h1>





      <datepicker
        v-model="dueDate"
        lang="ko"
        :lowerLimit="new Date()"
        :clearable="false"
      />

      <v-btn
        outlined
        rounded
        text
        @click="reserveConsult"
      >
        Button
      </v-btn>
    </div>
  </div>
</template>


<script>
// import Datepicker from 'vue3-datepicker';

import axios from 'axios'
import Datepicker from 'vue3-datepicker'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name:'ConsultantCardItemReserve',
  components:{
    // DateTimePicker
    Datepicker
  },
  props:{
    counselorData:Object
  },  
  data(){
    return{
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
      counselorId: this.counselorData.id,
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
    // updateDate(){
    //   this.date=value;
    // },
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
      console.log(this.age,this.clientId,this.consultType,this.content,this.clientId)
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
          counselorId: this.counselorId,
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

}
</script>



<style>

</style>