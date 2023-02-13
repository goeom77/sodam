<template>
  <v-container>
    <div class="parent">
      <div class="child1" style="padding-right: 15px;">
        <img v-bind:src="`${counselorData.profileImg}`" alt="까비" style="width:100%">
      </div>
      <div class="child2">
        <h1 style="display:inline;">
          {{ counselorData.name }} 상담사


            <span v-if="this.likestatus===false" @click="likeCounselor">
              <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-heart-filled" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                <path d="M19.5 12.572l-7.5 7.428l-7.5 -7.428a5 5 0 1 1 7.5 -6.566a5 5 0 1 1 7.5 6.572" fill="currentColor"></path>
              </svg>
            </span>
            <div v-else @click="deleteFav">
              <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-heart-filled" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                <path d="M19.5 12.572l-7.5 7.428l-7.5 -7.428a5 5 0 1 1 7.5 -6.566a5 5 0 1 1 7.5 6.572" fill="currentColor"></path>
              </svg>
            </div>

</h1> 
        <!-- 하트 표시 -->
          <!-- <label class="like" style="float:right;">
            <input type="checkbox"/>
            <div class="hearth"/>
          </label>
          <a class="dribbble" href="https://dribbble.com/TaminoMartinius" target="_blank">
            <img src="https://cdn.dribbble.com/assets/dribbble-ball-mark-2bd45f09c2fb58dbbfb44766d5d1d07c5a12972d602ef8b32204d28fa3dda554.svg" alt="" id="mybtn" @click="likeCounselor"/>
          </a> -->
        

        
        <br>
        <h2>
          {{ counselorData.introduce }}
        </h2>

        <!-- 이모티콘 하나 email이랑 전화 -->
        <h4>
          {{ counselorData.consultTypeList }}
        </h4>




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


<!-- 
        <div>
          <button class="buttonSize" style="color:#ea4335" @click="likeCounselor">
            관심 상담사 등록 
          </button>
        </div> -->
      </div>
    
  </v-container>
  </template>

<script>
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name:'ConsultantCardItemInfo',
  props:{
    counselorData:Object
  },
  data(){
    return{
      common_code: this.$store.state.payload.common_code,
      clientId:this.$store.state.payload.id,
      FavCounselor:null,
      likestatus:false
    }
  },
  methods:{
    likeCounselor(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/client/${this.clientId}/fav/${counselorData.id}`,
        data:{
          clientId:this.clientId,
          counselorId : this.counselorData.id
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        console.log(res)
      })
    },
    deleteFav(){
      axios({
        method:'delete',
        url: `${VUE_APP_API_URL}/api/client/${this.clientId}/fav/${this.counselorData.id}`,
        data:{
          clientId: this.clientId,
          counselorId: this.counselorData.id
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        console.log(res)
        console.log('카드 삭제 댐')
      })

    },
    getFavCounselor(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/fav/${this.clientId}`,
        data:{
          clientId:this.clientId,
        },
        // headers: {
        //   Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        // }
      })
      .then(res=>{
        let num = 0
        let idx = res.data.length

        for (let i = 0; i < idx; i++){
          if (res.data[i].id === this.counselorData.id){
            num ++
          }
        }

        if (num === 0){
          this.likestatus = false
        } else{
          this.likestatus = true
        }

        // const checkList = res.data
        // const counselorData = this.counselorData
        // checkList.forEach(function (check){
        //   if (check.id === counselorData.id){
        //     // 클릭 한번
        //     console.log(check)
        //   }
        // })
        console.log(res.data)
        this.FavCounselor = res.data
        console.log("fav : >> " + JSON.stringify(res.data))
      })
    }
  },
  beforeMount(){
    this.getFavCounselor()
  }
}
</script>

<style>
/* 찜하기, 상담신청 버튼 */


/* 전화번호  */




.child2{
  font-family: "Roboto", Arial, sans-serif;
  font-weight: 300;
  font-size: 20px;
  line-height: 1.6;
  color: rgba(0, 0, 0, 0.5);
}
.parent {
    display: flex;
}
.child1 {
    flex: 3;
}
.child2 {
    flex: 8;
    font: inherit;
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



/* he */

:root {
  --size: 100px;
  --frames: 62;
}

html {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  user-select: none;
}


.like {
  display: block;
  width: var(--size);
  height: var(--size);
  cursor: pointer;
  border-radius: 999px;
  overflow: visible;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  -webkit-tap-highlight-color: transparent;
}

.hearth {
  background-image: url('https://assets.codepen.io/23500/Hashflag-AppleEvent.svg');
  background-size: calc(var(--size) * var(--frames)) var(--size);
  background-repeat: no-repeat;
  background-position-x: calc(var(--size) * (var(--frames) * -1 + 2));
  background-position-y: calc(var(--size) * 0.02);
  width: var(--size);
  height: var(--size);
}

input:checked + .hearth {
  animation: like 1s steps(calc(var(--frames) - 3));  
  animation-fill-mode: forwards;
}

@keyframes like {
  0% {
    background-position-x: 0;
  }
  100% {
    background-position-x: calc(var(--size) * (var(--frames) * -1 + 3));
  }
}

@media (hover: hover) {
  .like:hover {
    background-color: #E1255E15;
    .hearth {
      background-position-x: calc(var(--size) * (var(--frames) * -1 + 1));
    }
  }
}

// hashflag-gallery - dribbble - twitter
.hashflag {
  position: fixed;
  display: block;
  right: 114px;
  bottom: 20px;
  img {
    display: block;
    height: 28px;
  }
}
.dribbble {
  position: fixed;
  display: block;
  right: 20px;
  bottom: 20px;
  img {
    display: block;
    height: 28px;
  }
}
.twitter {
  position: fixed;
  display: block;
  right: 64px;
  bottom: 14px;
  svg {
    width: 32px;
    height: 32px;
    fill: #1da1f2;
  }
}

</style>