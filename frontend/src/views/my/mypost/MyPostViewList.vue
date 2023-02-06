<template>
  <div id="MyPostViewList" >
    <div id="WriteButton">
      <!-- <router-link to="/KidBoard" id="KidBoardListAll">전체 (5)</router-link>  -->
      <div id="MyPostViewListAll" @click="MyPostViewListAll">전체 (5)</div>
      <!-- <router-link to="/KidBoardCreate" id="KidBoardCreateButton" class="KidBoardCreateButton" >글쓰기</router-link>  -->
    </div>
    <div>
      
    </div>
    <div>
      <MyPostViewListItem
        v-for="(MyPostarticle, index) in MyPostarticles.content"
        :key="MyPostarticle.postId"
        :MyPostarticle="MyPostarticle"
        :index="index"
        :limit="MyPostListPage"
      /> 
    </div>

    <div v-if="MyPostarticles" class="text-center">
      <v-pagination
        v-model="this.MyPostListPage"
        :length="5"

      ></v-pagination>
    </div>
  </div>

</template>

<script>

import axios from 'axios'
import MyPostViewListItem from '../mypost/MyPostViewListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'KidBoardList',
  data() {
    return {
      MyPostListPage: 1,
      contentlist: [], 
    }
  },
  components: {
    MyPostViewListItem
  },
  computed: {
    MyPostarticles() {
      return this.$store.state.MyPostarticles
    },
  },
  methods: {
    MyPostViewListAll(){
      this.$router.push({ name: 'MyPostView' },)
    },
  }
}
</script>

<style>
#KidBoardList {
  width: 1255px;
}
#WriteButton {
  
  height: 40px;
  line-height: 40px;
  margin-top: 50px;
  margin-left: 60px;
  margin-right: 60px;
  border-bottom: 1px solid #5F5F5F;
  display: flex;
  vertical-align: middle;
  justify-content: space-between;
  padding-left: 10px;
  padding-right: 10px;
  font-weight: 700;
}

.KidBoardCreateButton {
  float:left;
  height: 30px;
  line-height: 25px;
  font-weight: 700;
  border: 1px solid white;
  /* border-bottom: 0px; */
  background-color: #579BB1;
  padding: 3px;
  padding-left: 10px;
  padding-right: 10px;
  border-radius: 5px;
}

#KidBoardListAll {
  color: black;
  
}


</style>