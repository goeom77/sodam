<template>
  <div id="MyPostViewList" >
    <div>
      <div>
        <h2>여긴 고민게시판 내 글</h2>
        <MyPostViewListItem
          v-for="(MyPostarticle, index) in MyBoardarticles.content"
          :key="MyPostarticle.postId"
          :MyPostarticle="MyPostarticle"
          :index="index"
          :limit="MyPostListPage"
        /> 
      </div>
      <div>
        <h2>여긴 1:1 문의 내 글</h2>
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
  width: 1255px;
}
#WriteButton {
  
  height: 40px;
  line-height: 40px;
  margin-top: 50px;
  margin-left: 60px;
  margin-right: 60px;
  border-bottom: 1px solid #5F5F5F;
  display: flex;
  vertical-align: middle;
  justify-content: space-between;
  padding-left: 10px;
  padding-right: 10px;
  font-weight: 700;
}



#KidBoardListAll {
  color: black;
  
}


</style>