<template>
  <div>
    <div>
      <h1>Detail</h1>
      <p>글 번호 : {{ Announcearticle?.id }}</p>
      <p>제목 : {{ Announcearticle?.title }}</p>
      <p>내용 : {{ Announcearticle?.content }}</p>
      <p>작성시간 : {{ Announcearticle?.createdAt }}</p>
      <p>작성자 : {{ Announcearticle?.userType }}</p>

      <!-- 이미지 보임
      <v-img v-for="(item, i) in imagelist" :key="i" :src="require(`../../../assets/worryimage/${item}`)"
       contain height="150px" width="200px" style="border: 2px solid black; margin-left:100px;"/>
       -->
      
       <button @click="AnnouncearticleUpdate">수정</button>
      <button @click="AnnouncearticleDelete">삭제</button>
    </div>
    <div>
      <hr>
    <AnnounceCommentForm
      :Announcearticle="Announcearticle"
    />
    </div>
  </div>

</template>

<script>
// @ is an alias to /srcz
import axios from 'axios'
import {useRouter} from 'vue-router';


const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'AnnounceDetail',


  components: {

  },
  data() {
    const id = this.$route.params.id

    return {
      id: id,
      Announcearticle: null,
            // data속성에서 추가되는 변수들
      imagelist: [],        // 불러온 이미지들의 url을 저장하는 객체
      imagecnt: 0,        // 불러올 이미지 개수 (db에서 받아옴)
    }
  },
  created() {
    this.getAnnouncedarticleDetail()
  },
  methods: {
    getAnnouncedarticleDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/notice/${this.$route.params.id}`
      })
        .then((res) => {
          console.log(this.$route.params.id)
          this.Announcedarticle = res.data

          // 이미지 카운트
          // for(var i = 1; i <= res.data.imagecnt; i++){
          //    this.imagelist.push(this.$route.params.postId + '-' + i + '.png');}
        })
        .catch((err) => {
          console.log('실패다옹')
          Announcedarticle.log(err)
        })
    },
    AnnouncedarticleUpdate() {
      this.$router.push({
        name: 'AnnounceCreate',
        params: {
          postId: this.$route.params.id,
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


    AnnouncearticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/admin/notice/${this.$route.params.id}`,
        headers: { 
            Authorization: `Token ${this.$store.state.token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ name: 'announce' });
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