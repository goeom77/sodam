<template>
  <div id="homeview">
    <div id="hand">
      <div id="ImageLetter">
        <h1>소중한 당신을 위해</h1>
        <h1>여기, 소담이 함께합니다.</h1> 
        <br>
        <br>
        <input id="searhBar"
          class="search-input"
          type="text"
          placeholder="상담사, 카테고리 검색.."
          />
      </div>
    </div>
    <ConsultantList/>

  </div>
</template>

<script>
// @ is an alias to /srcz

import ConsultantList from '@/components/ConsultantList.vue'


export default {
  name: 'HomeView',
  components: {
    ConsultantList
  },

}
</script>
<style>
#homeview {
  /* 반응형 x */
  width: 1080px;
  margin: 0 auto;
}

#hand {
  /* margin-top: 61px; */
  /* width: 100%; */
  height: 300px;
  background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('../assets/hand.png');
  background-color: aliceblue;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  /* background-size: cover; */
    /* opacity: 0.7; */
  text-align: start;
  font-size: large;
  font-weight: 100;
  padding-top: 20px;
  padding-bottom: 40px;
  padding-left: 50px;
  padding-right: 50px;
}
#searhBar {
  width: 100%;
  height: 35px;
  /* padding: auto; */
  margin: 0 auto;
}
#ImageLetter {
  margin: 30px;
}
</style>