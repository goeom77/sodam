<template>
  <div id="MyPostViewList">
    <div id="fh5co-main" >
      <div class="fh5co-narrow-content" style=" margin-left: 0;">
        <div>
          <h3 style=" padding-left: 10px; border: solid 1px gray">최근 게시글</h3>
          <MyPostViewListItem
            v-for="(MyPostarticle, index) in MyBoardarticles.content"
            :key="MyPostarticle.postId"
            :MyPostarticle="MyPostarticle"
            :index="index"
            :limit="MyPostListPage"
          /> 
        </div>
        <div>
          <h3 style="margin-top: 30px; border: solid 1px gray ">최근 1:1 문의</h3>
          <MyInquiryPostViewListItem
            v-for="(MyInquiryPostarticle, index) in MyInquiryarticles.content"
            :key="MyInquiryPostarticle.id"
            :MyInquiryPostarticle="MyInquiryPostarticle"
            :index="index"
            :limit="MyPostListPage"
          /> 
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import axios from 'axios'
import MyPostViewListItem from '../mypost/MyPostViewListItem.vue'
import MyInquiryPostViewListItem from '../mypost/MyInquiryPostViewListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'MyPostViewList',
  data() {
    return {
      MyBoardarticles: [], 
      MyInquiryarticles: [],
      MyPostListPage: 1,
    }
  },
  components: {
    MyPostViewListItem,
    MyInquiryPostViewListItem,
  },
  created() {
    this.getMyInquiryarticles()  
    this.getMyBoardarticless()

  },
  methods: {
    getMyBoardarticless() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/my-trouble`,
        headers: {
          "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          console.log('이거 되라 게시글')
          this.MyBoardarticles = res.data
        })
        .catch((err) => {
          console.log('어림도 없지 게시글')
        })
    },
    getMyInquiryarticles() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/help-desk/qna/list`,
        headers: {
          "Authorization" : `Bearer ${this.$store.state.token.token.access_token}`}
      })
        .then((res) => {
          console.log('이거 되라 문의')
          this.MyInquiryarticles = res.data
        })
        .catch((err) => {
          console.log('어림도 없지 문의')
        })
    },

  }
}
</script>

<style>
#MyPostViewList {
  width: 66%;
}

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