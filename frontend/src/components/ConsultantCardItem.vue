<template>
  <div>
    <!-- 사진 -->
    <div></div>
    <!-- 설명 -->
    <div></div>
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
      <datepicker
        v-model="date"
        lang="ko"
        :lowerLimit="new Date()"
        :clearable="false"
        :format="customFormatter"
      />
    </div>
  </div>
</template>


<script>
import Datepicker from 'vue3-datepicker';

export default {
  name:'ConsultantCardItem',
  components:{
    Datepicker
  },
  data(){
    return{
      age:null,
      clientId:this.$store.state.userSignupData.id,
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
      counselorId:null,
      date:null,
      email:null,
      gender:null,
      genderList:[
        {name:'남', value:"MEN"},
        {name:'여', value:"WOMEN"},
      ],
      name:null,
      state:null,
      tel:null,
    }
  },
  methods:{
    checkDate(){
      console.log(this.age,this.clientId,this.consultType,this.content,this.counselorId)
      console.log(this.picked,this.email,this.gender,this.name,this.state,this.tel)
    },
    customFormatter(date){
      return moment(date).format('YYYY-MM-DD');
    }
  },
}
</script>



<style>

</style>