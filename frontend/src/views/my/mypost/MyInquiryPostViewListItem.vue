<template>
  <div id="MyInquiryPostViewListItem" v-if="index < 3" style="margin-top: 10px">
    <div style="border: solid 1px gray">
      <v-card width="100%">
      <template v-slot:title>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'NoticeDetailView', 
            params: { id: MyInquiryPostarticle.id } 
          }"
        >
          {{ MyInquiryPostarticle.title }}
        </router-link>
      </template>

      <template v-slot:subtitle>
        작성 자 : {{ MyInquiryPostarticle.writerId }}
      </template>

      <template v-slot:text>
        작성 일시 : {{ MyInquiryPostarticle?.createdAt }}
      </template>
    </v-card>
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