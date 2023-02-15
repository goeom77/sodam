<template>
  <div>
    <div id="fh5co-main" >
      <div class="fh5co-narrow-content">
        <div style="float: left;">
          <div style="margin-top: 100px">
            <h1> 안녕하세요 {{ this.name }}님!</h1>
          </div>
          <br>
          <br>
          <div style=" float: left; width: 66%;">
            <h3 style=" padding-top: 10px; padding-lef: 10px; border: solid 1px gray">최근 활동</h3>
            <MyPostView/>
  
            <!-- <LikeView/> -->
            <!-- <SangdamView/> -->
  
          </div>
        </div>
      </div>
    </div>
  </div>

</template>
  
<script>
 import axios from 'axios'
// import CheckEditInformation from '@/views/my/edit/CheckEditInformation.vue'
import LikeView from '@/views/my/like/LikeView.vue'
import MyPostView from '@/views/my/mypost/MyPostView.vue'
import SangdamView from '@/views/my/sangdam/SangdamView.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL


  export default {
    name: 'MyPage',
    components: {
      // CheckEditInformation,
      LikeView,
      MyPostView,
      SangdamView,
    },
    data(){
      return{
        tab:null,
        age: '',
        commonCodeId: '',
        email: '',
        gender: '',
        id: '',
        name: '',
        profileImg: '',
        tel: ''
      }
    },
    created() {
      this.getUserDetail() 
    },
    methods: {
      getUserDetail() {
        axios({
          method: 'get',
          url: `${VUE_APP_API_URL}/api/client/${this.$store.state.payload.id}`,
        })
        
        .then((res) => {
          console.log(this.$route.params.postId)
          this.gender= res.data.gender
          this.age = res.data.age
          this.commonCodeId= res.data.commonCodeId,
          this.email= res.data.email,
          this.id= res.data.id,
          this.name= res.data.name,
          this.profileImg= res.data.profileImg,
          this.tel= res.data.tel
        })
      }
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