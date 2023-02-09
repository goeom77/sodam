<template>
		<div id="fh5co-main">
      <div class="fh5co-narrow-content">
        <h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">소중한 당신을 위해
        </h2>
        <span>여기, 소담이 함께합니다.</span> 
        <br>
        <br>
        <input id="searhBar"
          class="search-input"
          type="text"
          placeholder="상담사, 카테고리 검색.."
          />
      </div>
      <ConsultantList/>
    </div>

</template>

<script>
// @ is an alias to /srcz

import ConsultantList from '@/components/ConsultantCard/ConsultantList.vue'
import ConsultantCardSearch from '@/components/ConsultantCard/ConsultantCardSearch.vue'

export default {
  name: 'HomeView',
  components: {
    ConsultantList,
    ConsultantCardSearch
  },

}
</script>
<style>

#fh5co-main {
  width: 85%;
  float: right;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
@media screen and (max-width: 1200px) {
  #fh5co-main {
    width: 70%;
  }
}
@media screen and (max-width: 768px) {
  #fh5co-main {
    width: 100%;
  }
}
#fh5co-main .fh5co-narrow-content {
  position: relative;
  width: 80%;
  margin: 0 auto;
  padding: 4em 0;
}
@media screen and (max-width: 768px) {
  #fh5co-main .fh5co-narrow-content {
    width: 100%;
    padding: 3.5em 1em;
  }
}
</style>