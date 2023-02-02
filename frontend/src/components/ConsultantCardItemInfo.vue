<template>
  <div>
    <v-container>
      <div class="ImageTag">
        <v-card class="d-flex mb-6 wt-surface-variant">
          <!-- 사진 -->
          <div class="ma-2 pa-2 me-auto">
            <img src="{{ counsel.profileImg }}" alt="사진 없음">
          </div>
        </v-card>
      </div>
      
      <div class="d-flex mb-6 bg-surface-variant">
        <v-card>
          <div class="ma-2 pa-2">
            <!-- 정보  -->
            <div>
              {{ counsel.name }}
              {{ counsel.introduce }}
              {{ counsel.major }}
              {{ counsel.career }}
              {{ counsel.qualification }}
            </div>
            <!-- 연락처 -->
            <div>
              {{ counsel.email }}
              {{ counsel.tel }}
            </div>
          </div>
        </v-card>
      </div>
  
  
      </v-container>
    </div>  
  </template>

<script>
export default {
  name:'ConsultantCardItemInfo',
  props:{
    counsel: Object
  }
}
</script>

<style>
.ImageTag{
  width:500px;
  height:100%
}
</style>