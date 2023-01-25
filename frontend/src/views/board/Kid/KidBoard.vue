<template>
  <div id="KidBoard">
    <div id="worryBoard">
      <br>
      <h1>고민 게시판</h1>
      <br>
      <div id="worryCategory"> 
        <div id="KidCategory" class="row" >아동 상담</div>
        <div id="TeenCategory" class="row">청소년 상담</div>
        <div id="AdultCategory" class="row">성인 상담</div>
        <div id="CoupleCategory" class="row">부부 상담</div>
        <div id="OldCategory" class="row">노년 상담</div>
        <div id="GuitarCategory" class="row" >기타 상담</div>
      </div>
      <div>

      </div>
    </div>
    <div>
      
    </div>
    <KidBoardList/>
  </div>
</template>

<script>
// @ is an alias to /srcz
import KidBoardList from '@/components/BoardItem/KidBoardList.vue'

export default {
  name: 'KidBoard',
  components: {
    KidBoardList
  },
}
</script>


<style>
#KidBoard {
  /* 반응형 x */
  width: 1200px;
  margin: 0 auto;
}

#worryBoard {
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
#worryCategory {
  width:100%; 
  height:50px; 
  line-height: 50px;
  /* justify-content: center; */
  text-align: center;
  color: white;
  background-color:rgba(96, 96, 96, 0.5);
} 

#KidCategory {
  float:left;
  width: 150px;
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
  width: 150px;
  height:50px; 
  font-weight: 700;
  /* background-color: rgba(0, 0, DD, 0.5); */
  border-left: 1px solid white;
  border-top: 1px solid white;
}
#AdultCategory {
  float:left;
  width: 150px;
  height:50px; 
  font-weight: 700;
  /* background-color: #ECE8DD; */
  border-left: 1px solid white;
  border-top: 1px solid white;
}
#CoupleCategory {
  float:left;
  width: 150px;
  height:50px; 
  font-weight: 700;
  /* background-color: #ECE8DD; */
  border-left: 1px solid white;
  border-top: 1px solid white;
}
#OldCategory {
  float:left;
  width: 150px;
  height:50px; 
  font-weight: 700;
  /* background-color: #ECE8DD; */
  border-left: 1px solid white;
  border-top: 1px solid white;
}
#GuitarCategory {
  float:left;
  width: 150px;
  height:50px; 
  font-weight: 700;
  /* background-color: #ECE8DD; */
  border-left: 1px solid white;
  border-top: 1px solid white;
  border-right: 1px solid white;
}
</style>