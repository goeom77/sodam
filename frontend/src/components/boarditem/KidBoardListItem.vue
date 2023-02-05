<template>
  <div id="KidBoardListItem" v-if="(limit-1)*10 <= index && index < limit * 10">
    <div style="float:left; margin-left: 60px;">
      <h5>{{ KidBoardarticle.postId }}</h5>
    </div>
    <div >
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'KidBoardDetail', 
            params: { postId: KidBoardarticle.postId } 
          }"
        >
          {{ KidBoardarticle.title }}
        </router-link>
        <!-- <button style="color:black" @click="clickList">{{ KidBoardarticle.title }}</button> -->
      </div>
      <div>
        <p>{{ KidBoardarticle.clientId }}  {{ KidBoardarticle?.createdAt }}</p>
      </div>
    </div>
    <div >
      <h3>{{ KidBoardarticle.views }}, {{ KidBoardarticle.commentCount }}</h3>
    </div>
    <hr>

  </div>
</template>

<script>

export default {
  name: 'KidBoardListItem',
  props: {
    KidBoardarticle: Object,
    index: Number,
    limit: Number,
  },
  methods: {
    // clickList() {
    //   this.$router.push({
    //     name: "KidBoardDetail",
    //     params: { postId: this.KidBoardarticle.postId },
    //   });
    // },
  }
}
</script>

<style>
#KidBoardListItem {
  /* width: 1255px; */
  margin-left: 60px;
  margin-right: 60px;
}
</style>