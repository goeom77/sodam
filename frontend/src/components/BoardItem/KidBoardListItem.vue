<template>
  <div id="KidBoardListItem">
    <h1>여기는 각 게시글</h1>
    <div>

      <!-- <h5>{{ KidBoardarticle.id }} -->
    </div>
    <div>
      <div>
        <!-- <router-link :to="{ name: 'KidBoardDetail', params: { id: KidBoardarticle.id } }">
          {{ KidBoardarticle.title }}
        </router-link> -->
      </div>
      <div>
        <!-- <p>{{ KidBoardarticle.username }}  {{ KidBoardarticle?.created_at }}</p> -->
      </div>
    </div>
    <div>
      <!-- <h3>조회수, 답변 수</h3> -->
    </div>
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