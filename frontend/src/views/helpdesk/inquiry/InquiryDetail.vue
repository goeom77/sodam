<template>
  <div>
    <div>
      <h1>Detail</h1>
      <p>글 번호 : {{ Inquiryarticle?.id }}</p>
      <p>제목 : {{ Inquiryarticle?.title }}</p>
      <p>내용 : {{ Inquiryarticle?.content }}</p>
      <p>작성시간 : {{ Inquiryarticle?.createdAt }}</p>
      <p>작성자 : {{ Inquiryarticle?.clientId }}</p>

      <!-- 이미지 보임
      <v-img v-for="(item, i) in imagelist" :key="i" :src="require(`../../../assets/worryimage/${item}`)"
       contain height="150px" width="200px" style="border: 2px solid black; margin-left:100px;"/>
       -->
      
       <button @click="InquiryarticleUpdate">수정</button>
      <button @click="InquiryarticleDelete">삭제</button>
    </div>
    <div>
      <hr>
    <InquiryCommentForm
      :Inquiryarticle="Inquiryarticle"
    />
    <!-- <KidBoardCommentForm
      :KidBoardarticle="KidBoardarticle"
      @get-KidBoardComments="getKidBoardComments"
    /> -->
    <InquiryCommentList
      v-for="(InquiryComment, index) in Inquiryarticle?.commentList"
      :key="InquiryComment.id"
      :InquiryComment="InquiryComment"
      :index="index"
      :limit="InquiryCommentsCurrentPage"
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
import InquiryCommentForm from '../../../components/helpitem/InquiryCommentForm.vue'
import InquiryCommentList from '../../../components/helpitem/InquiryCommentList.vue'
import {useRouter} from 'vue-router';


const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'InquiryDetail',


  components: {
    InquiryCommentForm,
    InquiryCommentList,
  },
  data() {
    const id = this.$route.params.id

    return {
      id: id,
      InquiryComments: null,
      Inquiryarticle: null,
      InquiryCommentsCurrentPage: 1,
            // data속성에서 추가되는 변수들
      imagelist: [],        // 불러온 이미지들의 url을 저장하는 객체
      imagecnt: 0,        // 불러올 이미지 개수 (db에서 받아옴)
    }
  },
  created() {
    this.getInquiryarticleDetail()
  },
  methods: {
    getInquiryarticleDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/qna/${this.$route.params.id}`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
        .then((res) => {
          console.log(this.$route.params.id)
          this.Inquiryarticle = res.data

          // 이미지 카운트
          // for(var i = 1; i <= res.data.imagecnt; i++){
          //    this.imagelist.push(this.$route.params.postId + '-' + i + '.png');}
        })
        .catch((err) => {
          console.log('실패다옹')
          Inquiryarticle.log(err)
        })
    },
    InquiryarticleUpdate() {
      this.$router.push({
        name: 'InquiryCreate',
        params: {
          id: this.$route.params.id,
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


    InquiryarticleeDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/help-desk/qna/${this.$route.params.id}`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ name: 'Inquiry' });
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    },
  
  }
}

</script>


<style>

</style>