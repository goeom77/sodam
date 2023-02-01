<template>
  <div id="InquiryList">
    <div id="InquiryWriteButton">
      <router-link to="/announce" id="InquiryListAll" style="color:black">전체 (5)</router-link> 
      <router-link to="/InquiryCreate" id="InquiryCreateButton" class="InquiryCreateButton" >글쓰기</router-link> 
    </div>

    <InquiryListItem
      v-for="Inquiryarticle in Inquiryarticles"
      :key="Inquiryarticle.id"
      :Inquiryarticle="Inquiryarticle"
    /> 
    <InquiryListItem/>
  </div>

</template>

<script>
import InquiryListItem from '@/components/helpitem/InquiryListItem.vue'

export default {
  name: 'InquiryList',
  components: {
    InquiryListItem
  },
  // computed: {
  //   articles() {
  //     return this.$store.state.Inquiryarticles
  //   }
  // },
  // created() {
  //   this.getInquiryarticles()
  // },
  // methods: {
  //   getInquiryarticles() {
  //   this.$store.dispatch('getInquiryarticles')
  //   }
  // }


}
</script>

<style>
/* #KidBoardList {
  text-align: end;
} */
#InquiryWriteButton {
  height: 40px;
  line-height: 40px;
  margin-top: 50px;
  margin-left: 60px;
  margin-right: 60px;
  border-bottom: 1px solid #5F5F5F;
  display: flex;
  vertical-align: middle;
  justify-content: space-between;
  padding-left: 10px;
  padding-right: 10px;
  font-weight: 700;
}

.InquiryCreateButton {
  float:left;
  height: 30px;
  line-height: 25px;
  font-weight: 700;
  border: 1px solid white;
  /* border-bottom: 0px; */
  background-color: #579BB1;
  padding: 3px;
  padding-left: 10px;
  padding-right: 10px;
  border-radius: 5px;
}

#AnnounceList {
  color: black;
}

</style>