<template>
  <div id="AnnounceListItem">
    <h1>여기는 각 게시글</h1>
    <div>
      <!-- 폴더 -->
      <!-- <h5>{{ Announcearticle.id }} -->
    </div>
    <div>
      <div>
        <!-- <router-link :to="{ name: 'AnnounceDetail', params: { id: Announcearticle.id } }">
          {{ Announcearticle.title }}
        </router-link> -->
      </div>
      <div>
        <!-- <p>{{ Announcearticle.username }}  {{ Announcearticle?.created_at }}</p> -->
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
  name: 'AnnounceListItem',
  // props: {
  //   Announcearticle: Object,
  // }
}
</script>

<style>

</style>