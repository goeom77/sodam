<template>
  <div id="InquiryListItem">
    <h1>여기는 각 게시글</h1>
    <div>
      <!-- <h5>{{ Inquiryarticle.id }} -->
    </div>
    <div>
      <div>
        <!-- <router-link :to="{ name: 'InquiryDetail', params: { id: Inquiryarticle.id } }">
          {{ Inquiryarticle.title }}
        </router-link> -->
      </div>
      <div>
        <!-- <p>{{ Inquiryarticle.username }}  {{ Inquiryarticle?.created_at }}</p> -->
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
  name: 'InquiryListItem',
  // props: {
  //   Inquiryarticle: Object,
  // }
}
</script>

<style>

</style>