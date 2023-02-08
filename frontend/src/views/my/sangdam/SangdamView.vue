<template>
  <div>
    <button @click="gogogogogogo"> 영상 연결 ㄱㄱ</button>
  </div>
</template>

<script>
export default {
  name: 'SangdamView',
  components: {
  },
  methods: {
    gogogogogogo() {
      this.$router.push({ name: 'VideoPage' },)
    }
  }
}
</script>

<style>

</style>