<template>
  <div class="row animate-box" data-animate-effect="fadeInLeft">
    <div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 work-item">
      <ConsultantCard
        v-for="(counselor,idx) in counselorInfo"
        :key="idx"
        :counselor="counselor"/>
      <a href="work.html">
        <img src="images/work_1.jpg" alt="Free HTML5 Website Template by FreeHTML5.co" class="img-responsive">
        <h3 class="fh5co-work-title">Work Title Here</h3>
        <p>Illustration, Branding</p>
      </a>
    </div>
  </div>


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
        <ConsultantCard
        v-for="(counselor,idx) in counselorInfo"
        :key="idx"
        :counselor="counselor"/>
      </div>
    </div>
  </div>
</template>

<script>
import ConsultantCard from '@/components/ConsultantCard/ConsultantCard'
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'ConsultantList',
  components: {
    ConsultantCard
  },
  data(){
    return{
      counselorInfo: null,
    }
  },
  methods:{
    getCounselorInfo(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/counselor/`,
      })
      .then(res=>{
        console.log(res)
        this.counselorInfo = res.data.content
      })
    },
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

<style>

.work-item {
  margin-bottom: 30px;
}
.work-item a {
  border: none;
  text-align: center;
}
.work-item a img {
  margin-bottom: 10px;
  float: left;
  border: 10px solid transparent;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
.work-item a h3 {
  font-size: 20px;
  color: #000;
  margin-bottom: 10px;
}
.work-item a p {
  font-size: 14px;
  color: #cccccc;
  margin-bottom: 0;
}
.work-item a:hover, .work-item a:active, .work-item a:focus {
  text-decoration: none;
}
.work-item a:hover img, .work-item a:active img, .work-item a:focus img {
  border: 1px solid #000;
}
/* h3 {
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
  }   */
</style>
