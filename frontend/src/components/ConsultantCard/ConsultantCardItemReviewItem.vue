<template>
  <div  v-if="(limit-1)*10 <= idx && idx < limit * 10">
    
    <v-card
    class="mx-auto"
    max-width="400"
  >
      <v-card-title>
        <div>
          {{review.title}}
        </div>
        <v-card-subtitle class="pt-4">
          {{ review.clientId }}
        </v-card-subtitle>
      </v-card-title>
      <v-card-text>
        <div>{{review.contents}}</div>
      </v-card-text>
  
    </v-card>
  </div>
</template>

<script>
export default {
  name:'ConsultantCardItemReviewItem',
  props:{
    review:Object,
    idx:Number,
    limit:Number
  }
}
</script>

<style>

</style>