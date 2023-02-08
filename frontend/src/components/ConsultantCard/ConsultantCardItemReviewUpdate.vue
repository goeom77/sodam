<template>
  <div @click="checkData">후기</div>
    <div>
      <div id="Writebox" v-if="this.reviewData">
        <form>
          <div style="text-align:start; padding: 10px;">
          </div>
          <!-- 평점 -->
          <div class="text-center">
            <v-rating
              v-model="this.reviewData.stars"
              hover
              half-increments
            ></v-rating>
          </div>

          <div style="text-align:start; padding: 10px;">
            <label for="category">대상</label>
            <select id="worryselect" v-model="this.reviewData.type" >
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
            <input type="text" id="title" v-model.trim="this.reviewData.title">
          </div>
          <div style="text-align:start; padding: 10px; border-top: 1px solid #B9B6B6; border-bottom: 1px solid black;">
            <label for="content">내용</label>
            <textarea id="content" v-model="this.reviewData.contents"></textarea>
          </div>
          <v-btn outlined rounded text @click="ReviewUpdate">수정</v-btn>
          <v-btn outlined rounded text @click="backTo">취소</v-btn>

          <!-- <button @click="postId !== undefined ? KidBoardarticleUpdate() : KidBoardcreateArticle()">{{ postId !== undefined ? "수정" : "작성" }}</button> -->
        </form>
        <!-- <button @click="ReviewUpdate">수정</button> -->
      </div>
    </div>
    {{ this.reviewData }}
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
        reviewData:null,
        // counselorId:null,
        // title:this.reviewData.title,
        // content:null,
        // type:null,
        // stars:1.0,
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
      ReviewUpdate(){
        axios({
          method:'post',
          url:`${VUE_APP_API_URL}/api/review/${this.id}`,
          data: {
            clientId:this.$store.state.payload.id,
            title: this.reviewData.title,
            contents: this.reviewData.contents,
            counselorId: this.reviewData.counselorId,
            passCount:0,
            stars:this.reviewData.stars,
            type: this.reviewData.type,
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(res=>{
          console.log(res)
          this.$router.push({name:'ConsultantCardItemReviewItemDetail', params: {id: this.id} })
        })
      },
      getReviewInfo(){
        axios({
          method:'get',
          url:`${VUE_APP_API_URL}/api/review/search?id=${this.id}`,
          data: {
            id: this.id,
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(res=>{
          console.log(res.data)
          this.reviewData = res.data
        })
      },
      backTo(){
        this.$router.go(-1);
      },
    },
    created(){
      this.getReviewInfo()
    }
  }
  </script>
  
  <style>
  
  </style>