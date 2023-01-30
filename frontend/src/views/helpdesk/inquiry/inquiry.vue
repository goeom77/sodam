<template>
  <div id="inquiry">
    <div id="helpBoard">
      <br>
      <h1>HELP DESK</h1>
      <br>
      <div id="helpCategory"> 
        <router-link to="/announce" id="AnnounceCategory" class="CategoryClass" >공지사항</router-link>
        <router-link to="/inquiry" id="InquiryCategory" class="CategoryClass">1:1 문의</router-link>
      </div>
      <div>

      </div>
    </div>
    <div>
      
    </div>
    <InquiryList/>
  </div>
</template>

<script>
// @ is an alias to /srcz
import InquiryList from '@/components/HelpItem/InquiryList.vue'

export default {
  name: 'inquiry',
  components: {
    InquiryList
  },
}
</script>


<style>
#inquiry {
  /* 반응형 x */
  width: 1255px;
  margin: 0 auto;
}

a {
  text-decoration: none;
  color: white;
}

a:visited {
  background-color: #579BB1;
}


#helpBoard {
  /* margin-top: 61px; */
  /* width: 100%; */
  background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('@/assets/hand.png');
  background-color: aliceblue;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  /* background-size: cover; */
  text-align: center;
  font-size: large;
  font-weight: 100;
  padding-top: 20px;
  height: 250px;
}
#helpCategory {
  width:100%; 
  height:50px; 
  display: grid;
  grid-template-columns: 1fr 1fr;
  line-height: 53.5px;
  text-align: center;
  padding-left: 60px;
  padding-right:60px;
  background-color:rgba(96, 96, 96, 0.5);
} 

#helpCategory a.router-link-exact-active {
  background-color: #579BB1;
}

.CategoryClass {
  float:left;
  height:50px; 
  font-weight: 700;
  border: 1px solid white;
  border-bottom: 0px;
}

</style>