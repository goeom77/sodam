<template>
  <div id="InquiryListItem" v-if="(limit-1)*10 <= index && index < limit * 10">
    <div style="float:left; margin-left: 60px;">
      <h5>{{ Inquiryarticle.id }}</h5>
    </div>
    <div >
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'InquiryDetail', 
            params: { id: Inquiryarticle.id } 
          }"
        >
          {{ Inquiryarticle.title }}
        </router-link>
        <!-- <button style="color:black" @click="clickList">{{ KidBoardarticle.title }}</button> -->
      </div>
      <div>
        <p>{{ Inquiryarticle.clientId }}  {{ KidBoardarticle?.createdAt }}</p>
      </div>
    </div>
    <hr>

  </div>
</template>

<script>

export default {
  name: 'InquiryListItem',
  props: {
    Inquiryarticle: Object,
    index: Number,
    limit: Number,
  },
  methods: {
    // clickList() {
    //   this.$router.push({
    //     name: "KidBoardDetail",
    //     params: { id: this.KidBoardarticle.id },
    //   });
    // },
  }
}
</script>

<style>
#InquiryListItem {
  /* width: 1255px; */
  margin-left: 60px;
  margin-right: 60px;
}
</style>