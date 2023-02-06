<template>
  <div>
    <div class="black-bg" v-if="modalopen == true" @click="close($event)">
      <div class="white-bg">
        <h4>상세페이지</h4>
        <p>상세페이지내용임</p>
        <button class="close">닫기</button>
      </div>
    </div>
    <div class="card">
      <h4 class="room-name" @click="modalopen = true"> 모달켜봅시다</h4>
      <p>보자</p>
      <button></button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NoticeView',
  data() {
    return {
      modalopen : false,
    }
  },
  methods: {
    close(event) {
      if(event.target.classList.contains('black-bg') || 
        event.target.classList.contains('close')) {
          this.modalopen = false;
      } else if (event.target.classList.contains('white-bg')){
        this.modalopen = true;
      }
    }
  }
}
</script>

<style>
.black-bg {
  width: 100%; height:100%;
  background: rgba(0,0,0,0.5);
  position: fixed; padding: 20px;
}
.white-bg {
  width: 100%; background: white;
  border-radius: 8px;
  padding: 20px;
} 
.close {
  cursor: pointer;
  border: none;
  background: green;
  color: white;
  font-weight: bold;
  border-radius: 5px;
  padding: 5px 15px;
}

.close:hover {
  color: white;
  font-weight: bold;
  transform: scale(1.1);
  transition: all 0.5s;
}
</style>