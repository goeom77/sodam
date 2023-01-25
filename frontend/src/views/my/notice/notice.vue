<template>
  <div id="notice">
    <div id="MyBoard">
      <br>
      <h1>마이페이지</h1>
      <br>
      <div id="myCategory"> 
        <router-link to="/notice" id="NoticeCategory" class="row" >내게 온 알림</router-link> &nbsp;&nbsp;&nbsp;
        <router-link to="/" id="TeenCategory" class="row">상담 내역</router-link>&nbsp;&nbsp;&nbsp;
        <router-link to="/" id="AdultCategory" class="row">내 글 보기</router-link>&nbsp;&nbsp;&nbsp;
        <router-link to="/" id="CoupleCategory" class="row">찜 목록</router-link>&nbsp;&nbsp;&nbsp;
        <router-link to="/" id="OldCategory" class="row">후기 상담</router-link>
        
      </div>
      <div>

      </div>
    </div>
    <div>
      
    </div>
    <!-- <KidBoardList/> -->
  </div>
</template>

<script>
// @ is an alias to /srcz
// import noticeList from '@/components/BoardItem/noticeList.vue'

export default {
  name: 'notice',
  components: {
    // noticeList
  },
  // created() {
  //   this.getKidBoardArticles()
  // },
  // methods: {
  //   getKidBoardArticles() {
  //   this.$store.dispatch('getKidBoardArticles')
  //   }
  // }
}
</script>


<style>
#notice {
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


#MyBoard {
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
#myCategory {
  width:100%; 
  height:50px; 
  line-height: 50px;
  /* justify-content: center; */
  text-align: center;
  background-color:rgba(96, 96, 96, 0.5);
} 

#NoticeCategory {
  float:left;
  width: 180px;
  height:50px; 
  margin-left: 150px;
  font-weight: 700;
  background-color: #579BB1;
  border-left: 1px solid white;
  border-top: 1px solid white;
}
/* ECE8DD */
#TeenCategory {
  float:left;
  width: 180px;
  height:50px; 
  font-weight: 700;
  /* background-color: rgba(0, 0, DD, 0.5); */
  border-left: 1px solid white;
  border-top: 1px solid white;
}
#AdultCategory {
  float:left;
  width: 180px;
  height:50px; 
  font-weight: 700;
  /* background-color: #ECE8DD; */
  border-left: 1px solid white;
  border-top: 1px solid white;
}
#CoupleCategory {
  float:left;
  width: 180px;
  height:50px; 
  font-weight: 700;
  /* background-color: #ECE8DD; */
  border-left: 1px solid white;
  border-top: 1px solid white;
}
#OldCategory {
  float:left;
  width: 180px;
  height:50px; 
  font-weight: 700;
  /* background-color: #ECE8DD; */
  border-left: 1px solid white;
  border-top: 1px solid white;
  border-right: 1px solid white;
}

</style>