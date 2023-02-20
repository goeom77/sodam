
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
        <div style="padding-top:30px; padding-bottom: 30px; text-align: center; font-size:40px; font-weight: bolder;"> 
          문의
        </div>
        <div class="row" style=" border-top:1px solid black; border-bottom:1px solid #ccc;">
          <div class="col-1" id="headercontent">
            제목 
          </div>
          <div class="col-11" id="inputcontent">
            <input type="text" placeholder="제목" v-model="this.title"
            style="display: flex; border: 1px solid #ccc; border-radius: 4px; width:100%; height:40px" >
          </div>
        </div>

        <div class="row">
          <div class="col-1" id="headercontent">
            내용 
          </div>
          <div class="col-11" id="inputcontent">
            <textarea name="" id="" cols="30" rows="10" placeholder="내용을 작성해주세요" v-model="this.content"
            style="display: flex; border: 1px solid #ccc; border-radius: 4px; width:100%;"></textarea>
          </div>
          
        </div>
        <div style="text-align:center;">
            <v-btn append-icon="mdi-pencil" @click="QnaCreateArticle">
              작성
            </v-btn>
            <v-btn append-icon="mdi-arrow-left" @click="moveback">
              취소
            </v-btn>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL


export default {
  name: 'QnaCreateView',

  data() {
    return {
      uploadimageurl: [],    // 업로드한 이미지의 미리보기 기능을 위해 url 저장하는 객체
      imagecnt: 0,           // 업로드한 이미지 개수 => 제출버튼 클릭시 back서버와 axios 통신하게 되는데, 이 때 이 값도 넘겨줌
      id: this.$route.params.id,
      title: null,
      content: null,
      writerId: this.$store.state.payload.id,
      

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
    this.QnaArticleContent()
  },

  methods: {
    QnaArticleContent() {
      const id  = this.id 
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/help-desk/qna/${this.$route.params.id}`,
        // url: `${VUE_APP_API_URL}/trouble/${postId}`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
        .then((res) => {
          // console.log(res)
          console.log('됐음 멍')
          this.title = res.data.title
          this.content = res.data.content
        })
        .catch(() => {
          console.log('안됐음 멍')
        })
    },

    QnaCreateArticle() {
      const title = this.title
      const content = this.content
      const writerId = this.writerId

      if (!title ) {
        alert('제목을 선택해주세요')
        return
      } else if (!content) {
        alert('내용을 입력해주세요')
        return
      }

        axios({
          method: 'post',
          url: `${VUE_APP_API_URL}/api/help-desk/qna/writing`,
          data: {
            title: title,
            content: content,
            writerId: writerId,
            // imagecnt: this.imagecnt
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
          .then((res) => {
            console.log(res)
            this.$router.push({ 
              name: 'HelpView'})
          })
          .catch((err) => {
            console.log('여긴 에러')
            console.log(err)
          })
    },


    //이미지 변경
    // onImageChange(file) {    // v-file-input 변경시
    //       if (!file) {
    //         return;
    //       }
    //       const formData = new FormData();    // 파일을 전송할때는 FormData 형식으로 전송
    //       this.uploadimageurl = [];        // uploadimageurl은 미리보기용으로 사용
    //       file.forEach((item) => {
    //         formData.append('filelist', item)    // formData의 key: 'filelist', value: 이미지
    //         const reader = new FileReader();
    //         reader.onload = (e) => {
    //           this.uploadimageurl.push({url: e.target.result});
    //           // e.target.result를 통해 이미지 url을 가져와서 uploadimageurl에 저장
    //         };
    //         reader.readAsDataURL(item);
    //       });
    //       axios({
    //         url: "http://127.0.0.1:52273/content/imagesave/",    // 이미지 저장을 위해 back서버와 통신
    //         method: "POST",
    //         headers: {'Content-Type': 'multipart/form-data'},    // 이걸 써줘야 formdata 형식 전송가능
    //         data: formData,
    //       }).then(res => {
    //         console.log(res.data.message);
    //         this.imagecnt = file.length;    // 이미지 개수 저장
    //       }).catch(err => {
    //         alert(err);
    //       });
    //     },



    QnaarticleUpdate() {
      const title = this.title
      const content = this.content

      axios({
        method: 'put',
        url: `${VUE_APP_API_URL}/api/help-desk/qna/${this.$route.params.id}`,
        data: {
          title: title,
          content: content,
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$router.push({ 
          name: 'QnaDetailView', 
          params: { id: this.$route.params.id } })


          
        // this.updateStatus = false
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    },
    moveback(){
      this.$router.go(-1);
    }
  }
}
</script>



<style>
/* #QnaCreate {
  width: 1255px;
  margin: 0 auto;
}

a {
  text-decoration: none;
  color: white;
}

#QnaCreateBoard {
  background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('@/assets/images/hand.png');
  background-color: aliceblue;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  text-align: center;
  font-size: large;
  font-weight: 100;
  padding-top: 20px;
  height: 250px;
  position: relative;
}
#QnaCategoryWrite {
  width:100%; 
  height:60px; 
  line-height: 65px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
  background-color:rgba(96, 96, 96, 0.5);
  padding-left: 60px;
  padding-right:60px;
  position: absolute;
  bottom: 0px;
} 
#QnaCreateBoardtitle {
  position: absolute;
  left: 50%; 
  bottom: 50%; 
  transform: translate(-50%);
} */

#Writebox {
  border-top: 1px solid black;
  margin: 60px;
}

#worryselect {
width: 980px; 
height: 50px;
padding: .8em .5em; 
border: 1px solid #B9B6B6;
font-family: inherit;  
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
border-radius: 0px; 
-webkit-appearance: none; 
-moz-appearance: none;
appearance: none;
margin-left: 100px;
}

#image {
width: 990px; 
height: 68px;
padding: .4em .5em; 
/* border: 1px solid #B9B6B6; */
font-family: inherit;  
/* background: url('arrow.jpg') no-repeat 95% 50%;  */
border-radius: 0px; 
-webkit-appearance: none; 
-moz-appearance: none;
appearance: none;
margin-left: 130px;
}
/* worryselect::-ms-expand {
        display: none;
} */

#submitno {
margin-top: 50px;
}

#submityes {
margin-top: 50px;
}
</style>