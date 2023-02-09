<template>
  <div>
    <input id="searhBar"
      class="search-input"
      type="text"
      placeholder="상담사, 카테고리 검색.."
      @input="searchGroup($event)"
      />
  </div>
</template>

<script>
export default {
  name:'ConsultantCardSearch',
  data(){
    return{

    }
    // 상담사 데이터 
  },
  methods:{
    searchGroup(event){
      console.log(event)
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