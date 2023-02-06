<template>
  <div id="Inquiry">
    <div id="inquiryhelpBoard">
      <div id="inquiryhelpBoardtitle">
        <h1>고민 게시판</h1>
      </div>

      <div id="inquiryhelpCategory"> 
        <router-link to="/Announce" id="AnnounceCategory" class="CategoryClass" >공지사항</router-link>
        <router-link to="/Inquiry" id="InquiryCategory" class="CategoryClass">1:1 문의</router-link>
      </div>
    </div>
    <div>
      
    </div>
    <InquiryList/>
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
import InquiryList from '@/components/helpitem/InquiryList.vue'

export default {
  name: 'Inquiry',
  components: {
    InquiryList,
  },
  data () {
      return {

      }
    },
  created() {
    this.getInquiryArticles()
  },
  methods: {
    getInquiryArticles() {
    this.$store.dispatch('getInquiryArticles')
    }
  }

}
</script>


<style>
#Inquiry {
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


#inquiryhelpBoard {
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
#inquiryhelpCategory {
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
#inquiryhelpBoardtitle {
  position: absolute;
  left: 50%; 
  bottom: 50%; 
  transform: translate(-50%);
}


#inquiryhelpCategory a.router-link-exact-active {
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