<template>
  <div>
    <div>
      <h1>Detail</h1>
      <p>글 번호 : {{ KidBoardarticle?.id }}</p>
      <p>대상 : {{ KidBoardarticle?.person }}</p>
      <p>제목 : {{ KidBoardarticle?.title }}</p>
      <p>내용 : {{ KidBoardarticle?.content }}</p>
      <p>작성시간 : {{ KidBoardarticle?.created_at }}</p>
      <p>수정시간 : {{ KidBoardarticle?.updated_at }}</p>
      <button @click="KidBoardarticleUpdate">수정</button>
      <button @click="KidBoardarticleDelete">삭제</button>
    </div>
    <div>
      <hr>
      <div>seyoung</div>
      <b-textarea
        placeholder="댓글을 입력하세요"
        v-model="Commentcontext"
      ></b-textarea>
      <b-button @click="createComment">댓글달기</b-button>
    </div>
  </div>

</template>

<script>
// @ is an alias to /srcz
import axios from 'axios'

const API_URL = 'http://127.0.0.1:8000'

export default {
  name: 'KidBoardDetail',
  data() {
    return {
      KidBoardarticle: null,
      // Commentcontext: null,
      person: KidBoardarticle?.person,
    }
  },
  created() {
    this.getKidBoardarticleDetail()
  },
  methods: {
    getKidBoardarticleDetail() {
      axios({
        method: 'get',
        url: `${API_URL}/backend/${this.$route.params.id}`
      })
        .then((res) => {
          console.log(res)
          this.KidBoardarticle = res.data
        })
        .catch((err) => {
          KidBoardarticle.log(err)
        })
    },
    KidBoardarticleUpdate() {
      this.$router.push({
        name: 'KidBoardCreate',
        params: {
            contentId: this.id
        }
      })
    },
    KidBoardarticleDelete() {
      const person = this.person
      if (!confirm("삭제하시겠습니까?")) {
        axios({
        method: 'delete',
        url: `${API_URL}/backend/${this.$route.params.id}`
        })
				.then((res)=>{
					if(res.data.result) {
						alert("삭제되었습니다.");
						this.$router.push({ name: {person} });
					} else {
						alert("실행중 실패했습니다.\n다시 이용해 주세요.");
					}
				})
				.catch((err)=>{
					console.log(err);
				})
			}
    },
    // createComment(){
    //   data.Comment.push(
    //       {
    //         comment_id: data.Comment[data.Comment.length - 1].comment_id + 1,
    //         user_id: 1,
    //         content_id: this.contentId,
    //         context: this.context,
    //         created_at: '2019-03-29 14:11:11',
    //         updated_at: null
    //       }
    //   )
    //   this.reload();
    //   console.log(data.Comment)
    // }
  }
}

</script>


<style>

</style>