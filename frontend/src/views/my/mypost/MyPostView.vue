<template>
  <div style="margin:auto">
    <h1>MyPost</h1>

    <MyPostViewList/>
  </div>
</template>

<script>

import MyPostViewList from '../mypost/MyPostViewList.vue'

export default {
  name:'MyPost',
  components: {
    MyPostViewList,
  },
}
</script>

<style>

</style>