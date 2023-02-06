<template>
  <div id="KidBoard">
    <div id="worryBoard">
      <div id="worryBoardtitle">
        <h1>고민 게시판</h1>
      </div>

      <div id="worryCategory"> 
        <router-link to="/KidBoard" id="KidCategory" class="CategoryClass" >아동 상담</router-link> 
        <router-link to="/KidBoard" id="TeenCategory" class="CategoryClass">청소년 상담</router-link>
        <router-link to="/" id="AdultCategory" class="CategoryClass">성인 상담</router-link>
        <router-link to="/KidBoard" id="CoupleCategory" class="CategoryClass">부부 상담</router-link>
        <router-link to="/KidBoard" id="OldCategory" class="CategoryClass">노년 상담</router-link>
        <router-link to="/KidBoard" id="GuitarCategory" class="CategoryClass">기타 상담</router-link>
      </div>
    </div>
    <div>
      
    </div>
    <KidBoardList
    
    />
    <!-- <div class="text-center">
      <v-pagination
        v-model="KidBoardListpage"
        :total-rows="comments.length"
        :per-page="10"
      ></v-pagination>
    </div> -->
  </div>
</template>

<script>
// @ is an alias to /srcz
import KidBoardList from '@/components/boarditem/KidBoardList.vue'


export default {
  name: 'KidBoard',
  components: {
    KidBoardList,
  },
  data () {
      return {

      }
    },
  created() {
    this.getKidBoardArticles()
  },
  methods: {
    getKidBoardArticles() {
    this.$store.dispatch('getKidBoardArticles')
    }
  }

}
</script>


<style>
#KidBoard {
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


#worryBoard {
  background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('@/assets/images/hand.png');
  background-color: aliceblue;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  text-align: center;
  font-size: large;
  font-weight: 100;
  padding-top: 20px;
  height: 250px;
  position: relative;
}
#worryCategory {
  width:100%; 
  /* width: 50vw; */
  height:60px; 
  line-height: 65px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
  background-color:rgba(96, 96, 96, 0.5);
  padding-left: 60px;
  padding-right:60px;
  position: absolute;
  bottom: 0px;
} 
#worryBoardtitle {
  position: absolute;
  left: 50%; 
  bottom: 50%; 
  transform: translate(-50%);
}


#worryCategory a.router-link-exact-active {
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