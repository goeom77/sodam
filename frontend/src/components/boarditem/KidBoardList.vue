<template>
  <div id="KidBoardList" >
    <div id="WriteButton">
      <router-link to="/KidBoard" id="KidBoardListAll">전체 (5)</router-link> 
      <router-link to="/KidBoardCreate" id="KidBoardCreateButton" class="KidBoardCreateButton" >글쓰기</router-link> 
    </div>
    <KidBoardListItem
      v-for="(KidBoardarticle, index) in KidBoardarticles.content"
      :key="KidBoardarticle.postId"
      :KidBoardarticle="KidBoardarticle"
      :index="index"
      :limit="KidBoardListPage"
    /> 
    <div v-if="KidBoardarticles" class="text-center">
      <v-pagination
        v-model="this.KidBoardListPage"
        :length="5"

      ></v-pagination>
    </div>
    <!-- :total-rows="KidBoardarticles.length"
        :per-page="dataPerPage" -->

  </div>

</template>

<script>


import KidBoardListItem from '@/components/boarditem/KidBoardListItem.vue'



export default {
  name: 'KidBoardList',
  data() {
    return {
      KidBoardListPage: 1,
      dataPerPage: 10,
    }
  },

  components: {
    KidBoardListItem
  },
  computed: {
    KidBoardarticles() {
      return this.$store.state.KidBoardarticles
    },
    // numOfPages() {
    //   return Math.ceil(this.$store.state.KidBoardarticles.length / this.dataPerPage);
    // }
  },
  
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