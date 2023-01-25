<template>
  <div id="cover">
    <div class="check">
      <LoginImage />
    </div>
    <div class="check">
      <LoginDetail/>
    </div>
  </div>
</template>

<script>
import LoginDetail from '@/components/account/login/LoginDetail.vue'
import LoginImage from '@/components/account/login/LoginImage.vue'

export default {
  name:'LoginPage',
  components: {
    LoginImage,
    LoginDetail
  }
}
</script>

<style>
#cover{
  text-align: center;
}
.check {
  display: inline-block;
}
</style>