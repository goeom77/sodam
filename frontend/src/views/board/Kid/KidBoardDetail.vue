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
    <KidBoardCommentForm
      :KidBoardarticle="KidBoardarticle"
      @get-KidBoardComments="getKidBoardComments"
    />
    <KidBoardCommentList
      v-for="(KidBoardComment, index) in KidBoardComments"
      :key="KidBoardComment.id"
      :KidBoardComment="KidBoardComment"
      :index="index"
      :limit="KidBoardCommentsCurrentPage"
      @delete-comment="getKidBoardComments"
      @update-comment="getKidBoardComments"
    />
    <div v-if="KidBoardComments" style="background-color: #2d3442; display: flex; justify-content: center;">
      <b-pagination id="comments_pagination" style="margin-bottom: 0px;"
        v-model="KidBoardCommentsCurrentPage"
        :total-rows="KidBoardComments.length"
        :per-page="10"
      >
      </b-pagination>  
    </div>

    </div>
  </div>

</template>

<script>
// @ is an alias to /srcz
import axios from 'axios'
import KidBoardCommentForm from '../../../components/boarditem/KidBoardCommentForm.vue'
import KidBoardCommentList from '../../../components/boarditem/KidBoardCommentList.vue'

const API_URL = 'http://127.0.0.1:8000'

export default {
  name: 'KidBoardDetail',
  components: {
    KidBoardCommentForm,
    KidBoardCommentList,
  },
  data() {
    return {
      KidBoardComments: null,
      KidBoardarticle: null,
      KidBoardCommentsCurrentPage: 1,
      person: KidBoardarticle?.person,
    }
  },
  // props: {
  //   KidBoardarticle: 
  // },
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
    getKidBoardComments() {
      axios({
        method:'get',
        url: `${API_URL}/backend/${this.KidBoardCommentForm?.id}/`,
        headers: { 
            Authorization: `Token ${this.$store.state.token}`
        }
      })
      .then((res) => {
        this.KidBoardComments = res.data
      })
      .catch(() => {
        this.KidBoardComments = null,
        console.log('댓글이 없습니다.')
      })
    },
  }
}

</script>


<style>

</style>