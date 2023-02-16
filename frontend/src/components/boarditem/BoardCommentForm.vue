<template>
  <div >
    <!-- <div>{{Boardarticle}}</div> -->
    <form @submit.prevent="createBoardComment" style="border-radius:0px; height:50px;  display:flex; justify-content: space-between; width: 100px;">
      <div>
      <input class="form-control" style="width:742px; " placeholder="댓글을 입력해주세요" aria-label="Please input the title" aria-describedby="basic-addon1" type="text" id="title"  v-model.trim="inputData">
    </div>
    
    <div>
      <input class="btn btn-danger" type="submit">
    </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL


export default {
    name: 'BoardCommentForm',
    props: {
        Boardarticle: Object,
        BoardComment: Object,
    },
    data() {
        return {
            inputData: null,
        }

    },
    // created() {
    //   this.getBoardCommentDetail()
    // },
    methods: {
      // getBoardarticleDetail() {
      //   axios({
      //     method: 'get',
      //     url: `${VUE_APP_API_URL}/api/trouble/${BoardComment.commentId}`
      //     // url: `${VUE_APP_API_URL}/trouble/${postId}`
      //   })
      //     .then((res) => {
      //       // console.log(res)
      //       console.log('성공이다람쥐')
      //       this.inputData = res.data.content
      //     })
      //     .catch((err) => {
      //       console.log('실패이다람쥐')
      //       Boardarticle.log(err)
      //     })
      // },





      createBoardComment() {
        const content = this.inputData
        const boardId = this.Boardarticle?.postId
        const counselorId = this.Boardarticle?.clientId
        if (!content) {
            alert('댓글을 입력해주세요')
            return
        }
        axios({
            method: 'post',
            // url: `${VUE_APP_API_URL}/api/trouble/comment/${this.Boardarticle.id}/`,
            url: `${VUE_APP_API_URL}/api/trouble/comment`,
            data: {
                content: content,
                boardId: this.Boardarticle?.postId,
                counselorId: this.$store.state.payload.id,
                
            },
            headers: {
              Authorization : `Bearer ${this.$store.state.token.token.access_token}`
            }
        })
        .then(() => {
            console.log('되냐?')
            this.$emit('get-comment')
            this.inputData=null
        })
        .catch((err) => {
            console.log('안되냐?')
            console.log(err)
        })
    }
  }
}
</script>

<style>

</style>