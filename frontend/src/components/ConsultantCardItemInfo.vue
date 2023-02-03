<template>
  {{ counselorData }}
  <v-container>
    <div class="parent">
      <div class="child1">
        <v-img src="https://cdn.vuetifyjs.com/images/cards/halcyon.png"></v-img>
      </div>
      <div class="child2">
        <span>
          {{ counselorData.id }} 상담사
        </span>
        <h2>
          {{ counselorData.introduce }}
        </h2>
        <h4>
          {{ counselorData.career }}
        </h4>
        
        <!-- 연락처 -->
        <h4>
          <!-- 이모티콘 하나 넣 -->
          {{ counselorData.tel }}
          {{ counselorData.email }}
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