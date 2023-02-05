<template>
  <v-container>
    <div class="parent">
      <div class="child1">
        <v-img src="https://cdn.vuetifyjs.com/images/cards/halcyon.png"></v-img>
      </div>
      <div class="child2">
        
        <span>
          {{ counselorData.name }} 상담사
        </span>
        <h2>
          {{ counselorData.introduce }}
        </h2>
        <h4>
          {{ counselorData.career }}
        </h4>
        <!-- 이모티콘 하나 email이랑 전화 -->
        <h4>
          {{ counselorData.email }}
        </h4>
        <h4>
          {{ counselorData.tel }}
        </h4>
        <h4>
          {{ counselorData.consultTypeList }}
        </h4>
        
        <h4 v-if="(this.common_code==2)">
          좋아요
        </h4>
      </div>
    </div>  
  </v-container>
  </template>

<script>
export default {
  name:'ConsultantCardItemInfo',
  props:{
    counselorData:Object
  },
  data(){
    return{
      common_code: this.$store.state.payload.common_code
    }
  }
}
</script>

<style>
.parent {
    display: flex;
}
.child1 {
    flex: 3;
}
.child2 {
    flex: 9;
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


</style>