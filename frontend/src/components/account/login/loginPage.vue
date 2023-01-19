<template>
  <div class="row">
    <loginImage style="float:left"/>
    <loginDetail style="float:left"/>
  </div>
</template>

<script>
import loginImage from './loginImage.vue';
import loginDetail from './loginDetail.vue';

export default {
  name:'loginView',
  components: {
    loginImage,
    loginDetail
  }
}
</script>

<style>

</style>