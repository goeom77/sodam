<template>
  <div id="MyPostViewListItem" v-if="index < 3">
    <div style="float:left; margin-left: 60px;">
      <h5>내 게시글</h5>
    </div>
    <div >
      <div>
        <router-link 
          v-if="check === 0"
          style="color:black" 
          :to="{ 
            name: 'KidBoardDetail', 
            params: { postId: MyPostarticle.postId } 
          }"
        >
          {{ MyPostarticle.title }}
        </router-link>
      </div>
      <div>
        <router-link 
          v-if="check === 1"
          style="color:black" 
          :to="{ 
            name: 'InquiryDetail', 
            params: { id: MyPostarticle.id } 
          }"
        >
          {{ MyPostarticle.title }}
        </router-link>
      </div>
      <div v-if="check === 0">
        <p>{{ MyPostarticle.clientId }}  {{ MyPostarticle?.createdAt }}</p>
      </div>
      <div v-if="check === 1">
        <p>{{ MyPostarticle.writerId }}  {{ MyPostarticle?.createdAt }}</p>
      </div>
    </div>
    <div v-if="check === 0">
      <h3>{{ MyPostarticle.views }}, {{ MyPostarticle.commentCount }}</h3>
    </div>
    <hr>

  </div>
</template>

<script>

export default {
  name: 'KidBoardListItem',
  props: {
    MyPostarticle: Object,
    index: Number,
    limit: Number,
    check: Number,
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