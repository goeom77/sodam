<template>
  <div id="AlarmViewListItem" v-if="(limit-1)*10 <= index && index < limit * 10">
    <div style="float:left; margin-left: 60px;">
      <h5>{{ AlarmViewarticle.postId }}</h5>
    </div>
    <div >
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'AlarmViewDetail', 
            params: { postId: AlarmViewarticle.postId } 
          }"
        >
          {{ AlarmViewarticle.title }}
        </router-link>
        <!-- <button style="color:black" @click="clickList">{{ AlarmViewarticle.title }}</button> -->
      </div>
      <div>
        <p>관리자   {{ AlarmViewarticle?.createdAt }}</p>
      </div>
    </div>
    <div>
      <button @click="AlarmViewDetail">자세히 보기</button>
    </div>
    <hr>

  </div>
</template>

<script>

export default {
  name: 'AlarmViewListItem',
  props: {
    AlarmViewarticle: Object,
    index: Number,
    limit: Number,
  },
  methods: {
    AlarmViewDetail(){
      this.$router.push({ name: 'AlarmViewDetail',  params: { postId: AlarmViewarticle.postId } },)
    },
  }
}
</script>

<style>
#AlarmViewListItem {
  /* width: 1255px; */
  margin-left: 60px;
  margin-right: 60px;
}
</style>