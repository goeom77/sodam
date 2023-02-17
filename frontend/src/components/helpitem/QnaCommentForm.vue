
<template>
  <div >
    <form @submit.prevent="createInquiryComment" style="border-radius:0px; height:50px;  display:flex; justify-content: space-between; width: 100px;">
      <div style="text-align: center;">
      <input style="width:742px; " placeholder="댓글을 입력해주세요" aria-label="Please input the title" aria-describedby="basic-addon1" type="text" id="title"  v-model.trim="inputData">
    </div>
    
    <div style="text-align: center; margin:auto">
      <v-btn append-icon="mdi-arrow-left-bottom" @click="createInquiryComment">확인</v-btn>
    </div>
    
    </form>
  </div>
</template>

<script>
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL


export default {
    name: 'InquiryCommentForm',
    props: {
      Qnaarticle: Object,
    },
    data() {
      return {
        inputData: null,
      }
    },
    methods: {
      createInquiryComment() {
        const content = this.inputData
        if (!content) {
            alert('댓글을 입력해주세요')
            return
        }
        axios({
            method: 'post',
            url: `${VUE_APP_API_URL}/api/admin/qna/comment/writing`,
            data: {
              content: content,
              id: this.Qnaarticle.id,
              writeId: this.$store.state.payload.id
            },
            headers: {
              Authorization : `Bearer ${this.$store.state.token.token.access_token}`
            }
        })
        .then((res) => {
          console.log(res)
            this.$emit('get-comment')
            this.inputData=null
        })
        .catch((err) => {
            console.log(err)
        })
    }
  }
}
</script>

<style>

</style>