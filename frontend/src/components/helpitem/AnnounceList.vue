<template>
  <div id="AnnounceList">
    <div id="AnnounceWriteButton">
      <router-link to="/Announce" id="AnnounceListAll" style="color:black">전체 (5)</router-link> 
      <div v-if="userId < 1">
        <router-link to="/AnnounceCreate" id="AnnounceCreateButton" class="AnnounceCreateButton" >글쓰기</router-link> 
      </div>
    </div>

    <AnnounceListItem
      v-for="Announcearticle in Announcearticles"
      :key="Announcearticle.id"
      :Announcearticle="Announcearticle"
    /> 
    <AnnounceListItem/>
  </div>

</template>

<script>
import AnnounceListItem from '@/components/helpitem/AnnounceListItem.vue'

export default {
  name: 'AnnounceList',
  components: {
    AnnounceListItem
  },
  // computed: {
  //   articles() {
  //     return this.$store.state.Announcearticles
  //   }
  // },
  // created() {
  //   this.getAnnouncearticles()
  // },
  // methods: {
  //   getAnnouncearticles() {
  //   this.$store.dispatch('getAnnouncearticles')
  //   }
  // }


}
</script>

<style>
/* #KidBoardList {
  text-align: end;
} */
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

#AnnounceList {
  color: black;
}

</style>