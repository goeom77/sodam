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
              {{ this.NoticeData.title }}
            </div>
            <div class="col-2" style="text-align: right; margin:auto;">
              {{ this.NoticeData.createdAt.split('T')[0] }}
            </div>
          </div>
          <div class="row noticecontent" style="margin:30px; font-size:20px">
            {{ this.NoticeData.content }}
          </div>
          <div style="text-align: right; margin-right:30px" v-if="(this.$store.state.payload.id=='admin')">
            <v-btn outlined rounded text @click="BoardarticleUpdate" color="blue">수정</v-btn>
            <v-btn outlined rounded text @click="BoardarticleDelete" color="red">삭제</v-btn>
          </div>
          <div style="text-align: center;">
              <v-btn variant="outlined" @click="BoardarticleBack">목록으로</v-btn>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import {useRouter} from 'vue-router';

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'BoardDetailView',
	data() {
		return {
      NoticeData:null,
      BoardCommentsCurrentPage: 1,    
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
        this.NoticeData = res.data
      })
    },
    BoardarticleUpdate() {
      this.$router.push({ name: 'NoticeCreateView', params: {id: this.NoticeData.id }  })
    },
    BoardarticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/admin/notice/${this.$route.params.id}`,
        data:{
          id: this.NoticeData.id
        },
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
        name: 'HelpView'
      })
    },

	},
}
</script>

<style>
.noticetitle{
  border-top: 1px solid black;
  padding:20px;
  text-align: left;
  margin:auto;
}
.noticecontent{
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  padding:20px;
}
</style>