<template>
  <div id="homeview">
    <div id="hand">
      <h1>소중한 당신을 위해</h1>
      <h1>여기, 소담이 함께합니다.</h1> <br>
      <input id="searhBar"
        class="search-input"
        type="text"
        placeholder="상담사, 카테고리 검색.."
      />
    </div>
    <HelloWorld/>

  </div>
</template>

<script>
// @ is an alias to /srcz

import HelloWorld from '@/components/HelloWorld.vue'


export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },

}
</script>
<style>
#homeview {

}

#hand {
  margin-top: 61px;
  /* width: 100%; */
  background-image: url('../assets/hand.png');
  background-color: aliceblue;
  background-repeat: no-repeat;
  background-size: cover;
  opacity: 0.7;
  text-align: start;
  padding-top: 20px;
  padding-bottom: 40px;
  padding-left: 50px;
  padding-right: 50px;
  border: 1px solid red;
}
#searhBar {
  width: 100%;
  height: 35px;
  /* padding: auto; */
  margin: 0 auto;
}
</style>