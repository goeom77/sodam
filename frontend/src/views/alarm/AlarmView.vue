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
}
</script>

<style>

</style>