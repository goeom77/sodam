<template>
    <div v-if="(limit-1)*10 <= index && index < limit * 10">
      <div class="container">
        <div v-if="!this.updateStatus">
          <div>
            <div style="font-size:20px">
              {{ QnaComment.writerId }}   |   {{ QnaComment.createdAt.split(' ')[0] }}
            </div>
            <div>
              {{ QnaComment.content }}
            </div>
            <div style="text-align: right; padding-bottom: 20px;">
              <v-btn append-icon="mdi-pencil" @click="changeStatus">
                  수정
                </v-btn>
                <v-btn append-icon="mdi-delete" color="red" @click="deleteComment">
                  삭제
                </v-btn>
            </div>
          </div>
        </div>
        <div v-if="this.updateStatus">
          <div style="font-size:20px">
            {{ QnaComment.writerId }}   |   {{ QnaComment.createdAt.split(' ')[0] }}
          </div>
          <div>
            {{ QnaComment.content }}
          </div>
            <div style="text-align:right">
              <input style="border:1px solid black; border-radius: 4px; width:100%" placeholder="댓글을 입력해주세요" type="text" v-model.trim="content">
            
              <v-btn append-icon="mdi-pencil" @click="updateComment">
                수정
              </v-btn>
            </div>
          
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  const VUE_APP_API_URL = process.env.VUE_APP_API_URL

  export default {
    name: 'QnaCommentList',
    props: {
      QnaComment: Object,
      index: Number,
      limit: Number,
    },
    data() {
      return{
        writerId: null,
        updateStatus: false,
        content: null,
        id : null,
        profileImageUrl: null,
      }
    },
    computed: {
      imgSrc() {
        return this.profileImageUrl
      },
    },
    methods: {
      changeStatus(){
        this.updateStatus= !this.updateStatus
      },
      deleteComment() {
        axios({
          method: 'delete',
          url: `${VUE_APP_API_URL}/api/admin/qna/comment/${this.QnaComment.id}/`,
          data:{
            id: this.index
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(() => {
          this.$router.go(0);
          this.$emit('delete-comment')
        })
        .catch((err) => {
          console.log(err)
        })
      },
      updateComment() {
        axios({
          method: 'put',
          url: `${VUE_APP_API_URL}/api/admin/qna/comment/${this.QnaComment.id}/`,
          data: {
            content: this.content,
            id: this.id 
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(() => {
          this.updateStatus = false
          
          this.$emit('update-comment')
          
        })
        .catch((err) => {
          console.log(err)
        })
      },
      convertClientId() {
      let prevId = this.review.clientId;
      let currId = prevId.substr(0, 5) + '*'.repeat(prevId.length - 5);
      return currId;
    }
  },
    
  }
  </script>
  
  <style>
  </style>