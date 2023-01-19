<template>
  <div class="row">
    <LoginImage style="float:left"/>
    <LoginDetail style="float:left"/>
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

</style>