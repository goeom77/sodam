<template>
  <div id="MyPostViewListItem" v-if="index < 3" style="margin-bottom: 10px">
    <div style="border: solid 1px gray">
      <v-card width="100%">
        <template v-slot:title>
          <router-link 
            style="color:black" 
            :to="{ 
              name: 'BoardDetailView', 
              params: { postId: MyPostarticle.postId } 
            }"
          >
            {{ MyPostarticle.title }}
          </router-link>
        </template>

        <template v-slot:subtitle>
          작성 자 : {{ MyPostarticle.clientId }}
        </template>

        <template v-slot:text>
          {{ MyPostarticle?.createdAt }}
        </template>
        <div >
          <h3>{{ MyPostarticle.views }}, {{ MyPostarticle.commentCount }}</h3>
        </div>
      </v-card>
    </div>
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