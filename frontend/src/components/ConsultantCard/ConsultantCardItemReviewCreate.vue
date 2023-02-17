<template>

<div id="fh5co-main">
  <div class="fh5co-narrow-content">

  <div @click="checkData">후기</div>
    
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
            <select v-model="this.type" style="margin-left:30px; border:1px solid black; border-radius: 4px; width: 600px;" >
              <option 
                v-for="(item, index) in selectTypeList"
                :key="index"
                :value="item.value"
                
                >{{ item.name }}</option
              >
            </select>
          </div>
          <div style="text-align:start; padding-left: 10px; border-top: 1px solid #B9B6B6;">
            <label for="title">제목</label>
            <input type="text" v-model.trim="title" style="width: 600px; margin-left:30px; border:1px solid black; border-radius: 4px;">
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6; border-bottom: 1px solid black;">
            <label for="content">내용</label>
            <textarea  v-model="content" style="margin-left:30px; width: 600px; border:1px solid black; border-radius: 4px;"></textarea>
          </div>
          <v-btn outlined rounded text @click="ReviewCreate">등록</v-btn>
          <v-btn outlined rounded text @click="backTo">취소</v-btn>

          <!-- <button @click="postId !== undefined ? KidBoardarticleUpdate() : KidBoardcreateArticle()">{{ postId !== undefined ? "수정" : "작성" }}</button> -->
    
        <!-- <button @click="ReviewUpdate">수정</button> -->
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  const VUE_APP_API_URL = process.env.VUE_APP_API_URL
  
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
          url:`${VUE_APP_API_URL}/api/review`,
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
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(res=>{
          console.log(res)
          this.$router.push({name:'consultantcarditem', params: {id: this.$store.state.userSignupData.id} })
        })
      },
      backTo(){
        this.$router.go(-1);
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