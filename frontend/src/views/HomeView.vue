<template>
  <div id="fh5co-main">
    <!--    <div class="fh5co-narrow-content">-->
    <div class="mb-3">
      <v-parallax
        src="@/assets/images/backimg.jpg"
      >
        <div class="d-flex flex-column fill-height justify-center align-center text-white">
          <h1 class="text-h4 font-weight-thin mb-4">
            소중한 상담
          </h1>
          <h4 class="subheading">
            여러분과 함께
          </h4>
        </div>
      </v-parallax>
    </div>
    <v-container class="mb-5">
      <v-container>
        <h2>추천 상담사</h2>

        <vueper-slides
            class="no-shadow mx-auto pb-2"
            :visible-slides="3"
            slide-multiple
            :gap="3"
            :slide-ratio="1 / 4"
            :dragging-distance="200"
            :breakpoints="{ 800: { visibleSlides: 2, slideMultiple: 2 } }">

          <vueper-slide v-for="(slide, i) in this.counselorInfo" :key="i" :image="slide.profileImg" @click="moveTo(slide.id)"/>

        </vueper-slides>
      </v-container>
    </v-container>
    <ConsultantList/>
  </div>

</template>

<script>
// @ is an alias to /srcz

import ConsultantList from '@/components/ConsultantCard/ConsultantList.vue'
// import ConsultantCardSearch from '@/components/ConsultantCard/ConsultantCardSearch.vue'
import {VueperSlides, VueperSlide} from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
import axios from "axios";
// import ConsultantCard from "@/components/ConsultantCard/ConsultantCard.vue";

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name: 'HomeView',
  components: {
    ConsultantList,
    // ConsultantCardSearch,
    VueperSlides,
    VueperSlide,

  },
  data() {
    return {
      counselorInfo: null,
      nullProfileImg: require('@/assets/images/사람altimg.png'),
    }
  },
  methods: {
    getCounselorInfo() {
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/counselor/`,
      })
          .then(res => {
            let jsonData = JSON.parse(JSON.stringify(res.data.content))
            this.counselorInfo = jsonData
          })
    },
    searchCounselor(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/counselor/test`,
        data:{
          name: this.SearchData
        }
      })
          .then(res=>{
            console.log("SearchData:"+res.data)
            console.log(res.data)
            this.checkInfo = res.data
          })
    },
    moveTo(id){
      this.$router.push({ name: 'consultantcarditem', params: {id:id} })
    },
  },
  created() {
    this.getCounselorInfo()
  }

}
</script>
<style>
.fh5co-page {
  font-family: "Roboto", Arial, sans-serif;
  font-weight: 300;
  font-size: 20px;
  line-height: 1.6;
  color: rgba(0, 0, 0, 0.5);
}

.five h2 {
  text-align: center;
  font-size: 22px;
  font-weight: 700;
  color: #202020;
  text-transform: uppercase;
  word-spacing: 1px;
  letter-spacing: 2px;
}

.five h2 span {
  margin-top: 40px;
  text-transform: none;
  font-size: .75em;
  font-weight: normal;
  font-style: italic;
  font-family: "Playfair Display", "Bookman", serif;
  color: #999;
  letter-spacing: -0.005em;
  word-spacing: 1px;
  /*letter-spacing: none;*/
}

.five h2:before {
  position: absolute;
  left: 0;
  bottom: 38px;
  width: 60px;
  height: 4px;
  content: "";
  left: 50%;
  margin-left: -30px;
  background-color: #dfdfdf;
}

.fh5co-heading animate-box {
  font-family: "Roboto", Arial, sans-serif;
  font-weight: 300;
  font-size: 45px;
  line-height: 1.6;
  color: rgba(0, 0, 0, 0.5);
}

#fh5co-main {
  width: 100%;
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

.bgImg {
  background-image: url('@/assets/images/backimg.jpg');
  width: 100%;
  background-position: center center;
  background-repeat: no-repeat;
}

.work-item {
  margin-bottom: 30px;
}

.work-item a {
  border: none;
  text-align: center;
}

.work-item a img {
  margin-bottom: 10px;
  float: left;
  border: 10px solid transparent;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}

.work-item a h3 {
  font-size: 20px;
  color: #000;
  margin-bottom: 10px;
}

.work-item a p {
  font-size: 14px;
  color: #cccccc;
  margin-bottom: 0;
}

.work-item a:hover, .work-item a:active, .work-item a:focus {
  text-decoration: none;
}

.work-item a:hover img, .work-item a:active img, .work-item a:focus img {
  border: 1px solid #000;
}

h1, h2, h3, h4, h5, h6 {
  color: #000;
  font-family: "Montserrat";
  font-weight: 700;
  margin: 0 0 30px 0;
}

.vueperslide__image {
  transform: scale(1.5) rotate(-10deg);
}

.vueperslide__title {
  font-size: 7em;
  opacity: 0.7;
}

.vueperslides--fixed-height {
  height: 500px;
}
</style>