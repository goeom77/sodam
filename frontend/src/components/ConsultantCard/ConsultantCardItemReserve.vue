<template>
  <div>
    <!-- 예약 -->
    <div>

      <h1 @click="checkDate">상담 유형 </h1>
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
      <DateTimePicker @update-date="updateDate">
      
      </DateTimePicker>


      <!-- <datepicker
        v-model="dueDate"
        lang="ko"
        :lowerLimit="new Date()"
        :clearable="false"
      /> -->

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
import DateTimePicker from '@/components/ConsultantCard/DateTimePicker.vue'


export default {
  name:'ConsultantCardItemReserve',
  components:{
    DateTimePicker
    // Datepicker

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
      counselorId: null,
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
    updateDate:function(value){
      this.dueDate = value;
    },

    checkDate(){

      // console.log(this.$store.state.token.[[Target]])
      // const clientId=clientId
      console.log(this.age,this.clientId,this.consultType,this.content,this.counselorData.id)
      console.log(this.dueDate,this.email,this.gender,this.name,this.state,this.tel)
    },
    reserveConsult(){
      const age = this.age
      const clientId = this.clientId
      const consultType = this.consultType
      const content = this.content
      const counselorId = this.counselorData.id
      const dueDate = this.dueDate
      const email = this.email
      const gender = this.gender
      const name = this.name
      const state = this.state
      const tel = this.tel

      const payload = {
        age: age,
        clientId: clientId,
        consultType: consultType,
        content: content,
        counselorId: counselorId,
        dueDate: dueDate,
        email: email,
        gender: gender,
        name: name,
        state: state,
        tel: tel
      }
      this.$store.dispatch('reserveConsult', payload)
    },
  },
}
</script>



<style>

</style>