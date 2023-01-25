<template>
  <div id="KidBoardList">
    <h1>여기는 게시판 목록 및 글쓰기 버튼</h1>
    <!-- <router-link :to="{ name: 'KidBoardCreateView' }">[CREATE]</router-link>
    <KidBoardListItem
      v-for="KidBoardarticle in KidBoardarticles"
      :key="KidBoardarticle.id"
      :KidBoardarticle="KidBoardarticle"
    /> -->
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
#KidBoardList {
  text-align: start;
}
</style>