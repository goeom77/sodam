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
    // this.getSangdamAlarmArticles()
    // this.getBoardAlarmArticles()
    // this.getHelpAlarmArticles()
    // this.getGuitarAlarmArticles()  
  },
  methods: {
    getAlarmArticles() {
      console.log('전체알람 알람뷰')
      this.$store.dispatch('getAlarmArticles')
      this.$store.dispatch('getSangdamAlarmArticles')
      // this.$store.dispatch('getBoardAlarmArticles')
      // this.$store.dispatch('getHelpAlarmArticles')
      // this.$store.dispatch('getGuitarAlarmArticles')
    },
    // getSangdamAlarmArticles() {
    //   this.$store.dispatch('getSangdamAlarmArticles')
    // },
    // getBoardAlarmArticles() {
    //  this.$store.dispatch('getBoardAlarmArticles')
    // },
    // getHelpAlarmArticles() {
    //   console.log('헬프알람 알람뷰')
    //  this.$store.dispatch('getHelpAlarmArticles')
    // }, 
    // getGuitarAlarmArticles() {
    //   this.$store.dispatch('getGuitarAlarmArticles')
    // },
  }

}
</script>

<style>

</style>