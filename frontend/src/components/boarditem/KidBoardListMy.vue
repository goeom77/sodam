<template>
  <div id="KidBoardListMy" >
    <div v-if="searchfinish===false">
      <KidBoardListItem
        v-for="(Boardarticle, index) in BoardMyarticles.content"
        :key="Boardarticle.postId"
        :Boardarticle="Boardarticle"
        :index="index"
        :limit="KidBoardListPage"
      /> 
    </div>
    <div v-if="searchfinish===true">
      <KidBoardListItem
        v-for="(Boardarticle, index) in contentlist.content"
        :key="Boardarticle.postId"
        :Boardarticle="Boardarticle"
        :index="index"
        :limit="KidBoardListPage"
        this.searchfinish: false
      /> 
    </div>

    <div v-if="BoardMyarticles" class="text-center">
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
  name: 'KidBoardListMy',
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
    BoardMyarticles() {
      return this.$store.state.BoardMyarticles
    },
    created() {
    this.getBoardMyArticles()
   },
  },
  methods: {
    getBoardMyArticles() {
    this.$store.dispatch('getBoardMyArticles')
    },


    KidBoardarticlessearch() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/list/child?searchword=${this.schVal}`
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
#KidBoardListMy {
  width: 1255px;
}




</style>