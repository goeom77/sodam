<template>
  <div id="InquiryList" >
    <div id="InquiryWriteButton">
      <!-- <router-link to="/KidBoard" id="KidBoardListAll">전체 (5)</router-link>  -->
      <div id="InquiryListAll" @click="InquiryListAll">전체 (5)</div>
      <router-link to="/InquiryCreate" id="InquiryCreateButton" class="InquiryCreateButton" >글쓰기</router-link> 
    </div>
    <div>
      
    </div>
    <div>
      <InquiryListItem
        v-for="(Inquiryarticle, index) in Inquiryarticles.content"
        :key="Inquiryarticle.postId"
        :Inquiryarticle="Inquiryarticle"
        :index="index"
        :limit="InquiryListPage"
      /> 
    </div>

    <div v-if="Inquiryarticles" class="text-center">
      <v-pagination
        v-model="this.InquiryListPage"
        :length="5"

      ></v-pagination>
    </div>
    <!-- :total-rows="KidBoardarticles.length"
        :per-page="dataPerPage" -->

  </div>

</template>

<script>

import axios from 'axios'
import InquiryListItem from '@/components/helpitem/InquiryListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'InquiryList',
  data() {
    return {
      InquiryListPage: 1,
			schVal: '',
      contentlist: [], 
    }
  },
  components: {
    InquiryListItem
  },
  computed: {
    Inquiryarticles() {
      return this.$store.state.Inquiryarticles
    },
  },
  methods: {
    InquiryListAll(){
      this.$router.push({ name: 'Inquiry' },)
    },
  }
}
</script>

<style>
#InquiryList {
  width: 1255px;
}
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

#InquiryListAll {
  color: black;
  
}


</style>