<template>
  <div class="container" style="padding-top:20px">
    <div v-if="!this.updateStatus">
      <div class="row">
        <div class="col-10">
          {{ BoardComment.counselorName }}상담사님의 답변
        </div>
        <div class="col-2">
          {{ BoardComment.createdAt.split(' ')[0] }}
        </div>
      </div>
      <div style="margin:10px">
        <div>
          {{ BoardComment.content }}
        </div>
        <div style="text-align: right; margin-right:30px; margin-bottom:20px">
          <v-btn append-icon="mdi-pencil" @click="changeStatus">수정</v-btn>&nbsp;
          <v-btn append-icon="mdi-delete" color="red" @click="deleteComment">삭제</v-btn>
        </div>
      </div>
    </div>
    <div v-if="this.updateStatus">
      <div class="row">
        <div class="col-10">
          {{ BoardComment.counselorName }}상담사님의 답변
        </div>
        <div class="col-2">
          {{ BoardComment.createdAt.split(' ')[0] }}
        </div>
      </div>
      <div style="margin:10px">
        <div>
          {{ BoardComment.content }}
        </div>
        <div style="text-align: right; margin-right:30px; margin-bottom:20px">
          <input type="text" style="border:1px solid black; border-radius: 4px; width:100%;" v-model.trim="content">
          <v-btn append-icon="mdi-pencil" @click="updateComment">수정</v-btn>&nbsp;
          <v-btn append-icon="mdi-delete" color="red" @click="deleteComment">삭제</v-btn>
        </div>
      </div>
    </div>
  </div>
  </template>
  
  <script>
  import axios from 'axios'
  import BoardCommentForm from '../boarditem/BoardCommentForm.vue'

  const VUE_APP_API_URL = process.env.VUE_APP_API_URL

  export default {
    name: 'BoardCommentList',
    data() {
      return{
        username: null,
        userid: null,
        updateStatus: false,
        content: null,
        counselorId : null,
        commentId : null,
        profileImageUrl: null,
      }
    },
    components: {
      BoardCommentForm,
    },
    computed: {
      imgSrc() {
        return this.profileImageUrl
      },
    },
    props: {
      BoardComment: Object,
      index: Number,
      limit: Number,
    },
    methods: {
      changeStatus(){
        this.updateStatus=!this.updateStatus
      },
      deleteComment() {
        axios({
          method: 'delete',
          url: `${VUE_APP_API_URL}/api/trouble/comment/${this.BoardComment.commentId}/`,
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
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
        console.log('유저네임 들어왔냐?')
        axios({
          method: 'post',
          url: `${VUE_APP_API_URL}/api/counselor/${this.BoardComment.counselorId}`,
          data: {
            userid: this.BoardComment.comment_user
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then((res) => {
          this.username = res.data.username
          this.userid = res.data.userid
        })

        .catch((err) => {
          console.log(err)
        })
    },
      updateComment() {
        axios({
          method: 'put',
          url: `${VUE_APP_API_URL}/api/trouble/comment/${this.BoardComment.commentId}/`,
          data: {
            content: this.content,
            counselorId: this.counselorId ,
            commentId: this.commentId 
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
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