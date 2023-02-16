<template>
  <div>
    <div class="container" style="padding:20px; border-bottom:1px solid #ccc">
      <!-- 평점 -->
      <div class="row">
        <div class="col-6">
          {{ review.clientId }} / 
          {{ convertConsultType(review.type) }} 
          <br>
          <v-rating
          model-value="3"
          size="medium"
              density="comfortable"
              color="yellow"
              v-model="star"
              ></v-rating>
            </div>
            <div class="col-3">
              
            </div>
            <div class="col-1">
              
            </div>
            <div class="col-2">
              {{ this.date }}
            </div>
          </div>
          <div>
            {{ review.title }}
          </div>
      <div>
        {{ review.contents }}
        
      </div>
      <div class="row">
        <div class="col-9">
          {{ review.pastCount }}회차 상담 후기
        </div>
        <div class="col-3">
          <v-btn outlined rounded text @click="ReviewDelete">삭제</v-btn>
        </div>
      </div>
      
      
    </div>
    <!-- <blockquote class="grass">
      <h1 @click="goDetail" style="cursor:pointer"><span class="Cgrass">{{review.title}}</span>-{{ review.clientId }}</h1>  
      <p>{{review.contents}}</p>
    </blockquote>    -->
    
  </div>
  
</template>

<script>
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'ConsultantCardItemReviewItem',
  props:{
    review:Object,
  },
  data(){
    return{
      star: this.review.stars,
      date: this.review.createdAt.split('T')[0]
    }
  },
  methods:{
    goDetail(){
      this.$router.push({name:'ConsultantCardItemReviewItemDetail', params:{id: this.review.id}})
    },
    convertConsultType(target) {
      const selectTypeList = [
        {name:'#아동 #청소년', value:"CHILD_TEENAGER"},
        {name:'#재난', value:"CALAMITY"},
        {name:'#부부 #가족상담', value:"COUPLE_FAMILY"},
        {name:'#재활', value:"REHABILITATION"},
        {name:'#노인', value:"AGED"},
        {name:'#중독', value:"ADDICTED"},
        {name:'#정신', value:"MENTAL_HEALTH"},
        {name:'#교정', value:"CORRECTION"},
        {name:'#진로', value:"COURSE"},
        {name:'#상담자교육', value:"EDUCATION"},
        {name:'#성폭력', value:"SEXUAL_VIOLENCY"},
        {name:'#상담자슈퍼비전', value:"SUPERVISION"},
        {name:'#스포츠상담', value:"SPORTS"},
        {name:'#학교상담', value:"SCHOOL"},
      ];
      
      let result = "";
      selectTypeList.forEach( type => {
          if(type.value === target) {
              result += type.name + " ";
              return;
          }
      })
      return result;
    },
    ReviewDelete(){
        axios({
          method:'delete',
          url:`${VUE_APP_API_URL}/api/review/review?reviewId=${this.review.id}`,
          data: {
            reviewId: this.review.id,
          },
          headers: {
            Authorization : `Bearer ${this.$store.state.token.token.access_token}`
          }
        })
        .then(res=>{
          this.$router.push({name:'ConsultantCardItem'})
          console.log(res)

        })
      }
  }
}
</script>

<style>
#fh5co-main2 {

  float: right;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
@media screen and (max-width: 1200px) {
  #fh5co-main2 {
    width: 70%;
  }
}
@media screen and (max-width: 768px) {
  #fh5co-main2 {
    width: 100%;
  }
}
#fh5co-main .fh5co-narrow-content {
  position: relative;
  width: 80%;
  margin: 0 auto;
  padding: 1em 0;
}
@media screen and (max-width: 768px) {
  #fh5co-main2 .fh5co-narrow-content {
    width: 100%;
    padding: 3.5em 1em;
  }
} 
blockquote{
  display:block;
  background: #fff;
  padding: 15px 20px 15px 45px;
  margin: 0 0 20px;
  position: relative;
  
  /*Font*/
  font-family: Georgia, serif;
  font-size: 14px;
  line-height: 1.2;
  color: #666;

  /*Box Shadow - (Optional)*/
  -moz-box-shadow: 2px 2px 15px #ccc;
  -webkit-box-shadow: 2px 2px 15px #ccc;
  box-shadow: 2px 2px 15px #ccc;

  /*Borders - (Optional)*/
  border-left-style: solid;
  border-left-width: 15px;
  border-right-style: solid;
  border-right-width: 2px;    
}
blockquote::before{
  content: "\201C"; /*Unicode for Left Double Quote*/
  
  /*Font*/
  font-family: Georgia, serif;
  font-size: 60px;
  font-weight: bold;
  color: #999;
  
  /*Positioning*/
  position: absolute;
  left: 10px;
  top:5px;
  
}

blockquote::after{
  /*Reset to make sure*/
  content: "";
}

blockquote a{
  text-decoration: none;
  background: #eee;
  cursor: pointer;
  padding: 0 3px;
  color: #c76c0c;
}

blockquote a:hover{
 color: #666;
}

blockquote em{
  font-style: italic;
}
/*Grass Color Palette*/
blockquote.grass{
  border-left-color: #9fd477;
  border-right-color: #8bc163;
}
.ReviewContainer{
  padding: 20px;
  border-bottom: solid 1px #ccc;
}
</style>