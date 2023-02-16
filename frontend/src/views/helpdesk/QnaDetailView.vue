<template>
  <div id="fh5co-main">
    <div class="fh5co-narrow-content-Help">
      <v-toolbar
      class="helpTool"
      color="white"
      dark
      tabs>
      </v-toolbar>
      <!-- 배경 End -->
      <div class="container">
          <div class="row noticetitle" >
            <div class="col-10" style="font-size:50px; font-weight: bolder;">
              {{ this.Qnaarticle.title }}
            </div>
            <div class="col-2" style="text-align: right; margin:auto;">
              {{ this.Qnaarticle.createdAt.split(' ')[0] }}
            </div>
          </div>
          <div class="row noticecontent" style="margin:30px; font-size:20px">
            {{ this.Qnaarticle.content }}
          </div>
          <div style="text-align: right; margin-right:30px" v-if="(this.$store.state.payload.id==`${writerId}`)">
            <v-btn outlined rounded text @click="QnaarticleUpdate" color="blue">수정</v-btn>
            <v-btn outlined rounded text @click="QnaarticleDelete" color="red">삭제</v-btn>
          </div>
          <div style="text-align: center; padding-bottom:20px">
              <v-btn variant="outlined" @click="QnaarticleBack">목록으로</v-btn>
          </div>



          <div style="margin-top:30px; margin-left:30px; font-size:20px">
            총 {{ Qnaarticle.comments.length }}개의 댓글이 있습니다.
          </div>
          <QnaCommentList
          style="margin-left:30px"
            v-for="(QnaComment, index) in Qnaarticle.comments"
            :key="QnaComment.id"
            :QnaComment="QnaComment"
            :index="index"
            :limit="QnaCommentsCurrentPage"
            @delete-comment="getQnaarticleDetail"
            @update-comment="getQnaarticleDetail"
          />
          <QnaCommentForm
            style="margin-left:0%; border-top:1px solid #ccc; padding-top:20px;"
            :Qnaarticle="Qnaarticle"
            @get-comment="getQnaarticleDetail"
          />
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
						<v-text-field readonly dense :value="writerId" />
					</v-col>
					<v-col>
						<v-text-field
							readonly
							dense
							:value="createdAt"
						/>
					</v-col>
					<v-col>
						<v-text-field readonly dense :value="commentCount" />
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
        <button @click="QnaarticleUpdate" style="width: 70px; border-radius: 20px; background-color: green;">수정</button>
        <button @click="QnaarticleDelete"  style="width: 70px; border-radius: 20px; background-color: red; margin-left:10px">삭제</button>
        <button @click="QnaarticleBack"  style="width: 70px; border-radius: 20px; background-color: grey; margin-left:10px; margin-right:10px">취소</button>
			</v-card-actions>
      <br>
      <br>
      <QnaCommentForm
      style="margin-left:0%"
      :Qnaarticle="Qnaarticle"
      @get-comment="getQnaarticleDetail"
    />

    <QnaCommentList
      v-for="(QnaComment, index) in Qnaarticle?.commentList"
      :key="QnaComment.id"
      :QnaComment="QnaComment"
      :index="index"
      :limit="QnaCommentsCurrentPage"
      @delete-comment="getQnaarticleDetail"
      @update-comment="getQnaarticleDetail"
    />
		</v-card>
	</v-container> -->
</template>

<script>
import axios from 'axios'
import QnaCommentForm from '../../components/helpitem/QnaCommentForm.vue'
import QnaCommentList from '../../components/helpitem/QnaCommentList.vue'
import {useRouter} from 'vue-router';

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'QnaDetailView',
	components: {
		QnaCommentForm,
    QnaCommentList
	},
	data() {

    const id = this.$route.params.id

		return {
			title: '',
			writerId: '',
			createdAt: '',
			commentCount: 0,
			QnaComments: null,
			comment: '',
      id: id,
      Qnaarticle: null,
      QnaCommentsCurrentPage: 1,
            
      imagelist: [],     
      imagecnt: 0,     
		}
	},
	created() {
		this.getQnaarticleDetail()
	},
  watch:{
    Qnaarticle: function(value){
      console.log('qweqwe')
    }
  },
	methods: {
    getQnaarticleDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/help-desk/qna/${this.$route.params.id}`,
      })
      
        .then((res) => {
          console.log(this.$route.params.id)
          this.Qnaarticle = res.data
          this.title= res.data.title
          this.writerId= res.data.writerId
          this.createdAt= res.data.createdAt
          this.commentCount= res.data.commentCount
          this.QnaComments= res.data.commentList
          this.content= res.data.content
          this.id= res.data.id
        })
    },
    QnaarticleUpdate() {
      this.$router.push({
        name: 'QnaCreateView',
        params: {
          id: this.$route.params.id,
        }
      })
    },
    QnaarticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/help-desk/qna/${this.$route.params.id}`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ name: 'HelpView' });
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    },
    QnaarticleBack() {
      this.$router.push({
        name: 'HelpView',
      })
    },

	},
}
</script>

<style></style>