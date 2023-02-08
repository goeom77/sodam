<template>
  <div id="AnnounceListItem" v-if="(limit-1)*10 <= index && index < limit * 10">
    <div style="float:left; margin-left: 60px;">
      <h5>{{ Announcearticle.id }}</h5>
    </div>
    <div >
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'AnnounceDetail', 
            params: { id: Announcearticle.id } 
          }"
        >
          {{ Announcearticle.title }}
        </router-link>
        <!-- <button style="color:black" @click="clickList">{{ KidBoardarticle.title }}</button> -->
      </div>
      <div>
        <p>관리자  {{ Announcearticle?.createdAt }}</p>
      </div>
    </div>

    <hr>

  </div>
</template>

<script>

export default {
  name: 'AnnounceListItem',
  props: {
    Announcearticle: Object,
    index: Number,
    limit: Number,
  },
  methods: {
    // clickList() {
    //   this.$router.push({
    //     name: "KidBoardDetail",
    //     params: { postId: this.KidBoardarticle.postId },
    //   });
    // },
  }
}
</script>

<style>
#AnnounceListItem {
  /* width: 1255px; */
  margin-left: 60px;
  margin-right: 60px;
}
</style>