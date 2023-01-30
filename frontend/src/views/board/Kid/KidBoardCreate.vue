
<template>
  <div id="KidBoardCreate">
    <div id="worryBoardWrite">
      <br>
      <h1>고민 게시판</h1>
      <br>
      <div id="worryCategoryWrite"> 
        <router-link to="/KidBoard" id="KidCategory" class="CategoryClass" >아동 상담</router-link> 
        <router-link to="/KidBoard" id="TeenCategory" class="CategoryClass">청소년 상담</router-link>
        <router-link to="/" id="AdultCategory" class="CategoryClass">성인 상담</router-link>
        <router-link to="/KidBoard" id="CoupleCategory" class="CategoryClass">부부 상담</router-link>
        <router-link to="/KidBoard" id="OldCategory" class="CategoryClass">노년 상담</router-link>
        <router-link to="/KidBoard" id="GuitarCategory" class="CategoryClass">기타 상담</router-link>
      </div>
      <div>

      </div>
    </div>
    <div>
      <div id="Writebox">
        <form @submit.prevent="KidBoardcreateArticle">
          <div style="text-align:start; padding: 10px;">
            <label for="category">대상</label>
            <select id="worryselect">
              <option>아동</option>
              <option>청소년</option>
              <option>성인</option>
              <option>부부</option>
              <option>노년</option>
              <option>기타</option>
            </select>
          </div>
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

const API_URL = 'http://127.0.0.1:8000'


export default {
  name: 'KidBoardCreate',
  data() {
    return {
      title: null,
      content: null,
    }
  },
  methods: {
    KidBoardcreateArticle() {
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
        url: `${API_URL}/backend/`,
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
          this.$router.push({ name: 'KidBoard' })
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>



<style>
#KidBoardCreate {
  width: 1255px;
  margin: 0 auto;
}

a {
  text-decoration: none;
  color: white;
}

#worryBoardWrite {
  background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('@/assets/hand.png');
  background-color: aliceblue;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  text-align: center;
  font-size: large;
  font-weight: 100;
  padding-top: 20px;

}
#worryCategoryWrite {
  width:100%; 
  height:50px; 
  line-height: 53.5px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
  background-color:rgba(96, 96, 96, 0.5);
  padding-left: 60px;
  padding-right:60px;
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