<template>
  histo
</template>

<script>
// @ is an alias to /srcz

export default {
  name: 'HistoryView',
  components: {
    
  },
  // created() {
  //   this.getHistoryViewArticles()
  // },
  // methods: {
  //   getHistoryViewArticles() {
  //   this.$store.dispatch('getHistoryViewArticles')
  //   }
  // }
}
</script>


<style>
#HistoryView {
  /* 반응형 x */
  width: 1255px;
  margin: 0 auto;
}

a {
  text-decoration: none;
  color: white;
}

a:visited {
  background-color: #579BB1;
}


#HistoryViewBoard {
  /* margin-top: 61px; */
  width: 100%;
  background-image: linear-gradient( rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5) ), url('@/assets/hand.png');
  background-color: aliceblue;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  /* background-size: cover; */
  text-align: center;
  font-size: large;
  font-weight: 100;
  padding-top: 20px;
  height: 250px;
  position: relative;
}
#HistoryViewBoardCategory {
  width:100%; 
  height:60px; 
  line-height: 65px;
  /* justify-content: center; */
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  background-color:rgba(96, 96, 96, 0.5);
  padding-left: 60px;
  padding-right:60px;
  position: absolute;
  bottom: 0px;
} 
#HistoryViewBoardTitle {
  position: absolute;
  left: 50%; 
  bottom: 50%; 
  transform: translate(-50%);
}

#HistoryViewBoardCategory a.router-link-exact-active {
  background-color: #579BB1;
}

.CategoryClass {
  float:left;
  height:60px; 
  font-weight: 700;
  border: 1px solid white;
  border-bottom: 0px;
}


</style>