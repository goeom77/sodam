<template>
  <div id="KidBoardListItem">
    <div>
      <h5>{{ KidBoardarticle.postId }}</h5>
    </div>
    <div>
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'KidBoardDetail', 
            // params: { postId: KidBoardarticle.postId } 
            params: { postId: KidBoardarticle.postId } 
          }"
        >
          {{ KidBoardarticle.title }}
        </router-link>
        <!-- <button style="color:black" @click="clickList">{{ KidBoardarticle.title }}</button> -->
      </div>
      <div>
        <p>{{ KidBoardarticle.clientId }}  {{ KidBoardarticle?.createdAt }}</p>
      </div>
    </div>
    <div>
      <h3>조회수, 답변 수</h3>
    </div>
    <hr>

  </div>
</template>

<script>

export default {
  name: 'KidBoardListItem',
  props: {
    KidBoardarticle: Object,
  },
  methods: {
    // clickList() {
    //   this.$router.push({
    //     name: "KidBoardDetail",
    //     params: { postId: KidBoardarticle.postId },
    //   });
    // },
  }
}
</script>

<style>

</style>