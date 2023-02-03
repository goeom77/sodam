<template>
  <div >
    <div>{{KidBoardarticle}}</div>
    <form @submit.prevent="createKidBoardComment" style="border-radius:0px; height:50px;  display:flex; justify-content: space-between; width: 100px;">
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

const API_URL = "http://127.0.0.1:8080"


export default {
    name: 'KidBoardCommentForm',
    props: {
        KidBoardarticle: Object,
    },
    data() {
        return {
            inputData: null,
        }
    },
    methods: {
        createKidBoardComment() {
        const content = this.inputData
        // const boardId = this.KidBoardarticle?.postId
        // const counselorId = this.KidBoardarticle?.clientId
        if (!content) {
            alert('댓글을 입력해주세요')
            return
        }
        axios({
            method: 'post',
            // url: `${API_URL}/api/trouble/comment/${this.KidBoardarticle.id}/`,
            url: `${API_URL}/api/trouble/comment`,
            data: {
                content: content,
                boardId: this.KidBoardarticle?.postId,
                counselorId: 'counselor2'
            },
            headers: {
            Authorization: `Token ${this.$store.state.token}`
        }
        })
        .then(() => {
            console.log('되냐?')
            // this.$emit('get-comment')
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