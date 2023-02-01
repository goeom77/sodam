<template>
  <div id="ConsultantList" class="hello">
    <div id="ConsultantCategory">
      <div id="box1">상담사</div>
      <div id="box2">전체, 아동, 청소년, 성인, 부부, 노인, 기타</div>
    </div>
    <div id="ConsultantCategory">
      <div id="box3">
        <p>태그</p>
        <p>태그</p>
        <p>태그</p>
        <p>태그</p>
        <p>태그</p>
      </div>
      <div>
        <ConsultantCard/>
        가나다라
      </div>
    </div>
  </div>
</template>

<script>
import ConsultantCard from '@/components/ConsultantCard'

export default {
  name: 'ConsultantList',
  components: {
    ConsultantCard
  },
  props: {
    msg: String
  },
  methods:{
    getCounselorInfo(){
      this.$store.dispatch('getCounselorInfo')
    }
  },
  created() {
    this.getCounselorInfo()
  }
  // computed: {
  //   ConsultantList() {
  //     return this.$store.getters.getConsultantJsonData
  //   },
  // },
  // beforeMount() {
  //     this.$store.dispatch('getConsultantJson')
  // },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
#ConsultantCategory {

}
#box1 {
  float:left; 
  margin-top: 30px;
  margin-right: 30px;
  margin-bottom: 30px;
  width:20%; 
  height:50px; 
  line-height: 50px;
  /* justify-content: center; */
  text-align: center;
  color: white;
  background-color:#579BB1;}
          
#box2 {
  display:inline-block; 
  margin-top: 30px;
  margin-left: 30px;
  margin-bottom: 30px;
  width:75%; 
  height:50px; 
  line-height: 50px;
  border-top: 1px solid #5F5F5F;
  border-bottom: 1px solid #5F5F5F;
  }  

#box3 {
  float:left; 
  margin-top: 0;
  margin-right: 30px;
  margin-bottom: 0px;
  width:20%; 
  line-height: 50px;
  /* justify-content: center; */
  text-align: center;
  border: 1px solid #5F5F5F;
  }
          
#box4 {
  display:inline-block; 
  margin-top: 30px;
  margin-left: 30px;
  margin-bottom: 0px;
  width:65%; 
  height:50px; 
  line-height: 50px;
  border-top: 1px solid #5F5F5F;
  border-bottom: 1px solid #5F5F5F;
  }  
</style>
