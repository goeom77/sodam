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
	</v-container>
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