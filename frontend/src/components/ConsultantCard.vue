<template>
        <!-- b-card와 style 사이에 들어갈 것
        :title="Consultant.title"
        :img-src="ConsultantPictureUrl"
        img-alt="No Poster"
        img-height=410
        img-top
        tag="article" -->
  <div>
    <div>
      <b-card

        style="max-width: 18rem; height: 40rem; box-shadow: 10px 10px 20px black;"
        class="mb-2"
      >
        <b-card-text>
          <p>상담사 태그 및 상담사 한마디</p>
        </b-card-text>

      </b-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ConsultantCard',
  props: {
    Consultant: Object,
  },
  computed: {
    // ConsultantPictureUrl() {
    //   return 'https://' + this.Consultant.poster_path
    // }
  },
}
</script>

<style>

</style>
