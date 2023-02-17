<template>
  <div id="fh5co-main">
    <div class="fh5co-narrow-content">
      <v-row>
        <v-card width="100%">
          <v-img
            height="240"
            src="@/assets/images/hand.png"
            cover
            class="text-white align-end  pb-4 pl-4"
          >
          
            <v-toolbar
              color="rgba(0, 0, 0, 0)"
              theme="dark"
            >
              <v-toolbar-title class="text-h3">
                안녕하세요 {{ this.name }}님!
              </v-toolbar-title>
            </v-toolbar>
          </v-img>

          <v-card-text>
            <div style="height:20px"></div>
            <v-row>
              <v-card
                class="mx-auto"
                width="65%"
              >
                <MyPostViewList/>
              </v-card>
              <v-card
                class="mx-auto pa-1"
                width="30%"
              > 
                <div class="font-weight-bold mt-3">
                  <h3>Today</h3>
                </div>
                <SangdamView/>
              </v-card>
            </v-row>
            <v-timeline density="compact" align="start">
              <v-timeline-item
                v-for="message in messages"
                :key="message.time"
                :dot-color="message.color"
                size="x-small"
              >
                <div class="mb-4">
                  <div class="font-weight-normal">
                    <strong>{{ message.from }}</strong> @{{ message.time }}
                  </div>
                  <div>{{ message.message }}</div>
                </div>
              </v-timeline-item>
            </v-timeline>
          </v-card-text>
        </v-card>
      </v-row>
    </div>
  </div>
</template>
  
<script>
import axios from 'axios'
// import CheckEditInformation from '@/views/my/edit/CheckEditInformation.vue'
// import LikeView from '@/views/my/like/LikeView.vue'
import MyPostViewList from '@/views/my/mypost/MyPostViewList.vue'
import SangdamView from '@/views/my/sangdam/SangdamView.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL


export default {
  name: 'MyPage',
  components: {
    // CheckEditInformation,
    // LikeView,
    MyPostViewList,
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