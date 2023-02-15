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
      gender: '',
      age: '',
      postId: postId,
      Boardarticle: null,
      content: '',



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
          this.gender= res.data.gender
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