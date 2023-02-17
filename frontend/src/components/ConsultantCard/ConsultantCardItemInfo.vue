<template>
  <v-container class="counselorInfoArea">
    <div class="parent">
      <div class="child1">
        <img class="detailProfileImg" v-if="counselorData.profileImg != null" v-bind:src="`${counselorData.profileImg}`" >
        <img class="detailProfileImg" v-else v-bind:src="`${this.nullProfileImg}`" alt="까비" >
      </div>
      <div class="child2" style="padding-left:30px">
        <div v-if:toggleHeart="!toggleHeart" @click="likeCounselor">
          <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-bookmark-heart" viewBox="0 0 16 16" style="float:right; cursor:pointer;">
            <path fill-rule="evenodd" d="M8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
            <path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
          </svg>
        </div>
        <div v-else @click="deleteFavCounselor">
          <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-bookmark-heart-fill" viewBox="0 0 16 16" style="float:right; cursor:pointer;">
            <path d="M2 15.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2v13.5zM8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
          </svg>
        </div>

        <h2>{{ counselorData.name }} 상담사</h2>
        <h4>{{ counselorData.introduce }}</h4>
        <h5>{{ convertConsultType(counselorData.consultTypeList) }}</h5>

        <div class="d-flex flex-nowrap" style="padding-top:50px;">
          <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16">
            <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
          </svg>
          <h4 style="padding-left:10px">
            {{ counselorData.tel }}
          </h4>
        </div>
        <div class="d-flex flex-nowrap" >
          <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
            <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z"/>
          </svg>
          <h4 style="padding-left:10px">
            {{ counselorData.email }}
          </h4>
        </div>
      </div>
    </div>
    <ConsultantCardItemInfoDetail  />
  </v-container>
</template>

