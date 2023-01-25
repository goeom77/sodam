<template>
  <div id="KidBoardListItem">
    <h1>여기는 각 게시글</h1>
    <!-- <h5>{{ KidBoardarticle.id }}</h5>
    <p>작성자 : {{ KidBoardarticle.username }}</p>
    <p>{{ KidBoardarticle.title }}</p>
    <router-link :to="{ name: 'KidBoardDetail', params: { id: KidBoardarticle.id } }">
      [DETAIL]
    </router-link> -->
    <hr>

  </div>
</template>

<script>

export default {
  name: 'KidBoardListItem',
  // props: {
  //   KidBoardarticle: Object,
  // }
}
</script>

<style>

</style>