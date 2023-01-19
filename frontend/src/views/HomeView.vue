<template>
  <div id="homeview" class="home">
    <HelloWorld msg="소중한 당신을 위해  여기, 소담이 함께 합니다."/>
    
  </div>
</template>

<script>
// @ is an alias to /srcz

import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script>
<style>
#homeview {
  margin-top: 61px;
  width: 100%;
  background-image: url('../assets/hand.png');
  background-color: aliceblue;
  background-repeat: no-repeat;
  background-size: cover;
  opacity: 0.7;
}
</style>