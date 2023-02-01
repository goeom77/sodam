<!-- b-card와 style 사이에 들어갈 것
:title="Consultant.title"
:img-src="ConsultantPictureUrl"
img-alt="No Poster"
img-height=410
img-top
tag="article" -->
<template>
  <v-card
    class="mx-auto"
    max-width="344"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <!-- <div class="text-overline mb-4">
          OVERLINE
        </div> -->
        <v-list-item-title class="text-h5 mb-1">
          이름
        </v-list-item-title>

        <v-list-item-subtitle>소개</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar
        tile
        size="80"
        color="grey"
      ></v-list-item-avatar>
    </v-list-item>

    <v-card-actions>
      <v-btn
        outlined
        rounded
        text
        @click="moveTo"
      >
        Button
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'ConsultantCard',
  props: {
    Consultant: Object,
  },
  data(){
    return{
      moveTo(){
        this.$router.push({ name: 'consultantcarditem' })
      }
    }
  }
}
</script>

<style>

</style>
