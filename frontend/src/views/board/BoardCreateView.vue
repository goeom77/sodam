
<template>
     <div id="fh5co-main" >
      <div class="fh5co-narrow-content">
      <div id="BoardCreateBoardtitle">
        <h1>고민 게시판</h1>
      </div>

    </div>
    <div>
      <div id="Writebox">
        <form @submit.prevent="BoardCreateArticle">
        <!-- <form> -->
          <div style="text-align:start; padding: 10px;">
            <label for="category" style="float:left;">대상</label>
            <select style="width: 85%;" id="worryselect" v-model="category"  >
              <option 
                v-for="(item, index) in selectList"
                :key="index"
                :value="item.value"
                >{{ item.name }}</option
              >
            </select>
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6;">
            <label for="title">제목</label>
            <input style="width: 85%;" type="text" id="title" v-model.trim="title">
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6; border-bottom: 1px solid #B9B6B6;">
            <label for="content">내용</label>
            <textarea style="width: 85%;" id="content" v-model="content"></textarea>
          </div>
          <router-link to="/BoardView" style="text-decoration: none; color: black;">취소</router-link>
          <v-btn v-if="check" @click="BoardarticleUpdate">수정</v-btn>
          <input v-else type="submit" id="submityes" value="등록">
        </form>
        
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL


export default {
  name: 'BoardCreateView',

  data() {
    return {
      uploadimageurl: [],    // 업로드한 이미지의 미리보기 기능을 위해 url 저장하는 객체
      imagecnt: 0,           // 업로드한 이미지 개수 => 제출버튼 클릭시 back서버와 axios 통신하게 되는데, 이 때 이 값도 넘겨줌
      postId: this.$route.params.postId,
      category : null,
      title: null,
      content: null,
      check: '',
      clientId: this.$store.state.payload.id,
      

      selectList: [
        { name: "아동", value: "child" },
        { name: "청소년", value: "teenager" },
        { name: "성인", value: "adult" },
        { name: "부부", value: "couple" },
        { name: "노년", value: "elder" },
        { name: "기타", value: "other" },
      ],
    }
  },

  created() {
    this.BoardArticleContent()
  },

  methods: {
    BoardArticleContent() {
      const postId  = this.postId 
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`,
        // url: `${VUE_APP_API_URL}/trouble/${postId}`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
        .then((res) => {
          // console.log(res)
          console.log('됐음 멍')
          this.category = res.data.category
          this.title = res.data.title
          this.content = res.data.content
          this.check = true
        })
        .catch(() => {
          console.log('안됐음 멍')
        })
    },




    BoardCreateArticle() {
      const category  = this.category 
      const title = this.title
      const content = this.content
      const clientId = this.clientId

      if (!category ) {
        alert('대상을 선택해주세요')
        return
      } else if (!title) {
        alert('제목을 입력해주세요')
        return
      } else if (!content) {
        alert('내용을 입력해주세요')
        return
      }

        axios({
          method: 'post',
          url: `${VUE_APP_API_URL}/api/trouble/writing`,
          data: {
            category : category ,
            title: title,
            content: content,
            clientId: clientId,
            // imagecnt: this.imagecnt
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
          .then((res) => {
            console.log('여긴 안에러')
            this.$router.push({ 
              name: 'BoardView'  })
          })
          .catch((err) => {
            console.log('여긴 에러')
            console.log(err)
          })
    },
    backTo(){
        this.$router.go(-1);
      },

    BoardarticleUpdate() {
      const category  = this.category 
      const title = this.title
      const content = this.content

      axios({
        method: 'put',
        url: `${VUE_APP_API_URL}/api/trouble/${this.$route.params.postId}`,
        data: {
          title: title,
          content: content,
          category: category,
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ 
          name: 'BoardDetailView', 
          params: { postId: this.$route.params.postId } })


          
        // this.updateStatus = false
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    }
  }
}
</script>



<style>
#BoardCreate {
  margin-left: 200px;
  width: 85%;
  /* margin: 0 auto; */
}

a {
  text-decoration: none;
  color: white;
}

#BoardCreateBoard {
  /* background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('@/assets/images/hand.png');
  background-color: aliceblue;
  background-repeat: no-repeat;
  background-size: 100% 100%; */
  text-align: center;
  font-size: large;
  font-weight: 100;
  padding-top: 20px;
  height: 250px;
  position: relative;
}

#BoardCreateBoardtitle {
  position: absolute;
  left: 50%; 
  bottom: 50%; 
  transform: translate(-50%);
}

#Writebox {
  border-top: 1px solid black;
  /* border-bottom: 1px solid black; */
  margin: 60px;
}

#worryselect {
width: 980px; 
height: 50px;
padding: .8em .5em; 
border: 1px solid #B9B6B6;
font-family: inherit;  
/* background: url('arrow.jpg') no-repeat 95% 50%;  */
border-radius: 0px; 
-webkit-appearance: none; 
-moz-appearance: none;
appearance: none;
margin-left: 100px;
}

#title {
width: 980px; 
height: 50px;
padding: .8em .5em; 
border: 1px solid #B9B6B6;
font-family: inherit;  
/* background: url('arrow.jpg') no-repeat 95% 50%;  */
border-radius: 0px; 
-webkit-appearance: none; 
-moz-appearance: none;
appearance: none;
margin-left: 100px;
}

#content {
width: 980px; 
height: 500px;
padding: .8em .5em; 
border: 1px solid #B9B6B6;
font-family: inherit;  
/* background: url('arrow.jpg') no-repeat 95% 50%;  */
border-radius: 0px; 
-webkit-appearance: none; 
-moz-appearance: none;
appearance: none;
margin-left: 100px;
}

#fh5co-main {
  width: 85%;
  float: right;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
@media screen and (max-width: 1200px) {
  #fh5co-main {
    width: 70%;
  }
}
@media screen and (max-width: 768px) {
  #fh5co-main {
    width: 100%;
  }
}
#fh5co-main .fh5co-narrow-content {
  position: relative;
  width: 80%;
  margin: 0 auto;
  padding: 4em 0;
}
@media screen and (max-width: 768px) {
  #fh5co-main .fh5co-narrow-content {
    width: 100%;
    padding: 3.5em 1em;
  }
}
</style>