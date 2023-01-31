<template>
  <div id="HistoryViewList">
    <h1>여기는 리스트 목록
    </h1>
    <HistoryViewListItem
      v-for="HistoryViewarticle in HistoryViewarticles"
      :key="HistoryViewarticle.id"
      :HistoryViewarticle="HistoryViewarticle"
    /> 
    <HistoryViewListItem/>
  </div>

</template>

<script>
import HistoryViewListItem from './HistoryViewListItem.vue';

export default {
  name: 'HistoryViewList',
  components: {
    HistoryViewListItem
  },

  // computed: {
  //   articles() {
  //     return this.$store.state.HistoryViewarticles
  //   }
  // },

}
</script>

<style>
/* #KidBoardList {
  text-align: end;
} */


#HistoryViewList {
  color: black;
}

</style>