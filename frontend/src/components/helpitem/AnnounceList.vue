<template>
  <div id="AnnounceList" >
    <div id="AnnounceWriteButton">
      <!-- <router-link to="/KidBoard" id="KidBoardListAll">전체 (5)</router-link>  -->
      <div id="AnnounceListAll" @click="AnnounceListAll">전체 (5)</div>
      <div>
        <router-link to="/AnnounceCreate" id="AnnounceCreateButton" class="AnnounceCreateButton" >글쓰기</router-link> 
      </div>
    </div>
    <div>
      
    </div>
    <div>
      <AnnounceListItem
        v-for="(Announcearticle, index) in Announcearticles.content"
        :key="Announcearticle.id"
        :Announcearticle="Announcearticle"
        :index="index"
        :limit="AnnounceListPage"
      /> 
    </div>

    <div v-if="Announcearticles" class="text-center">
      <v-pagination
        v-model="this.AnnounceListPage"
        :length="5"

      ></v-pagination>
    </div>

    <!-- :total-rows="KidBoardarticles.length"
        :per-page="dataPerPage" -->

  </div>

</template>

<script>

import axios from 'axios'
import AnnounceListItem from '@/components/helpitem/AnnounceListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'AnnounceList',
  data() {
    return {
      AnnounceListPage: 1,
			schVal: '',
      contentlist: [], 
    }
  },
  components: {
    AnnounceListItem
  },
  computed: {
    Announcearticles() {
      return this.$store.state.Announcearticles
    },
  },
  methods: {

    AnnounceListAll(){
      this.searchfinish = false;
      this.$router.push({ name: 'Announce' },)
    },
  }
}
</script>

<style>
#AnnounceList {
  width: 1255px;
}
#AnnounceWriteButton {
  
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

.AnnounceCreateButton {
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

#AnnounceListAll {
  color: black;
  
}


</style>