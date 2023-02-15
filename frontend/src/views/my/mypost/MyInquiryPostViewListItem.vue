<template>
  <div id="MyInquiryPostViewListItem" v-if="index < 3" style="margin-top: 10px">
    <div style="border: solid 1px gray">
      <div style="float:left; margin-left: 60px;">
        <h5>내 게시글</h5>
      </div>
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'NoticeDetailView', 
            params: { id: MyInquiryPostarticle.id } 
          }"
        >
          {{ MyInquiryPostarticle.title }}
        </router-link>
      </div>
      <div >
        <p>{{ MyInquiryPostarticle.writerId }}  {{ MyInquiryPostarticle?.createdAt }}</p>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'MyInquiryPostViewListItem',
  props: {
    // MyPostarticle: Object,
    MyInquiryPostarticle: Object,
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