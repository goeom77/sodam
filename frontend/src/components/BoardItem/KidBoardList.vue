<template>
  <div id="KidBoardList">
    <div id="WriteButton">
      <div>전체(5)</div>
      <router-link to="/KidBoardCreate" id="KidBoardCreate" class="KidBoardCreate" >글쓰기</router-link> 
    </div>
    <KidBoardListItem
      v-for="KidBoardarticle in KidBoardarticles"
      :key="KidBoardarticle.id"
      :KidBoardarticle="KidBoardarticle"
    /> 
    <KidBoardListItem/>
  </div>

</template>

<script>
import KidBoardListItem from '@/components/BoardItem/KidBoardListItem.vue'

export default {
  name: 'KidBoardList',
  components: {
    KidBoardListItem
  },
  // computed: {
  //   articles() {
  //     return this.$store.state.KidBoardarticles
  //   }
  // },
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
/* #KidBoardList {
  text-align: end;
} */
#WriteButton {
  height: 40px;
  margin-top: 50px;
  margin-left: 60px;
  margin-right: 60px;
  border-bottom: 1px solid #5F5F5F;
  display: flex;
  justify-content: space-between;
}

.KidBoardCreate {
  float:left;
  height: 30px;
  font-weight: 700;
  border: 1px solid white;
  /* border-bottom: 0px; */
  background-color: #579BB1;
  padding: 3px;
  text-align: center;
}
</style>