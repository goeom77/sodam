<template>
  <div id="fh5co-main">
    <div class="fh5co-narrow-content">
      <v-toolbar
      class="BoardTool"
        color="#FCFAEF"
        dark
        tabs
        style="text-align: start;">
      </v-toolbar>
      <div class="container" style="border-bottom:1px solid #ccc;">
        <div class="row" style="border-top:1px solid black; margin-top:20px; padding-top:20px">
          <div class="col-10" style="margin:auto; font-size:25px; font-weight: 800;">
            [{{ convertConsultType(this.Boardarticle.category) }}] {{ this.Boardarticle.title }}
          </div>
          <div class="col-1" style="text-align:right; margin:auto;">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
              <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
              <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
            </svg>
          </div>
          <div class="col-1" style="text-align:left; margin:auto;">
            {{ this.Boardarticle.views }}
          </div>
        </div>
        <br>
        <div class="row" style="border-bottom:1px solid #ccc; padding-bottom:20px;">
          <div class="col-1" style="text-align: center; margin:auto;">
            {{ this.Boardarticle.clientId }}
          </div>
          <div class="col-1" style="text-align: center; margin:auto;">
            {{ this.Boardarticle.gender }}
          </div>
          <div class="col-1" style="text-align: center; margin:auto;">
            {{ this.Boardarticle.age }}
          </div>
          <div class="col-7">
          </div>
          <div class="col-2" style="text-align: center; margin:auto;">
            {{ this.Boardarticle.createdAt.split(' ')[0] }}
          </div>
        </div>
        <div style="margin:30px;">
          {{ this.Boardarticle.content }}
        </div>

        <div style="text-align: right; margin-right:30px; margin-bottom:20px">
          <v-btn append-icon="mdi-pencil" @click="BoardarticleUpdate">수정</v-btn>&nbsp;
          <v-btn append-icon="mdi-delete" color="red" @click="BoardarticleDelete">삭제</v-btn>
        </div>
      </div>

      <div class="container" style="padding:30px">
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
          
      </div>
      <div style="text-align: center;">
              <v-btn variant="outlined" @click="BoardarticleBack">목록으로</v-btn>
          </div>
    </div>  
  </div>


  
	<!-- <v-container>
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
	</v-container> -->
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
      Boardarticle: null,
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
    convertConsultType(target) {
      const selectTypeList = [
        {name:'#아동', value:"child"},
        {name:'#청소년', value:"teenager"},
        {name:'#성인', value:"adult"},
        {name:'#부부', value:"couple"},
        {name:'#노년', value:"elder"},
      ];
      
      let result = "";
      selectTypeList.forEach( type => {
          if(type.value === target) {
              result += type.name + " ";
              return;
          }
      })
      return result;
    },
	},
}
</script>

<style></style>