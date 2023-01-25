<template>
  <div id="announce">
    <div id="helpBoard">
      <br>
      <h1>HELP DESK</h1>
      <br>
      <div id="helpCategory"> 
        <router-link to="/KidBoard" id="AnnounceCategory" class="row" >공지사항</router-link> &nbsp;&nbsp;&nbsp;
        <router-link to="/" id="InquiryCategory" class="row">1:1 문의</router-link>&nbsp;&nbsp;&nbsp;
      </div>
      <div>

      </div>
    </div>
    <div>
      
    </div>
    <!-- <announceList/> -->
  </div>
</template>

<script>
// @ is an alias to /srcz
// import announceList from '@/components/BoardItem/announceList.vue'

export default {
  name: 'KidBoard',
  components: {
    // announceList
  },
}
</script>


<style>
#announce {
  /* 반응형 x */
  width: 1200px;
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
  margin-top: 61px;
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

}
#helpCategory {
  width:100%; 
  height:50px; 
  line-height: 50px;
  /* justify-content: center; */
  text-align: center;
  background-color:rgba(96, 96, 96, 0.5);
} 

#AnnounceCategory {
  float:left;
  width: 450px;
  height:50px; 
  margin-left: 150px;
  font-weight: 700;
  background-color: #579BB1;
  border-left: 1px solid white;
  border-top: 1px solid white;
}
/* ECE8DD */
#InquiryCategory {
  float:left;
  width: 450px;
  height:50px; 
  font-weight: 700;
  /* background-color: rgba(0, 0, DD, 0.5); */
  border-left: 1px solid white;
  border-top: 1px solid white;
  border-right: 1px solid white;
}

</style>