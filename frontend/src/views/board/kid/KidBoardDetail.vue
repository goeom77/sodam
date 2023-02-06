<template>
  <div>
    <div>
      <h1>Detail</h1>
      <p>글 번호 : {{ KidBoardarticle?.postId }}</p>
      <p>대상 : {{ KidBoardarticle?.category }}</p>
      <p>제목 : {{ KidBoardarticle?.title }}</p>
      <p>내용 : {{ KidBoardarticle?.content }}</p>
      <p>작성시간 : {{ KidBoardarticle?.createdAt }}</p>
      <p>작성자 : {{ KidBoardarticle?.clientId }}</p>

      <!-- 이미지 보임
      <v-img v-for="(item, i) in imagelist" :key="i" :src="require(`../../../assets/worryimage/${item}`)"
       contain height="150px" width="200px" style="border: 2px solid black; margin-left:100px;"/>
       -->
      
       <button @click="KidBoardarticleUpdate">수정</button>
      <button @click="KidBoardarticleDelete">삭제</button>
    </div>
    <div>
      <hr>
    <KidBoardCommentForm
      :KidBoardarticle="KidBoardarticle"
    />
    <!-- <KidBoardCommentForm
      :KidBoardarticle="KidBoardarticle"
      @get-KidBoardComments="getKidBoardComments"
    /> -->
    <KidBoardCommentList
      v-for="(KidBoardComment, index) in KidBoardarticle?.commentList"
      :key="KidBoardComment.id"
      :KidBoardComment="KidBoardComment"
      :index="index"
      :limit="KidBoardCommentsCurrentPage"
    />
      <!-- @delete-comment="getKidBoardComments"
      @update-comment="getKidBoardComments" -->




    <!-- <div v-if="KidBoardComments" style="background-color: #2d3442; display: flex; justify-content: center;">
      <b-pagination id="comments_pagination" style="margin-bottom: 0px;"
        v-model="KidBoardCommentsCurrentPage"
        :total-rows="KidBoardComments.length"
        :per-page="10"
      >
      </b-pagination>  
    </div> -->

    </div>
  </div>

</template>

<script>
// @ is an alias to /srcz
import axios from 'axios'
import KidBoardCommentForm from '../../../components/boarditem/KidBoardCommentForm.vue'
import KidBoardCommentList from '../../../components/boarditem/KidBoardCommentList.vue'
import {useRouter} from 'vue-router';


const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'KidBoardDetail',


  components: {
    KidBoardCommentForm,
    KidBoardCommentList,
  },
  data() {
    const postId = this.$route.params.postId

    return {
      postId: postId,
      KidBoardComments: null,
      KidBoardarticle: null,
      KidBoardCommentsCurrentPage: 1,
            // data속성에서 추가되는 변수들
      imagelist: [],        // 불러온 이미지들의 url을 저장하는 객체
      imagecnt: 0,        // 불러올 이미지 개수 (db에서 받아옴)
    }
  },
  created() {
    this.getKidBoardarticleDetail()
  },
  methods: {
    getKidBoardarticleDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`,

      })
        .then((res) => {
          console.log(this.$route.params.postId)
          this.KidBoardarticle = res.data

          // 이미지 카운트
          // for(var i = 1; i <= res.data.imagecnt; i++){
          //    this.imagelist.push(this.$route.params.postId + '-' + i + '.png');}
        })
        .catch((err) => {
          console.log('실패다옹')
          KidBoardarticle.log(err)
        })
    },
    KidBoardarticleUpdate() {
      this.$router.push({
        name: 'KidBoardCreate',
        params: {
          postId: this.$route.params.postId,
        }
      })
    },

 

    // KidBoardarticleDelete() {
    //   const category = this.category
    //   if (!confirm("삭제하시겠습니까?")) {
    //     axios({
    //     method: 'delete',
    //     url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`
    //     })
		// 		.then((res)=>{
    //       console.log('됨')
		// 			if(res.data.result) {
		// 				alert("삭제되었습니다.");
		// 				this.$router.push({ name: category });
		// 			} else {
		// 				alert("실행중 실패했습니다.\n다시 이용해 주세요.");
    //         this.$router.push({ name: category });
		// 			}
		// 		})
		// 		.catch((err)=>{
    //       console.log('안됨')
		// 			console.log(err);
		// 		})
		// 	}
    // },


    KidBoardarticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ name: this.KidBoardarticle?.category });
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    },


    deleteReview() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/capi/trouble/${this.review?.id}/`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        this.$emit('delete-review')
      })
      .catch((err) => {
        console.log(err)
      })
    },
    getReviewUpdate() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/community/reviews/detail/${this.review?.id}/`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then((res) => {
        this.title = res.data.title
        this.content = res.data.content
        this.rank = res.data.rank
        this.updateStatus = true
      })
      .catch((err) => {
        console.log(err)
      })
    },
    setRating(rating) {
        this.rank = rating
    },
    updateReview() {
      axios({
        method: 'put',
        url: `${VUE_APP_API_URL}/community/reviews/detail/${this.review?.id}/`,
        data: {
          title: this.title,
          content: this.content,
          rank: this.rank,
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        this.$emit('update-review')
        this.updateStatus = false
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