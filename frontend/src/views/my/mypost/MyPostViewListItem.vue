<template>
  <div id="MyPostViewListItem" v-if="index < 3" style="margin-bottom: 10px">
    <div style="border: solid 1px gray">
    <!-- <div  v-if="check === 0"> -->
      <div >
        <h5>{{ MyPostarticle.title }}</h5>
      </div>
      <div>
        <div>
          <router-link 
            style="color:black" 
            :to="{ 
              name: 'BoardDetailView', 
              params: { postId: MyPostarticle.postId } 
            }"
          >
            <!-- {{ MyPostarticle.title }} -->
            <!-- {{ MyPostarticle }} -->
          </router-link>
        </div>
        <div>
          <p>{{ MyPostarticle.clientId }}  {{ MyPostarticle?.createdAt }}</p>
        </div>
      </div>
      <div >
        <h3>{{ MyPostarticle.views }}, {{ MyPostarticle.commentCount }}</h3>
      </div>
    </div>
    <!-- <div v-else>
      <div style="float:left; margin-left: 60px;">
        <h5>내 게시글</h5>
      </div>
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'InquiryDetail', 
            params: { id: MyInquiryPostarticle.id } 
          }"
        >
          {{ MyPostarticle.title }}
        </router-link>
      </div>
      <div v-if="check === 1">
        <p>{{ MyInquiryPostarticle.writerId }}  {{ MyInquiryPostarticle?.createdAt }}</p>
      </div>
    </div> -->
  </div>
</template>

<script>

export default {
  name: 'MyPostViewListItem',
  props: {
    MyPostarticle: Object,
    // MyInquiryPostarticle: Object,
    index: Number,
    limit: Number,
    // check: Number,
  },
  methods: {

  }
}
</script>

<style>
/* #MyPostViewListItem {
  width: 1255px;
  margin-left: 60px;
  margin-right: 60px;
} */
</style>