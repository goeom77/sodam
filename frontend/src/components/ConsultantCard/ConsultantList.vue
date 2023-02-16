<template>
  <div class="counselor-container">
    <div class="searchBarArea">
      <input id="searhBar"
        class="search-input"
        type="text"
        v-model="this.SearchData"
        placeholder="상담사, 카테고리 검색.."
        @input="searchCounselor()"
        />
    </div>
      
    <div class="row animate-box" data-animate-effect="fadeInLeft">
      <div v-if="this.checkInfo">
        <div class="d-flex flex-wrap">
            <ConsultantCard
              v-for="(counselor,idx) in checkInfo"
              :key="idx"
              :counselor="counselor"/>
        </div>
      </div>
      <div v-else-if="this.checkInfo===null"> 
        <div class="d-flex flex-wrap">
          <ConsultantCard
          v-for="(counselor,idx) in counselorInfo"
          :key="idx"
          :counselor="counselor"/>
        </div>
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
      SearchData: null,
      checkInfo:null
    }
  },
  methods:{
    getCounselorInfo(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/counselor/`,
      })
      .then(res=>{
        let jsonData = JSON.parse(JSON.stringify(res.data.content))
        this.counselorInfo = jsonData
      })
    },
    searchCounselor(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/counselor/search`,
        data:{
          keyword: this.SearchData
        }
      })
      .then(res=>{
        console.log(res.data)
        this.checkInfo = res.data.content
      })
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
.searchBarArea {
  margin-bottom: 5%;
}
.animate-box {
  padding: 0 7%;
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
