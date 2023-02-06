<template>
  <div id="KidBoardList" >
    <div id="WriteButton">
      <!-- <router-link to="/KidBoard" id="KidBoardListAll">전체 (5)</router-link>  -->
      <div id="KidBoardListAll" @click="KidBoardListAll">전체 (5)</div>
      <router-link to="/KidBoardCreate" id="KidBoardCreateButton" class="KidBoardCreateButton" >글쓰기</router-link> 
    </div>
    <div>
      
    </div>
    <div v-if="searchfinish===false">
      <KidBoardListItem
        v-for="(KidBoardarticle, index) in KidBoardarticles.content"
        :key="KidBoardarticle.postId"
        :KidBoardarticle="KidBoardarticle"
        :index="index"
        :limit="KidBoardListPage"
      /> 
    </div>
    <div v-if="searchfinish===true">
      <KidBoardListItem
        v-for="(KidBoardarticle, index) in contentlist.content"
        :key="KidBoardarticle.postId"
        :KidBoardarticle="KidBoardarticle"
        :index="index"
        :limit="KidBoardListPage"
        this.searchfinish: false
      /> 
    </div>

    <div v-if="KidBoardarticles" class="text-center">
      <v-pagination
        v-model="this.KidBoardListPage"
        :length="5"

      ></v-pagination>
    </div>
    <v-row>
					<v-col cols="12" md="8">
						<v-text-field
							v-model="schVal"
							label="검색어"
							single-line
							@keypress.enter.prevent="KidBoardarticlessearch"
						></v-text-field>
					</v-col>
					<v-col align-self="center">
						<button
							@click="KidBoardarticlessearch"
              style="width: 100px; height: 50px; background-color: black;"
							rounded
							small
							block
							btnName="Search"
						></button>
					</v-col>
				</v-row>
    <!-- :total-rows="KidBoardarticles.length"
        :per-page="dataPerPage" -->

  </div>

</template>

<script>

import axios from 'axios'
import KidBoardListItem from '@/components/boarditem/KidBoardListItem.vue'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'KidBoardList',
  data() {
    return {
      KidBoardListPage: 1,
			schVal: '',
      searchfinish: false,
      contentlist: [], 
    }
  },
  components: {
    KidBoardListItem
  },
  computed: {
    KidBoardarticles() {
      return this.$store.state.KidBoardarticles
    },
  },
  methods: {
    KidBoardarticlessearch() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/list/child?searchword=schVal`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
        .then((res) => {
          console.log(res)
          this.contentlist = res.data;
          this.searchfinish = true;
          this.schVal = '';
          console.log('됐음 카멜레온')
          // this.KidBoardarticles = res.data.category
        })
        .catch(() => {
          console.log('안됐음 카멜레온')
        })
    },
    KidBoardListAll(){
      this.searchfinish = false;
      this.$router.push({ name: 'KidBoard' },)
    },
  }
}
</script>

<style>
#KidBoardList {
  width: 1255px;
}
#WriteButton {
  
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

.KidBoardCreateButton {
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

#KidBoardListAll {
  color: black;
  
}


</style>