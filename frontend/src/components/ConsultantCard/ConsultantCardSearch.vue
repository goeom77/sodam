<template>
  <div>
    <input id="searhBar"
      class="search-input"
      type="text"
      v-model="this.SearchData"
      placeholder="상담사, 카테고리 검색.."
      @keyup="searchCounselor()"
      />
      {{ this.SearchData }}
  </div>
</template>

<script>
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'ConsultantCardSearch',
  data(){
    return{
      SearchData:null
    }
    // 상담사 데이터 
  },
  methods:{
    searchCounselor(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/counselor/search`,
        data:{
          keyword: this.SearchData
        }
      })
      .then(res=>{
        console.log(res.data)
      })
    }
  }
}
</script>

<style>
.search-input {
  display: block;
  background-color: white;
  padding: 4px 8px;
  margin: 10px auto;
  width: 320px;
  font-size: 16px;
  outline: none;
  border:1px solid black;
  border-radius: 10px;
}
</style>