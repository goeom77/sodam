<template>
    <div v-if="(limit-1)*10 <= index && index < limit * 10">
      <div v-if="!this.updateStatus" id="comment_container">
        <div class="comment-detail">
      <div>
        <!-- <router-link style="text-decoration: none; color:gray; font-size:13px;" v-if="counselorName" :to="{name: 'ProfileView', params: {counselorName: this.counselorName}}">
        <img id="comment-img" :src="imgSrc">
          {{ this.counselorName }}
          </router-link> -->
      ┖ {{ KidBoardComment.content }}
      </div>
      <div style="margin-top:3px;">
        
        <a style="cursor:pointer; color:gray; margin-left: 8px; font-size:13px;" v-if="this.userid === this.KidBoardComment.counselorId" @click="getCommentUpdate">수정하기</a>
        <a style="cursor:pointer; color:gray; margin-left: 8px; font-size:13px;" v-if="this.userid === this.KidBoardComment.counselorId" @click="deleteComment">삭제</a>
      </div>
    </div>
      <hr style="margin:0px;">
    </div>
      <div v-if="this.updateStatus">
        <form @submit.prevent="updateComment" style="border-radius:0px; height:39px;  display:flex; justify-content: space-between; width: 100px;">
          <div>
            <input class="form-control" style="width:742px; " placeholder="댓글을 입력해주세요" aria-label="Please input the title" aria-describedby="basic-addon1" type="text" id="content" v-model.trim="content">
          </div>
          <div>
            <input class="btn btn-danger" type="submit" id="submit" value="수정">
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'

  const API_URL = "http://127.0.0.1:8080"

  export default {
    name: 'KidBoardCommentList',
    data() {
      return{
        username: null,
        userid: null,
        updateStatus: false,
        content: null,
        profileImageUrl: null,
      }
    },
    computed: {
      imgSrc() {
        return this.profileImageUrl
      },
    },
    props: {
      KidBoardComment: Object,
      index: Number,
      limit: Number,
    },
    // created() {
    //   this.getUserName()
    // },
    methods: {
      // getProfileImage() {
      //   axios({
      //     method: 'get',
      //     url: `${API_URL}/api/accounts/profile_image/${this.KidBoardComment.comment_user}/`,
      //     headers: { 
      //       'Content-Type': 'multipart/form-data',
      //           Authorization: `Token ${this.$store.state.token}`
      //         },
      //     })
      //     .then((res) => {
      //       this.profileImageUrl=`${API_URL}${res.data.profile_image}`
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     })
      // },
      deleteComment() {
        axios({
          method: 'delete',
          url: `${API_URL}/api/trouble/${this.KidBoardComment.commentId}/`,
          headers: { 
              Authorization: `Token ${this.$store.state.token}`
          }
        })
        .then(() => {
          this.$emit('delete-comment')
        })
        .catch((err) => {
          console.log(err)
        })
      },
      getUserName() {
        axios({
          method: 'post',
          url: `${API_URL}/api/counselor/${this.KidBoardComment.counselorId}`,
          data: {
            userid: this.KidBoardComment.comment_user
          },
          headers: { 
              Authorization: `Token ${this.$store.state.token}`
          }
        })
        .then((res) => {
          this.username = res.data.username
          this.userid = res.data.userid
        })
        // .then(() => {
        //   this.getProfileImage()
        // })
        .catch((err) => {
          console.log(err)
        })
    },
    getCommentUpdate() {
        axios({
          method: 'get',
          url: `${API_URL}/api/trouble/${this.KidBoardComment.id}/`,
          headers: { 
              Authorization: `Token ${this.$store.state.token}`
          }
        })
        .then((res) => {
          this.content = res.data.content
          this.updateStatus = true
        })
        .catch((err) => {
          console.log(err)
        })
      },
      updateComment() {
        axios({
          method: 'put',
          url: `${API_URL}/api/trouble/${this.KidBoardComment.id}/`,
          data: {
            content: this.content,
          },
          headers: { 
              Authorization: `Token ${this.$store.state.token}`
          }
        })
        .then(() => {
          this.$emit('update-comment')
          this.updateStatus = false
        })
        .catch((err) => {
          console.log(err)
        })
      },
  },
    
  }
  </script>
  
  <style>
  #comment_container {
    width: 800px;
    /* border: 2px solid white; */
    background-color: #2d3442;
    color: white;
  }
  .comment-detail {
    display: flex;
    justify-content: space-between;
    margin-left: 5px;
    margin-right: 15px;
  }
  #comment-img {
    width: 25px;
    height: 25px;
    border-radius: 50%;
    object-fit: cover;
    margin: 5px;
    margin-right: 6px;
  }
  </style>