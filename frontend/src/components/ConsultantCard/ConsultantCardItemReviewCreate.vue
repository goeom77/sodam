<template>
  <div @click="checkData">후기</div>
    <div>
      <div id="Writebox">
        <form>
          <div style="text-align:start; padding: 10px;">
          </div>
          <!-- 평점 -->
          <div class="text-center">
            <v-rating
              v-model="stars"
              hover
              half-increments
            ></v-rating>
          </div>

          <div style="text-align:start; padding: 10px;">
            <label for="category">대상</label>
            <select id="worryselect" v-model="this.type" >
              <option 
                v-for="(item, index) in selectTypeList"
                :key="index"
                :value="item.value"
                
                >{{ item.name }}</option
              >
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
          <input type="submit" id="submityes" @click='ReviewCreate' value="등록">
          <input type="submit" id="submitno" value="취소">
          <!-- <button @click="postId !== undefined ? KidBoardarticleUpdate() : KidBoardcreateArticle()">{{ postId !== undefined ? "수정" : "작성" }}</button> -->
        </form>
        <!-- <button @click="ReviewUpdate">수정</button> -->
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  const API_URL = 'http://127.0.0.1:8080'
  
  export default {
    name:'ConsultantCardItemReviewCreate',
    props:{
      id:String
    },
    data(){
      return {
        counselorId:null,
        title:null,
        content:null,
        type:null,
        stars:1.0,
        selectTypeList: [
          {name:'아동 청소년', value:"CHILD_TEENAGER"},
          {name:'재난', value:"CALAMITY"},
          {name:'부부 및 가족상담', value:"COUPLE_FAMILY"},
          {name:'재활 상담', value:"REHABILITATION"},
          {name:'노인 상담', value:"AGED"},
          {name:'중독 상담', value:"ADDICTED"},
          {name:'정신 건강', value:"MENTAL_HEALTH"},
          {name:'교정 상담', value:"CORRECTION"},
          {name:'진로 상담', value:"COURSE"},
          {name:'상담자 교육', value:"EDUCATION"},
          {name:'성폭력 상담', value:"SEXUAL_VIOLENCY"},
          {name:'상담자 슈퍼비전', value:"SUPERVISION"},
          {name:'스포츠 상담', value:"SPORTS"},
          {name:'학교 상담', value:"SCHOOL"},
        ],
      }
    },
    methods:{
      checkData(){
        console.log(this.title, this.content, this.type, this.counselorId)
      },
      ReviewCreate(){
        const title = this.title
        const content = this.content
  
        if (!title){
          alert('제목을 입력해주세요')
          return
        } else if(!content){
          alert('내용을 입력해주세요')
          return
        }
        axios({
          method:'post',
          url:`${API_URL}/api/review`,
          data: {
            clientId:this.$store.state.payload.id,
            title: this.title,
            contents: this.content,
            counselorId: this.counselorId,
            passCount:0,
            stars:this.stars,
            type: this.type,
          },
          headers: {
            Authorization: `Token ${this.$store.state.token}`
          }
        })
        .then(res=>{
          console.log(res)
        //   this.$router.push({name:'consultantcarditem'})
        })
      },
      changePropstoData(){
        this.counselorId = this.id
      },  
    },
    created() {
      this.changePropstoData()
  }
  }
  </script>
  
  <style>
  
  </style>