<script>
import axios from 'axios'
import ConsultantCardItemInfoDetail from '@/components/ConsultantCard/ConsultantCardItemInfoDetail.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name:'ConsultantCardItemInfo',
  components: {
    ConsultantCardItemInfoDetail,
  },
  props:{
    counselorData:Object
  },
  data(){
    return{
      nullProfileImg : require('../../assets/images/사람altimg.png'),
      common_code: this.$store.state.payload.common_code,
      clientId:this.$store.state.payload.id,
      toggleHeart: this.$store.state.userSignupData.like
    }
  },
  methods:{
    likeCounselor(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/client/${this.clientId}/fav/${this.counselorData.id}`,
        data:{
          clientId:this.clientId,
          counselorId : this.counselorData.id
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        this.toggleHeart = true;
        this.$store.state.userSignupData.like = true;
      })
    },
    deleteFavCounselor() {
      axios({
        method:'delete',
        url:`${VUE_APP_API_URL}/api/client/${this.clientId}/fav/${this.counselorData.id}`,
        data:{
          clientId:this.clientId,
          counselorId : this.counselorData.id
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        this.toggleHeart = false;
        this.$store.state.userSignupData.like = false;
      })
    },
    convertConsultType(list) {
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
      list.forEach(element => {
          selectTypeList.forEach( type => {
              if(type.value === element) {
                  result += type.name + " ";
                  return
              }
          })
      });
      return result;
    }
  }
}
</script>

<style>
/* 찜하기, 상담신청 버튼 */
body{
  font-family: 'Roboto', sans-serif;
  text-align: center;
  background: #f1f1f1;
}

#presentation{
  width: 480px;
  height: 120px;
  padding: 20px;
  margin: auto;
  background: #FFF;
  margin-top: 10px;
  box-shadow: 0 3px 15px -5px rgba(0,0,0,0.1); 
  transition: all 0.3s; 
  border-radius: 10px;
  
}
h1{
  font-weight: 400;
}

h3{
  font-weight: 400;
  color: #666;
}

#presentation:hover{
  box-shadow: 0 12px 28px -5px rgba(0,0,0,0.13);
  transition: all 0.3s;
  transform: translateZ(10px);
}

#floating-button{
  width: 55px;
  height: 55px;
  border-radius: 50%;
  background: #db4437;
  position: fixed;
  bottom: 30px;
  right: 30px;
  cursor: pointer;
  box-shadow: 0px 2px 10px rgba(0,0,0,0.2);
}

.plus{
  color: white;
  position: absolute;
  top: 0;
  display: block;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
  padding: 0;
  margin: 0;
  line-height: 55px;
  font-size: 38px;
  font-family: 'Roboto';
  font-weight: 300;
  animation: plus-out 0.3s;
  transition: all 0.3s;
}

#container-floating{
  position: fixed;
  width: 70px;
  height: 70px;
  bottom: 30px;
  right: 30px;
  z-index: 50px;
}

#container-floating:hover{
  height: 400px;
  width: 90px;
  padding: 30px;
}

#container-floating:hover .plus{
  animation: plus-in 0.15s linear;
  animation-fill-mode: forwards;
}

.edit{
  position: absolute;
  top: 0;
  display: block;
  bottom: 0;
  left: 0;
  display: block;
  right: 0;
  padding: 0;
  opacity: 0;
  margin: auto;
  line-height: 65px;
  transform: rotateZ(-70deg);
  transition: all 0.3s;
  animation: edit-out 0.3s;
}

#container-floating:hover .edit{
  animation: edit-in 0.2s;
   animation-delay: 0.1s;
  animation-fill-mode: forwards;
}

@keyframes edit-in{
    from {opacity: 0; transform: rotateZ(-70deg);}
    to {opacity: 1; transform: rotateZ(0deg);}
}

@keyframes edit-out{
    from {opacity: 1; transform: rotateZ(0deg);}
    to {opacity: 0; transform: rotateZ(-70deg);}
}

@keyframes plus-in{
    from {opacity: 1; transform: rotateZ(0deg);}
    to {opacity: 0; transform: rotateZ(180deg);}
}

@keyframes plus-out{
    from {opacity: 0; transform: rotateZ(180deg);}
    to {opacity: 1; transform: rotateZ(0deg);}
}

.nds{
  width: 40px;
  height: 40px;
  border-radius: 50%;
  position: fixed;
  z-index: 300;
  transform:  scale(0);
  cursor: pointer;
}

.nd1{
  background: #d3a411;
  right: 40px;
  bottom: 120px;
  animation-delay: 0.2s;
    animation: bounce-out-nds 0.3s linear;
  animation-fill-mode:  forwards;
}

.nd3{
  background: #3c80f6;
  right: 40px;
  bottom: 180px;
  animation-delay: 0.15s;
    animation: bounce-out-nds 0.15s linear;
  animation-fill-mode:  forwards;
}

.nd4{
  background: #ba68c8;
  right: 40px;
  bottom: 240px;
  animation-delay: 0.1s;
    animation: bounce-out-nds 0.1s linear;
  animation-fill-mode:  forwards;
}

@keyframes bounce-nds{
    from {opacity: 0;}
    to {opacity: 1; transform: scale(1);}
}

@keyframes bounce-out-nds{
    from {opacity: 1; transform: scale(1);}
    to {opacity: 0; transform: scale(0);}
}

#container-floating:hover .nds{
  
  animation: bounce-nds 0.1s linear;
  animation-fill-mode:  forwards;
}

#container-floating:hover .nd3{
  animation-delay: 0.08s;
}
#container-floating:hover .nd4{
  animation-delay: 0.15s;
}
#container-floating:hover .nd5{
  animation-delay: 0.2s;
}

.letter{
  font-size: 23px;
  font-family: 'Roboto';
  color: white;
  position: absolute;
  left: 0;
  right: 0;
  margin: 0;
  top: 0;
  bottom: 0;
  text-align: center;
  line-height: 40px;
}

.reminder{
  position: absolute;
  left: 0;
  right: 0;
  margin: auto;
  top: 0;
  bottom: 0;
  line-height: 40px;
}

.profile{
  border-radius: 50%;
  width: 40px;
  position: absolute;
  top: 0;
  bottom: 0;
  margin: auto;
  right: 20px;
}

/* 전화번호  */



.counselorInfoArea {
  border: 1px solid #dedbdb;
}
.child2{
  font-family: "Roboto", Arial, sans-serif;
  font-weight: 300;
  font-size: 20px;
  line-height: 1.6;
  color: rgba(0, 0, 0, 0.5);
}
.parent {
    display: flex;
    padding: 25px 50px;
    margin-bottom: 15px;
}
.child1 {
    flex: 3;
    margin-right: 3rem;
}
.child2 {
    flex: 8;
    font: inherit;
    padding: 15px 0px;
  }
h1 {
  position: relative;
  padding: 0;
  margin: 0;
  font-family: "Raleway", sans-serif;
  font-weight: 300;
  font-size: 40px;
  color: #080808;
  -webkit-transition: all 0.4s ease 0s;
  -o-transition: all 0.4s ease 0s;
  transition: all 0.4s ease 0s;
}

h1 span {
  display: block;
  font-size: 0.5em;
  line-height: 1.3;
}
h1 em {
  font-style: normal;
  font-weight: 600;
}
.five h1 {
  text-align: center;
  font-size: 22px;
  font-weight: 700; color:#202020;
  text-transform: uppercase;
  word-spacing: 1px; letter-spacing:2px;
}
.five h1 span {
  margin-top: 40px;
  text-transform: none;
  font-size:.75em;
  font-weight: normal;
  font-style: italic; font-family: "Playfair Display","Bookman",serif;
  color:#999; letter-spacing:-0.005em; word-spacing:1px;
  letter-spacing:none;
}
.ImageTag{
  display: flex;
  flex-flow: column nowrap;
  justify-content: center;
  padding: 2rem;
  min-height: 100%;
  height: auto;
}

.Info-body {
  display: flex;
  flex-flow: row wrap;
  margin: 1rem;
}
.detailProfileImg {
  height:230px;
  width:230px;
  object-fit: cover;
  /* border-radius: 50%; */
}

</style>