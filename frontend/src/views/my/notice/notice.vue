<template>
  <div id="notice">
    <div id="MyBoard">
      <div id="MyBoardtitle">
        <h1>마이페이지</h1>
      </div>  
      <div id="myCategory"> 
        <router-link to="/notice" id="NoticeCategory" class="CategoryClass" >내게 온 알림</router-link>
        <router-link to="/HistoryView" id="HistoryCategory" class="CategoryClass">상담 내역</router-link>
        <router-link to="/" id="MypostCategory" class="CategoryClass">내 글 보기</router-link>
        <router-link to="/notice" id="LikeCategory" class="CategoryClass">찜 목록</router-link>
        <router-link to="/" id="WritereviewCategory" class="CategoryClass">후기 상담</router-link>
        
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
// import noticeList from '@/components/boarditem/noticeList.vue'

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


#MyBoard {
  /* margin-top: 61px; */
  width: 100%;
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
  position: relative;
}
#myCategory {
  width:100%; 
  height:60px; 
  line-height: 65px;
  /* justify-content: center; */
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  background-color:rgba(96, 96, 96, 0.5);
  padding-left: 60px;
  padding-right:60px;
  position: absolute;
  bottom: 0px;
} 
#MyBoardtitle {
  position: absolute;
  left: 50%; 
  bottom: 50%; 
  transform: translate(-50%);
}

#myCategory a.router-link-exact-active {
  background-color: #579BB1;
}

.CategoryClass {
  float:left;
  height:60px; 
  font-weight: 700;
  border: 1px solid white;
  border-bottom: 0px;
}


</style>