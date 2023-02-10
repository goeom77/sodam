<!-- <template>
  <div>
    <div>
      <h1>Detail</h1>
      <p>글 번호 : {{ Boardarticle?.postId }}</p>
      <p>대상 : {{ Boardarticle?.category }}</p>
      <p>제목 : {{ Boardarticle?.title }}</p>
      <p>내용 : {{ Boardarticle?.content }}</p>
      <p>작성시간 : {{ Boardarticle?.createdAt }}</p>
      <p>작성자 : {{ Boardarticle?.clientId }}</p>






      이미지 보임 여기 주석
      <v-img v-for="(item, i) in imagelist" :key="i" :src="require(`../../../assets/worryimage/${item}`)"
       contain height="150px" width="200px" style="border: 2px solid black; margin-left:100px;"/>
      




      
       <button @click="BoardarticleUpdate">수정</button>
      <button @click="BoardarticleDelete">삭제</button>
    </div>
    <div>
      <hr>
    <BoardCommentForm
      :Boardarticle="Boardarticle"
      @get-comment="getBoardarticleDetail"
    />

    <BoardCommentList
      v-for="(BoardComment, index) in Boardarticle?.commentList"
      :key="BoardComment.id"
      :BoardComment="BoardComment"
      :index="index"
      :limit="BoardCommentsCurrentPage"
      @delete-comment="getBoardarticleDetail"
      @update-comment="getBoardarticleDetail"
    />







    댓글 페이지네이션 여기 주석
    <div v-if="BoardComments" style="background-color: #2d3442; display: flex; justify-content: center;">
      <b-pagination id="comments_pagination" style="margin-bottom: 0px;"
        v-model="BoardCommentsCurrentPage"
        :total-rows="BoardComments.length"
        :per-page="10"
      >
      </b-pagination>  
    </div>





    </div>
  </div>

</template>

<script>
import axios from 'axios'
import BoardCommentForm from '../../components/boarditem/BoardCommentForm.vue'
import BoardCommentList from '../../components/boarditem/BoardCommentList.vue'
import {useRouter} from 'vue-router';


const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'BoardDetailView',


  components: {
    BoardCommentForm,
    BoardCommentList,
  },
  data() {
    const postId = this.$route.params.postId

    return {
      postId: postId,
      BoardComments: null,
      Boardarticle: null,
      BoardCommentsCurrentPage: 1,
            
      imagelist: [],     
      imagecnt: 0,      
    }
  },
  created() {
    this.getBoardarticleDetail()
  },
  methods: {
    getBoardarticleDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`,
      })
      
        .then((res) => {
          console.log(this.$route.params.postId)
          this.Boardarticle = res.data




          이미지 카운트
          for(var i = 1; i <= res.data.imagecnt; i++){
             this.imagelist.push(this.$route.params.postId + '-' + i + '.png');}
        
        
        
        
        
        
         })
        .catch((err) => {
          console.log('실패다옹')
          KidBoardarticle.log(err)
        })
    },
    BoardarticleUpdate() {
      this.$router.push({
        name: 'BoardCreateView',
        params: {
          postId: this.$route.params.postId,
        }
      })
    },
    BoardarticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ name: 'AllBoardView' });
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

</style> -->







<template>
	<v-container>
		<v-card elevation="10" outlined width="100%" class="mx-auto">
			<v-card-title>
				<span class="mr-2">Detail</span>
			</v-card-title>
			<v-card-text>
				<v-row>
					<v-col>
						<v-text-field readonly :value="title" />
					</v-col>
				</v-row>
				<v-row>
					<v-col>
						<v-text-field readonly dense :value="clientId" />
					</v-col>
					<v-col>
						<v-text-field
							readonly
							dense
							:value="createdAt"
						/>
					</v-col>
					<v-col v-if="this.common_code==='1' && this.gender">
						<v-text-field readonly dense :value="gender"/>
					</v-col>
          <v-col v-if="this.common_code==='1' && this.age">
						<v-text-field readonly dense :value="age" />
					</v-col>
				</v-row>
				<v-row>
					<v-col>
						<v-text-field readonly dense :value="content" />
					</v-col>
				</v-row>

			</v-card-text>
			<v-card-actions>
				<v-spacer></v-spacer>
        <button @click="BoardarticleUpdate" style="width: 70px; border-radius: 20px; background-color: green;">수정</button>
        <button @click="BoardarticleDelete"  style="width: 70px; border-radius: 20px; background-color: red; margin-left:10px">삭제</button>
        <button @click="BoardarticleBack"  style="width: 70px; border-radius: 20px; background-color: grey; margin-left:10px; margin-right:10px">취소</button>
			</v-card-actions>
      <br>
      <br>
      <BoardCommentForm
      style="margin-left:0%"
      :Boardarticle="Boardarticle"
      @get-comment="getBoardarticleDetail"
    />

    <BoardCommentList
      v-for="(BoardComment, index) in Boardarticle?.commentList"
      :key="BoardComment.postId"
      :BoardComment="BoardComment"
      :index="index"
      :limit="BoardCommentsCurrentPage"
      @delete-comment="getBoardarticleDetail"
      @update-comment="getBoardarticleDetail"
    />
		</v-card>
	</v-container>
</template>

<script>
import axios from 'axios'
import BoardCommentForm from '../../components/boarditem/BoardCommentForm.vue'
import BoardCommentList from '../../components/boarditem/BoardCommentList.vue'
import {useRouter} from 'vue-router';

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'BoardDetailView',
	components: {
		BoardCommentForm,
    BoardCommentList

	},
	data() {

    const postId = this.$route.params.postId

		return {
			title: '',
			clientId: '',
			createdAt: '',
			views: 0,
			commentCount: 0,
			BoardComments: null,
			comment: '',
      jender: '',
      age: '',
      postId: postId,
      Boardarticle: null,



      BoardCommentsCurrentPage: 1,
      common_code: this.$store.state.payload.common_code,
            
      imagelist: [],     
      imagecnt: 0,     
		}
	},
	created() {
		this.getBoardarticleDetail()
    this.getUserDetail()
	},
	methods: {
    getUserDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/client/${this.clientId}`,
      })
      
        .then((res) => {
          console.log(this.$route.params.postId)
          this.jender= res.data.jender
          this.age = res.data.age
        })
    },

    getBoardarticleDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`,
      })
      
        .then((res) => {
          console.log(this.$route.params.postId)
          this.Boardarticle = res.data
          this.title= res.data.title
          this.clientId= res.data.clientId
          this.createdAt= res.data.createdAt
          this.views= res.data.views
          this.commentCount= res.data.commentCount
          this.BoardComments= res.data.commentList
          this.content= res.data.content
          this.postId= res.data.postId
          this.gender= res.data.gender
          this.age = res.data.age
        })
    },
    BoardarticleUpdate() {
      this.$router.push({
        name: 'BoardCreateView',
        params: {
          postId: this.$route.params.postId,
        }
      })
    },
    BoardarticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ name: 'BoardView' });
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    },
    BoardarticleBack() {
      this.$router.push({
        name: 'BoardView',
      })
    },

	},
}
</script>

<style></style>