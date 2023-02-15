<template>
  <!-- <div></div> -->
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
						<v-text-field readonly dense label="관리자" />
					</v-col>
					<v-col>
						<v-text-field
							readonly
							dense
							:value="createdAt"
						/>
					</v-col>
					<v-col>
						<v-text-field readonly dense :value="views" />
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
		</v-card>
	</v-container>
</template>

<script>
import axios from 'axios'
import {useRouter} from 'vue-router';

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'BoardDetailView',
	components: {


	},
	data() {

    const id = this.$route.params.id

		return {
			title: '',
			adminId: '',
			createdAt: '',
			views: 0,
			comment: '',
      postId: postId,
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
        url: `${VUE_APP_API_URL}/api/help-desk/notice/${this.$route.params.id}`,
      })
      
        .then((res) => {
          console.log(this.$route.params.id)
          this.Boardarticle = res.data
          this.title= res.data.title
          this.adminId= res.data.adminId
          this.createdAt= res.data.createdAt
          this.views= res.data.views
          this.content= res.data.content
          this.id= res.data.id
        })
    },
    BoardarticleUpdate() {
      this.$router.push({
        name: 'BoardCreateView',
        params: {
          id: this.$route.params.id,
        }
      })
    },
    BoardarticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/admin/${this.$route.params.id}`,
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
    BoardarticleBack() {
      this.$router.push({
        name: 'HelpView',
      })
    },

	},
}
</script>

<style></style>