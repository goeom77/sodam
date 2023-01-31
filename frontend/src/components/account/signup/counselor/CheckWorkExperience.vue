<template>
  <div>
    <h4>
      <label for="inputTextCareerSummary">상담 경력</label>
    </h4>
    <!-- 상담경력 input박스 -->
    <div @click="checkData">
      <input type="text" id="inputTextCarrerSummary" v-model="career" placeholder="예) 3년 이상, 6개월 이상 ">
      <p>입력하지않는 경우, 프로필에 공란으로 표시됩니다.</p>
    </div>
  </div>
</template>

<script>
export default {
  name:'CheckWorkExperience',
  data(){
    return {
      career:null,
    }
  },
  methods:{
    checkData(){
      console.log(this.career)
    }

  }

}
</script>

<style>

</style>