
<template>
  <div id="InquiryCreate">
    <div id="InquiryCreateBoard">
      <div id="InquiryCreateBoardtitle">
        <h1>HELP DESK</h1>
      </div>
      <div id="InquiryCreateWrite"> 
        <router-link to="/Announce" id="AnnounceCategory" class="CategoryClass" >공지사항</router-link>
        <router-link to="/inquiry" id="InquiryCategory" class="CategoryClass">1:1 문의</router-link>
      </div>
      <div>

      </div>
    </div>
    <div>
      <div id="HelpWritebox">
        <form @submit.prevent="InquirycreateArticle">
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6;">
            <label for="title">제목</label>
            <input type="text" id="title" v-model.trim="title">
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6; border-bottom: 1px solid black;">
            <label for="content">내용</label>
            <textarea id="content" v-model="content"></textarea>
          </div>
          <input type="submit" id="submitno" value="취소">
          <input type="submit" id="submityes" value="등록">
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL


export default {
  name: 'InquiryCreate',
  data() {
    return {
      title: null,
      content: null,
    }
  },
  methods: {
    InquirycreateArticle() {
      const title = this.title
      const content = this.content
      if (!title) {
        alert('제목을 입력해주세요')
        return
      } else if (!content) {
        alert('내용을 입력해주세요')
        return
      }
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/backend/`,
        data: {
          title: title,
          content: content,
        },
        headers: {
          Authorization: `Token ${this.$store.state.token}`
        }
      })
        .then((res) => {
          console.log(res)
          this.$router.push({ name: 'Inquiry' })
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>



<style>
#InquiryCreate {
  width: 1255px;
  margin: 0 auto;
}

a {
  text-decoration: none;
  color: white;
}

#InquiryCreateBoard {
  background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('@/assets/hand.png');
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
#InquiryCreateWrite {
  width:100%; 
  height:60px; 
  line-height: 65px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  background-color:rgba(96, 96, 96, 0.5);
  padding-left: 60px;
  padding-right:60px;
  position: absolute;
  bottom: 0px;
} 
#InquiryCreateBoardtitle {
  position: absolute;
  left: 50%; 
  bottom: 50%; 
  transform: translate(-50%);
}
#HelpWritebox {
  border-top: 1px solid black;
  /* border-bottom: 1px solid black; */
  margin: 60px;
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