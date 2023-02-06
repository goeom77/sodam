<template>
  <div id="AlarmView">
    <h1>여긴 알람뷰</h1>
    <AlarmViewList/>
  </div>
</template>

<script>
import AlarmViewList from '../alarm/AlarmViewList.vue'

export default {
  name: 'AlarmView',
  components: {
    AlarmViewList,
  },
  created() {
    this.getAlarmArticles()
    this.getAlarmArticlesSangdam()
    this.getAlarmArticlesBoard()
    this.getAlarmArticlesHelp()
    this.getAlarmArticlesGuitar()
  },
  methods: {
    getAlarmArticles() {
    this.$store.dispatch('getAlarmArticles')
    },
    getSangdamAlarmArticles() {
    this.$store.dispatch('getSangdamAlarmArticles')
    },
    getBoardAlarmArticles() {
    this.$store.dispatch('getBoardAlarmArticles')
    },
    getHelpAlarmArticles() {
    this.$store.dispatch('getHelpAlarmArticles')
    }, 
    getGuitarAlarmArticles() {
    this.$store.dispatch('getGuitarAlarmArticles')
    },
  }

}
</script>

<style>

</style>