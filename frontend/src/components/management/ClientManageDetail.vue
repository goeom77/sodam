<template>
  <v-card
    class="mx-auto"
    max-width="344"
  >

    <v-card-text>
      <p class="text-h4 text--primary">
        고객 성함
      </p>
      
      <div class="text--primary">
        <h3>최근 상담 일자 :</h3><br>
        <h3>{{ this.sessionInfo.clientId }}</h3>
        <h3>뭐든 넣겠찌 뭐 </h3>
      </div>
    </v-card-text>
    
    <v-card-actions>
      <v-btn
        text
        color="deep-purple accent-4"
      >
        Learn More
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name:'ClientManageDetail',
  props:{
    sessionInfo:Object
  }
}
</script>

<style>

</style>